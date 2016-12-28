package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import po.FolderWithBLOBs;
import service.FolderService;
import service.UserService;
import util.QiNiu;
import util.YmlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Random;

/**
 * Created by Joe_C on 2016/12/4.
 */
@Controller
public class DiskCtrl {
    @Autowired
    private FolderService folderService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/disk", method = {RequestMethod.POST, RequestMethod.GET})
    public String disk(HttpSession session, HttpServletRequest request) throws Exception {
        if (session.getAttribute("isLogin") != null) {
            return "disk";
        }
        return "redirect:" + request.getContextPath() + "/user/account/signin";
    }

    @RequestMapping(value = "/disk/upload", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    FolderWithBLOBs upload(@RequestParam(value = "file") MultipartFile file, HttpSession session, HttpServletRequest request) throws Exception {
        FolderWithBLOBs folder = new FolderWithBLOBs();
        if (session.getAttribute("isLogin") == null) {
            return null;
        }
        String username = (String) session.getAttribute("username");
        String fileName = file.getOriginalFilename();//获取文件名
        String localFolder = YmlUtil.getValue("LocalStorage", "folder");//获取存储的文件夹
        String savePath = request.getServletContext().getRealPath("/" + localFolder + "/" + "user");//上传文件目录
        File targetFile = new File(savePath + File.separator + username, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //存储到服务器
        file.transferTo(targetFile);

        //上传到七牛云
        new QiNiu().upLoad(savePath + File.separator + username + File.separator + fileName,
                "user/" + username + "/" + fileName);
        //存储文件信息
        folder.setFile_name(fileName);
        folder.setComment("user/" + username + "/" + fileName);
        folder.setParent_path("/");
        folder.setIs_dir(0);
        folder.setSize(file.getSize());
        folder.setCreate_time(System.currentTimeMillis());
        folder.setUpdate_time(System.currentTimeMillis());
        folder.setUser_id(userService.getIdByUsername(username));

        //存储到数据库
        folderService.addFolder(folder);

        //是否开启服务器本地存储
        if (YmlUtil.getValue("LocalStorage", "use").equals("false")) {
            if (targetFile.exists()) {
                targetFile.delete();
            }
        }
        return folder;
    }

    private String getRandomString(int count) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            int num = random.nextInt(62);
            sb.append(str.charAt(num));
        }
        return sb.toString();

    }
}

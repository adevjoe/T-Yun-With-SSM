package controller;

import api.API_Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import po.FolderWithBLOBs;
import po.Result;
import service.FolderService;
import service.UserService;
import util.QiNiu;
import util.YmlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * Created by Joe_C on 2016/12/4.
 */
@Controller
@RequestMapping("/disk")
public class DiskCtrl {
    @Autowired
    private FolderService folderService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public String disk(HttpSession session, HttpServletRequest request) throws Exception {
        if (API_Utils.isLogin(session)) {
            return "disk";
        }
        return "redirect:" + request.getContextPath() + "/user/account/signin";
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Result upload(@RequestParam(value = "file") MultipartFile file, HttpSession session, HttpServletRequest request) throws Exception {
        Result result = new Result();
        if (API_Utils.isLogin(session)) {
            FolderWithBLOBs folder = new FolderWithBLOBs();
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

            result.setObject(folder);
            result.setMsg("上传成功！");
        }
        result.setMsg("用户未登录！");
        return result;
    }

    @RequestMapping(value = "/createFolder")
    public @ResponseBody Result createFolder(HttpSession session, FolderWithBLOBs folderWithBlobs) throws Exception{
        Result result = new Result();
        if (API_Utils.isLogin(session)) {
            FolderWithBLOBs folder = new FolderWithBLOBs();
            //存储文件信息
            folder.setFile_name(folderWithBlobs.getFile_name());
            folder.setParent_path(folderWithBlobs.getParent_path());
            folder.setIs_dir(1);
            folder.setCreate_time(System.currentTimeMillis());
            folder.setUpdate_time(System.currentTimeMillis());
            folder.setUser_id(userService.getIdByUsername((String) session.getAttribute("username")));
            //存储到数据库
            folderService.addFolder(folder);
            result.setObject(folder);
            result.setMsg("创建" + folder.getFile_name() + "成功！");
        }
        result.setMsg("用户未登录！");
        return result;
    }

    @RequestMapping(value = "/list")
    public @ResponseBody
    Result listFolder(HttpSession session, @RequestParam(value = "path") String path) throws Exception{
        Result result = new Result();
        if (API_Utils.isLogin(session)) {
            FolderWithBLOBs folderSearch = new FolderWithBLOBs();
            folderSearch.setUser_id(userService.getIdByUsername((String) session.getAttribute("username")));
            folderSearch.setParent_path(path);
            List<FolderWithBLOBs> list = folderService.listFolder(folderSearch);
            result.setObject(list);
            result.setMsg("成功！");
            return result;
        }
        result.setMsg("用户未登录！");
        return result;
    }
}

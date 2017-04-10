package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import po.Result;
import po.UDisk;
import service.UDiskService;
import util.QiNiu;
import util.YmlUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by joe on 17-3-2.
 * 移动 U 盘 API
 */
@Controller
@RequestMapping("/api/u_disk")
public class API_UDisk {

    @Autowired
    private UDiskService uDiskService;

    /**
     * 上传文件
     * @param file 文件
     * @param u 联系人标识
     * @param request 请求对象
     * @return 响应信息
     */
    @RequestMapping("/upload")
    public @ResponseBody
    Result upload(@RequestParam(value = "file") MultipartFile file, UDisk u, HttpServletRequest request){
        Result result = new Result();
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取存储的文件夹
        String localFolder = YmlUtil.getValue("LocalStorage", "folder");
        //七牛云中的 key
        String keyPath = "u_disk" + "/" + API_Utils.getRandomString(16);
        //上传文件目录
        String savePath = request.getServletContext().getRealPath("/" + localFolder + "/" + keyPath);
        File targetFile = new File(savePath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //存储到服务器
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //上传到七牛云
        new QiNiu().upLoad(savePath + File.separator + fileName,
                keyPath, YmlUtil.getValue("u_disk_bucket"));
        //存储文件信息
        UDisk uDisk = new UDisk();
        uDisk.setCode(API_Utils.getRandomString(8));
        uDisk.setCreate_time(System.currentTimeMillis());
        if (u.getMark() != null){
            uDisk.setMark(u.getMark());
        }
        uDisk.setUrl(keyPath);
        try {
            uDiskService.addFile(uDisk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //是否开启服务器本地存储
        if (YmlUtil.getValue("LocalStorage", "use").equals("false")) {
            if (targetFile.exists()) {
                targetFile.delete();
            }
        }
        result.setObject(uDisk);
        result.setMsg("上传成功！");
        return result;
    }

    /**
     * 获取文件
     * @param u　提取码
     * @return 响应信息
     */
    @RequestMapping("/getFile")
    public @ResponseBody Result getFile(UDisk u){
        Result result = new Result();
        try {
            UDisk uDisk = uDiskService.getFileByCode(u.getId());
            result.setObject(uDisk);
            result.setMsg("获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

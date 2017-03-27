package api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import po.Result;
import po.UDisk;
import util.QiNiu;
import util.YmlUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by joe on 17-3-2.
 * 图床 API
 */
@Controller
@RequestMapping("/api/photo_bucket")
public class API_PhotoBucket {

    @RequestMapping("/upload")
    public @ResponseBody
    Result upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
        Result result = new Result();
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取存储的文件夹
        String localFolder = YmlUtil.getValue("LocalStorage", "folder");
        //七牛云中的 key
        String keyPath = API_Utils.getRandomString(16);
        //上传文件目录
        String savePath = request.getServletContext().getRealPath("/" + localFolder + "/img/" + keyPath);
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
                keyPath, YmlUtil.getValue("img_bucket"));
        //是否开启服务器本地存储
        if (YmlUtil.getValue("LocalStorage", "use").equals("false")) {
            if (targetFile.exists()) {
                targetFile.delete();
            }
        }
        result.setObject(YmlUtil.getValue("img_domain") + File.separator + keyPath);
        result.setMsg("上传成功！");
        return result;
    }

}

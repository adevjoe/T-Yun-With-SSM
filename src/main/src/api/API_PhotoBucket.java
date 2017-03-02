package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Result;
import service.FolderService;
import service.UserService;

/**
 * Created by joe on 17-3-2.
 * 图床 API
 */
@Controller
@RequestMapping("/api/photo_bucket")
public class API_PhotoBucket {

    @Autowired
    private FolderService folderService;
    @Autowired
    private UserService userService;


    @RequestMapping("/upload")
    public @ResponseBody
    Result upload(){
        Result result = new Result();
        return result;
    }

}

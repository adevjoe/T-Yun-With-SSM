package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import po.FolderWithBLOBs;
import po.Result;
import service.FolderService;
import service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by Joe_C on 2017/1/29.
 * 云盘核心API
 */
@Controller
@RequestMapping("/api/disk")
public class API_Disk {
    @Autowired
    private FolderService folderService;
    @Autowired
    private UserService userService;

    /**
     * 获取当前路径的信息
     * @param session 会话，这里主要验证用户并获取用户id
     * @param path 获取的路径
     * @param folderWithBLOBs 一些参数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pathInfo")
    public @ResponseBody
    Result getPathInfo(HttpSession session, @RequestParam(value = "path") String path,
                       FolderWithBLOBs folderWithBLOBs) throws Exception{
        Result result = new Result();
        if (API_Utils.isLogin(session)) {
            FolderWithBLOBs f = new FolderWithBLOBs();
            f.setUser_id(userService.getIdByUsername((String) session.getAttribute("username")));
            f.setFile_name(path);
            f = folderService.getPathInfo(f);
            result.setMsg("成功！");
            result.setObject(f);
            return result;
        }
        result.setMsg("用户未登录！");
        return result;
    }

    @RequestMapping(value = "/pathList")
    public @ResponseBody
    Result getPathList(HttpSession session, @RequestParam(value = "path") String path,
                       FolderWithBLOBs folderWithBLOBs) throws Exception{
        Result result = new Result();
        String pathStr = "根目录";
        StringBuffer sb = new StringBuffer(pathStr);
        if (API_Utils.isLogin(session)) {
            FolderWithBLOBs f = new FolderWithBLOBs();
            f.setUser_id(userService.getIdByUsername((String) session.getAttribute("username")));
            f.setFile_name(path);
            f.setLevel(folderWithBLOBs.getLevel());
            f = folderService.getPathInfo(f);
            int level = f.getLevel();
            for (int i = level; i>1; i--) {
                f.setFile_name(f.getParent_path());
                f.setLevel(f.getLevel()-1);
                f = folderService.getPathInfo(f);
                sb.insert(3, "/");
                sb.insert(4, f.getFile_name());
            }
            sb.append("/");
            sb.append(path);
            result.setMsg("成功");
            result.setObject(sb);
            return result;
        }
        result.setMsg("用户未登录！");
        return result;
    }
}

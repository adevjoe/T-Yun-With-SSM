package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import po.FolderWithBLOBs;
import po.Result;
import service.FolderService;
import service.UserService;
import util.QiNiu;
import util.YmlUtil;

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
     * @param path 获取的路径，是当前路径，不是父路径
     * @param folderWithBLOBs 一些参数
     *                        file_name（此处为path）, level 必须
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

    /**
     * 获取路径列表
     * @param session 会话，这里主要验证用户并获取用户id
     * @param path 当前路径
     * @param folderWithBLOBs 一些参数
     *                        file_name（此处为path）, level 必须
     * @return
     * @throws Exception
     */
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

    /**
     * 创建文件夹
     * @param session 会话
     * @param folderWithBlobs 创建文件的一些参数
     *                        file_name, parent_path, level 必须
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createFolder")
    public @ResponseBody Result createFolder(HttpSession session, FolderWithBLOBs folderWithBlobs) throws Exception{
        Result result = new Result();
        if (API_Utils.isLogin(session)) {
            FolderWithBLOBs folder = new FolderWithBLOBs();
            //存储文件信息
            folder.setFile_name(folderWithBlobs.getFile_name());
            folder.setParent_path(folderWithBlobs.getParent_path());
            folder.setLevel(folderWithBlobs.getLevel());
            folder.setIs_dir(1);
            folder.setCreate_time(System.currentTimeMillis());
            folder.setUpdate_time(System.currentTimeMillis());
            folder.setUser_id(userService.getIdByUsername((String) session.getAttribute("username")));
            //存储到数据库
            folderService.addFolder(folder);
            result.setObject(folder);
            result.setMsg("创建文件夹 " + folder.getFile_name() + " 成功！");
            return result;//TODO 需要修复文件夹同名bug
        }
        result.setMsg("用户未登录！");
        return result;
    }

    /**
     * 根据文件id获取详细信息
     * @param session 会话
     * @param args 此处只需传入id
     * @return 响应信息
     * @throws Exception
     */
    @RequestMapping(value = "/fileDetail")
    public @ResponseBody Result fileDetail(HttpSession session, FolderWithBLOBs args)throws Exception{
        Result result = new Result();
        if (API_Utils.isLogin(session)) {
            FolderWithBLOBs folder = folderService.getFileDetail(args);
            folder.setComment("http://" + YmlUtil.getValue("chain_domain") + "/" + folder.getComment());
            result.setObject(folder);
            result.setMsg("成功！");
            return result;
        }
        result.setMsg("用户未登录！");
        return result;
    }

    /**
     * 删除文件
     * @param session 会话
     * @param args 文件id
     * @return 响应信息
     * @throws Exception
     */
    @RequestMapping(value = "/delFile")
    public @ResponseBody Result delFile(HttpSession session, FolderWithBLOBs args)throws Exception{
        Result result = new Result();
        if (API_Utils.isLogin(session)) {
            folderService.delFile(args.getId());
            result.setMsg("成功删除！");
            return result;
        }
        result.setMsg("用户未登录！");
        return result;
    }

}

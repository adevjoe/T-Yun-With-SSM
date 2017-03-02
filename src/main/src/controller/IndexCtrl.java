package controller;

import api.API_Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Joe_C on 2016/12/4.
 * Control all the pages
 */
@Controller
public class IndexCtrl {

    // home page
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    // photo bucket page
    @RequestMapping("/photo_bucket")
    public String photoBucket(){
        return "photo_bucket";
    }

    // u_disk page
    @RequestMapping("/u_disk")
    public String u_disk(){
        return "u_disk";
    }

    /**
     * control disk page
     * @param session 会话
     * @param request 请求对象
     * @return 指定页面，已经登录的返回文件页面，否则返回到登录页面
     */
    @RequestMapping("/disk")
    public String disk(HttpSession session, HttpServletRequest request){
        if (API_Utils.isLogin(session)) {
            return "disk";
        }
        return "redirect:" + request.getContextPath() + "/user/account/signin";
    }

}

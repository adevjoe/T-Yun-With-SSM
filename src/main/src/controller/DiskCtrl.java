package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Joe_C on 2016/12/4.
 */
@Controller
public class DiskCtrl {
    @RequestMapping(value = "/disk", method = {RequestMethod.POST,RequestMethod.GET})
    public String disk(HttpSession session, HttpServletRequest request) throws Exception{
        if (session.getAttribute("isLogin")!=null){
            return "disk";
        }
        return "redirect:" + request.getContextPath() + "/user/account/signin";
    }
}

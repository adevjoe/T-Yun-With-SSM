package controller;

import com.sun.deploy.net.HttpResponse;
import exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import po.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Joe_C on 2016/12/3.
 * 用户账户相关操作
 */
@Controller
@RequestMapping("/user/account")
public class UserAccountCtrl {
    @Autowired
    private UserService userService;

    /**
     * /user/account 页面调度控制
     * @param action
     * @param session
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/{action}")
    public String setView(@PathVariable("action") String action, HttpSession session, HttpServletRequest request) throws Exception{
        if (action.equalsIgnoreCase("signout")){
            if (session.getAttribute("isLogin")!=null){
                session.invalidate();
                return "redirect:" + request.getContextPath() + "/";
            }
            return "redirect:" + request.getContextPath() + "/";
        }
        if (session.getAttribute("isLogin")!=null){
            return "redirect:" + request.getContextPath() + "/";
        }
        if (action.equalsIgnoreCase("signin")){
            return "user/signin";
        }
        if (action.equalsIgnoreCase("signup")){
            return "user/signup";
        }
        return "redirect:" + request.getContextPath() + "/";
    }

    //TODO 注册的验证
    /**
     * 用户登录提交
     * @param user
     * @param session
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/signinSubmit", method = {RequestMethod.POST})
    public String signin(User user, HttpSession session, HttpServletRequest request) throws Exception{
        if (session.getAttribute("isLogin")!=null){
            return "redirect:" + request.getContextPath() + "/";
        }
        if (userService.login(user)){
            session.setAttribute("id", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("isLogin", true);
            return "redirect:" + request.getContextPath() + "/disk";
        }
        else {
            return "user/signin";
        }
    }

    /**
     * 用户注册提交
     * @param user
     * @param model
     * @param session
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/signupSubmit", method = {RequestMethod.POST})
    public String signup(User user,Model model,HttpSession session, HttpServletRequest request) throws Exception{
        if (session.getAttribute("isLogin")!=null){
            return "redirect:" + request.getContextPath() + "/";
        }
        userService.addUser(user);
        model.addAttribute("success","注册成功，请登录！");
        return "user/signin";
    }
}

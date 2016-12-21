package controller;

import exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.User;
import service.UserService;

/**
 * Created by Joe_C on 2016/12/2.
 */
@Controller
public class JsonTest {

    @Autowired
    private UserService userService;
    //请求json，返回json
    @RequestMapping("requestJson")
    public @ResponseBody
    User requestJson(@RequestBody User user) throws Exception {
        user = userService.getUserById(user.getId());
        if (user==null){
            throw new CustomException("用户不存在");
        }
        return user;
    }

    //请求k/v，返回json
    @RequestMapping("responseJson")
    //RequestBody解析json到pojo
    //ResponseBody解析pojo到json
    public @ResponseBody
    User responseJson(@RequestBody User user) throws Exception {
        user = userService.getUserById(user.getId());
        if (user==null){
            throw new CustomException("用户不存在");
        }
        return user;
    }
}

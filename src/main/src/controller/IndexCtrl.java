package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Joe_C on 2016/12/4.
 */
@Controller
public class IndexCtrl {
    @RequestMapping(value = "/", method = {RequestMethod.POST,RequestMethod.GET})
    public String index() throws Exception{
        return "index";
    }
}

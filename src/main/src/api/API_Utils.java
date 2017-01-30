package api;

import po.Result;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Created by Joe_C on 2017/1/30.
 */
public class API_Utils {

    /**
     * 获取随机字符串
     * @param count 字符串长度
     * @return String 类型字符串
     */
    public static String getRandomString(int count) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int num = random.nextInt(62);
            sb.append(str.charAt(num));
        }
        return sb.toString();
    }

    /**
     * 用户状态检测
     * @param session 用于检测
     * @return true or false
     */
    public static boolean isLogin(HttpSession session){
        boolean a = false;
        try{
            a =  session.getAttribute("isLogin").equals(true);
        }catch (Exception e){
            //忽略异常
        }
        return a;
    }
}

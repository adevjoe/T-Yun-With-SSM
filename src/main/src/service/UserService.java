package service;

import po.User;

import java.util.List;

/**
 * Created by Joe_C on 2016/12/1.
 */
public interface UserService {

    /**
     * 获取所有用户
     * @return List<User> 所有用户的集合
     * @throws Exception
     */
    List<User> getAllUser() throws Exception;

    /**
     * 根据ID获取用户信息
     * @param id 用户的ID
     * @return 用户pojo
     * @throws Exception
     */
    User getUserById(int id) throws Exception;

    /**
     * 执行注册用户的操作
     * @param user 用户pojo信息
     * @throws Exception
     */
    void addUser(User user) throws Exception;

    boolean login(User user)throws Exception;

}

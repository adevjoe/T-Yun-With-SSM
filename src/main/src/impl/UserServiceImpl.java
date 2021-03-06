package impl;

import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import po.User;
import service.UserService;
import util.MD5;

import java.util.List;

/**
 * Created by Joe_C on 2016/12/1.
 */
public class UserServiceImpl implements UserService {
    //注入mapper
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getAllUser() throws Exception {
        //通过UserMapper查询数据库
        return userMapper.getAllUser();
    }

    @Override
    public User getUserById(int id) throws Exception {
        return userMapper.findUserById(id);
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public int getIdByUsername(String username) throws Exception {
        return userMapper.getIdByUsername(username);
    }

    @Override
    public void addUser(User user) throws Exception {
        user.setPassword(MD5.getInstance().getMD5(user.getPassword()));
        userMapper.addUser(user);
    }

    @Override
    public boolean login(User user) throws Exception {
        user.setPassword(MD5.getInstance().getMD5(user.getPassword()));
        if (user.getPassword().equals(userMapper.getPasswordByUsername(user.getUsername()))){
            return true;
        }
        return false;
    }
}

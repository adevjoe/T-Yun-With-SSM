package mapper;

import po.User;

import java.util.List;

public interface UserMapper {

	//根据ID查询用户信息
    public User findUserById(int id)throws Exception;

    //根据用户名查询用户信息,模糊查询
    public List<User> findUserByName(String name)throws Exception;

    //根据用户名查询用户密码
    public String getPasswordByUsername(String username)throws Exception;

    //根据用户名查询用户id
    public int getIdByUsername(String username) throws Exception;

    //查询所有用户
    public List<User> getAllUser() throws Exception;

    //添加用户
    public void addUser(User user)throws Exception;

    //删除用户
    public void delUser(int id)throws Exception;

    //修改用户
    public void updateUser(User user)throws Exception;
}

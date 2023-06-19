package DAOS;

import Entity.Course;
import Entity.Teacher;
import User.User;

import java.util.LinkedList;

/**
 * @author caoqike
 * @date 2022-12-12 09:05:51
 */
public interface UserDAO {
    //增加
    public void addUser(User user);
    //删除
    public void deleteUser(User user);
    //修改
    public void updateUser(User user);
    //根据用户名查找
    public User getUser(String name);


    //返回全部用户信息
    public LinkedList<User> getAllUsers();

}

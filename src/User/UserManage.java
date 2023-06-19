package User;
import DAOS.DAOFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author caoqike
 * @date 2022-12-06 10:16:46
 */


public class UserManage {

    public void delUser(String name ,String id){

    }
    public static void saveInfo(User user){
        DAOFactory.getUserDAO().addUser(user);
    }
    public  void register() {

        System.out.println("请输入用户名：");
        Scanner sc=new Scanner(System.in);

        String name=sc.next();
        System.out.println("请输入密码：");
        String passwd=sc.next();
        UserType type=UserType.Administrator;
        User user= new Administrator(type,name,passwd);//文件输出流

        saveInfo(user);

    }


//    public void login() throws IOException, ClassNotFoundException {
//        UserType usertype = null;
//        String name;
//        String passwd;
//        System.out.println("请输入用户名：");
//        Scanner sc=new Scanner(System.in);
//
//        name=sc.next();
//        System.out.println("请输入密码：");
//        passwd=sc.next();
//
//        int flag=0;
//
//        LinkedList<User> objs = DAOFactory.getUserDAO().getAllUsers();
//
//        System.out.println(objs.size());
//        Iterator<User> itr = objs.iterator();
//
//        while (itr.hasNext()) {
//            User u=itr.next();
//            System.out.println(u.toString());
//            if (u.loadname.trim().equals(name)){
//                flag=1;
//                if (u.passwd.trim().equals(passwd)){
//                    System.out.println("登录成功");
//                    System.out.println(u.type);
//                    System.out.println(u.loadname+','+u.getLoadname());
//                    u.menu();
//
//                }
//                else
//                {
//                    System.out.println("密码错误");
//                }
//                break;
//            }
//        }
//        if (flag==0){
//            System.out.println("用户名不存在");
//        }
//
//    }
    public void login() throws IOException, ClassNotFoundException {
        String name;
        String passwd;
        System.out.println("请输入用户名：");
        Scanner sc=new Scanner(System.in);

        name=sc.next();
        System.out.println("请输入密码：");
        passwd=sc.next();

        int flag=0;
        LinkedList<User> objs = DAOFactory.getUserDAO().getAllUsers();


        //System.out.println(objs.size());
        Iterator<User> itr = objs.iterator();

        //在此设置学生登录权限，有权限进行第二次选课的学生，包括没有参与第一次选课的，和参与第一次选课但都没有中。**
        while (itr.hasNext()) {
            User u=itr.next();
            //System.out.println(u.loadname);
            if (u.loadname.trim().equals(name)){
                flag=1;
                if (u.passwd.trim().equals(passwd)){
                    System.out.println("登录成功!");
                    u.menu();
                }
                else
                {
                    System.out.println("密码错误!");
                }
                break;
            }
        }
        if (flag==0){
            System.out.println("用户名不存在");
        }
    }

    //假设助教模块一天的浏览量为3，辅助限制时间权限
    //**
    private static final int PAGEVIEW = 3;
    //打开对学生开放，但对教师限制的约束
    //***
    public void OpenMaster(){
        //构造连接
        Connection con = null;
        con = DAOFactory.getDAO().getConnection();
        try {

            //对学生开放
            String sql="update testUserInfo set use=? where type=?";
            PreparedStatement psmt = con.prepareStatement(sql);
            //1表示可以访问
            psmt.setInt(1, 1);
            psmt.setString(2, "Master");

            int row=psmt.executeUpdate();
            psmt.close();

            //0表示不可访问
            psmt.setInt(1, 0);
            psmt.setString(2, "Teacher");


            row+=psmt.executeUpdate();
            System.out.println("更新了"+row+"条记录！");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //打开对教师开放，但对学生限制的约束
    //***
    public void OpenTeacher(){
        //构造连接
        Connection con = null;
        con = DAOFactory.getDAO().getConnection();
        try {
            String sql="update testUserInfo set use=? where type=?";
            PreparedStatement psmt = con.prepareStatement(sql);

            //1表示可以访问
            psmt.setInt(1, 1);
            psmt.setString(2, "Teacher");

            int row=psmt.executeUpdate();
            psmt.close();

            //0表示不可访问
            psmt.setInt(1, 0);
            psmt.setString(2, "Masterr");


            row+=psmt.executeUpdate();
            System.out.println("更新了"+row+"条记录！");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

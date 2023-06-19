package User;


import java.io.IOException;
import java.util.Scanner;

/**
 * 该类是除系统管理员之外的用户登录界面
 * 功能：接收输入的用户名密码
 * 并判断用户类型
 * 进行相应的页面跳转
 **/

public class UserMenu{


    public static void menu() {
        System.out.println("--------------欢迎使用研究生培养环节和成果认定综合管理系统----------------");
        System.out.println("\t1.登录");
        System.out.println("\t2.退出");
        System.out.println("请选择（1~2）：");

        Scanner sc=new Scanner(System.in);

        //int choose=sc.nextInt();//输入一个整数
        boolean flag = true;
        while(flag){
            String choice = sc.next();
            UserManage um=new UserManage();
            switch (choice){
                case "1":
                    //System.out.println("login");
                    try {
                        um.login();
                        //calling(ut);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    flag = false;
                    break;
                case "2":
                    System.out.println("欢迎下次使用，谢谢！");
                    flag = false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入：");

            }
        }
    }
    public static void main(String [] args){
        menu();
    }
}

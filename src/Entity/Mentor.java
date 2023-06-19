package Entity;
import User.User;
import User.UserType;
import User.Menu;
/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9
 */
public class Mentor {
    private String menid;//导师号
    private String subid;//学科号
    private String name;//学生姓名

    public Mentor( String menid, String subid, String name) {

        this.menid = menid;
        this.subid = subid;
        this.name = name;
    }

    public Mentor() {

    }

    public String getMenid() {
        return menid;
    }

    public String getSubid() {
        return subid;
    }

    public String getName() {
        return name;
    }

    public void setMenid(String menid) {
        this.menid = menid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public void setName(String name) {
        this.name = name;
    }


//    public static void menu() {
//        System.out.println("Mentor");
//    }
}
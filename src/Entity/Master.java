package Entity;
import DAOS.DAOFactory;
import User.User;
import User.UserType;
import User.Menu;

import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9
 */

public class Master implements Menu {
    private String sid;//学生学号
    private String name;//学生姓名
    private String menid;//所属导师
    private Date addmissiontime;//入学时间
    private int stype;//学生类型（博士研究生，硕士研究生）


    public Master( String sid, String name, String menid, Date addmissiontime, int stype) {
        this.sid = sid;
        this.name = name;
        this.menid = menid;
        this.addmissiontime = addmissiontime;
        this.stype = stype;
    }

    public Master() {

    }


    public String getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getMenid() {
        return menid;
    }

    public Date getAddmissiontime() {
        return addmissiontime;
    }

    public int getStype() {
        return stype;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenid(String menid) {
        this.menid = menid;
    }

    public void setAddmissiontime(Date addmissiontime) {
        this.addmissiontime = addmissiontime;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }


//    public void AcademicActivityMenu(){
//        System.out.println("---------研究生学术活动成果认定---------");
//        System.out.println("1. 提交活动初审材料");
//        System.out.println("2. ");
//    }

    @Override
    public String toString() {
        return "Master{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", subject=" + menid +
                ", addmissiontime='" + addmissiontime + '\'' +
                ", stype=" + stype +
                '}';
    }

}
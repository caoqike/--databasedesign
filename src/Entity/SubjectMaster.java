/**
 * @author caoqike
 * @date 2022-12-08 21:49:27
 */
package Entity;
import DAOS.DAOFactory;
import User.User;
import User.UserType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import User.Menu;


public class SubjectMaster{
    private  String smid;
    private String subid;
    private String name;


    public SubjectMaster(String smid, String subid, String name) {
        this.smid = smid;
        this.subid = subid;
        this.name = name;
    }

    public String getSmid() {
        return smid;
    }

    public void setSmid(String smid) {
        this.smid = smid;
    }

    public String getSubid() {
        return this.subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubjectMaster{" +
                "smid='" + smid + '\'' +
                ", subid='" + subid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

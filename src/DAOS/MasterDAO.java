package DAOS;

import Entity.Master;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/10 9:01
 */
public interface MasterDAO {
    //增加
    public void addMaster(Master master);
    //删除
    public void deleteMaster(Master master);
    //修改
    public void updateMaster(Master master);
    //查找
    public Master getMaster(String id);

    //查找某一学科下学生
    public LinkedList<String> Masterlist(String subid);
    //查找某一导师下的学生
    List<Master> getMasterByMentor(String MentorId);
}

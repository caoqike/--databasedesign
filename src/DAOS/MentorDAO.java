package DAOS;

import Entity.Mentor;

/**
 * @author Administrator
 * @version 1.0
 * @data 2022/12/10 9:20
 */
public interface MentorDAO {
    //增加
    public void addMentor(Mentor mentor);
    //删除
    public void deleteMaster(Mentor mentor);
    //修改
    public void updateMaster(Mentor mentor);
    //查找
    public Mentor getMentor(String id);
}


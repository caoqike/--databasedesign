package DAOS;

import Entity.Teacher;

/**
 * @author Administrator
 * @version 1.0
 * @data 2022/12/9 19:35
 */
public interface TeacherDAO {
    //增加
    public void addTeacher(Teacher teacher);
    //删除
    public void deleteTeacher(Teacher teacher);
    //修改
    public void updateTeacher(Teacher teacher);
    //查找
    public Teacher getTeacher(String id);
}

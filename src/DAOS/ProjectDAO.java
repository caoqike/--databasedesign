package DAOS;

import Entity.Project;
import Entity.ProjectCerification;

import java.util.LinkedList;

public interface ProjectDAO {
    //增加
    public void addProject(Project project);
    //删除
    public void deleteProject(String projid);
    //修改
    public void updateProject(Project project);
    //查找
    public Project getProject(String projectid);

    //返回特定导师的项目
    public LinkedList<Project> getMentorProject(String menid);

    public LinkedList<Project> getManagerProject(String menid);
}

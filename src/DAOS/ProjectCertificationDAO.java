package DAOS;

import Entity.Course;
import Entity.Project;
import Entity.ProjectCerification;

import java.util.LinkedList;

public interface ProjectCertificationDAO {
    //增加
    public void addProjectCertification(ProjectCerification projectcertification);
    //删除
    public void deleteProjectCertification(ProjectCerification projectcertification);
    //修改
    public void updateProjectCertification(ProjectCerification projectcertification);
    //查找 根据证明编号
    public ProjectCerification getProjectCertification(String certid);
    //返回特定项目的证明
    public LinkedList<ProjectCerification> getGivenProjectCertification(String projid);

}


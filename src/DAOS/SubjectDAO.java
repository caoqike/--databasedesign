package DAOS;


import Entity.Subject;


/**
 * @author caoqike
 * @date 2022-12-09 08:41:47
 */
public interface SubjectDAO {
    void addSubject(Subject subject);
    void updateSubject(Subject subject);
    void  deleteSubject(Subject subject);
    Subject getSubject(String id);
}

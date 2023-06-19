package DAOS;

import Entity.Mentor;
import Entity.SubjectMaster;

import java.util.List;

/**
 * @author caoqike
 * @date 2022-12-08 21:48:19
 */
public interface SubjectMasterDAO {
    void addSubjectMaster(SubjectMaster subjectMaster);
    void updateSubjectMaster(SubjectMaster subjectMaster);
    void deleteSubjectMaster(SubjectMaster subjectMaster);
    SubjectMaster getSubjectMaster(String id);
    List<Mentor> getAllMentor(SubjectMaster subjectMaster);
}

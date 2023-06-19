package DAOS;

import Entity.GraduationRequirements;

import java.util.List;

public interface GraduationRequirementsDAO {
    void AddTeachingAssistantTimes(String masterid);
    void AddAcademicActivityTimes(String masterid);
    void AddPaperTimes(String masterid);
    void AddAwardTimes(String masterid);
    void AddStandardTimes(String masterid);
    void AddReportTimes(String masterid);
    void AddPatentTimes(String masterid);
    void Addhs_platformTimes(String masterid);
    void AddtextbookTimes(String masterid);
    void AddProjectCertificationTimes(String masterid);

    void AddNewmaster(String masterid);

    void Addmaster(GraduationRequirements gr);
    List<GraduationRequirements> showalllog();

    GraduationRequirements GetLogById(String masterid);
}

package DAOS;

import Entity.AcademicActivity;

import java.io.FileInputStream;
import java.util.List;

public interface AcademicActivityDAO {
    //增加
    void addAcademicActivity(AcademicActivity activity);
    //删除
    void deleteAcademicActivity(String ActivityId);
    //修改
    void updateAcademicActivity(AcademicActivity activity);

    //修改导师意见
    void updateTutorView(boolean permitted, String ActivityId);

    //修改学院意见
    void updateSubjectMasterView(boolean permitted, String ActivityId);

    //导入图片
    void conservePicture(FileInputStream file, String ActivityId, String ImageType);

    void conservePicture(AcademicActivity aa);

    List<AcademicActivity> getAcademicActivity(String MasterId);

    AcademicActivity getAcademicActivitybyId(String ActivityId);


}

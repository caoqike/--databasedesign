package DAOS;

import Entity.GraduationRequirements;

import javax.xml.bind.UnmarshallerHandler;

/**
 * @author caoqike,zhuwentao
 * @date 2022-12-08 21:31:54
 */
public class DAOFactory {
    private static DAOFactory daoFactory;
    static {
        daoFactory = new DAOFactory();

    }
    private DAOFactory(){

    }

    //DAO
    public static DAO getDAO(){
        return new DAOBase();
    }

    public static DAOFactory getInstance(){
        return daoFactory;
    }


    //SubjectDAO
    public static SubjectDAO getSubjectDAO(){
        return new SubjectDAOimp();
    }

    //TeacherDAO
    public static TeacherDAO getTeacherDAO(){
        return new TeacherDAOimp();
    }

    //CourseDAO
    public static CourseDAO getCourseDAO(){
        return new CourseDAOimp();
    }



    //MasterDAO
    public static MasterDAO getMasterDAO(){
        return new MasterDAOimp();
    }

    //MentorDAO
    public static MentorDAO getMentorDAO(){
        return new MentorDAOimp();
    }

    //SubjectMasterDAO
    public static SubjectMasterDAO getSubjectMasterDAO(){
        return new SubjectMasterimp();
    }

    //ChooseDAO
    public static ChooseDAO getChooseDAO(){return  new ChooseDAOimp();}

    //UserDAO
    public static UserDAO getUserDAO(){return  new UserDAOimp();}

    public static AcademicActivityDAO getAcademicActivityDAO(){
        return new AcademicActivityDAOimp();
    }

    public static awardDAO getawardDAO(){return new awardDAOImpl();}

    public static paperDAO getpaperDAO(){return new paperDAOImpl();}

    public static patentDAO getpatentDAO(){return new patentDAOImpl();}

    public static standardDAO getstandardDAO(){return new standardDAOImpl();}

    public static reportDAO getreportDAO(){return new reportDAOImpl();}

    public static hs_platformDAO getplatformDAO(){return new hs_platformDAOImpl();}

    public static textbookDAO gettextbookDAO(){return new textbookDAOImpl();}

    //ProjectDAO zxy
    public static ProjectDAO getProjectDAO(){
        return new ProjectDAOimp();
    }

    //ProjectCertificationDAO zxy
    public static ProjectCertificationDAO getProjectCertificationDAO() {
        return new ProjectCertificationDAOimp();
    }

    public static ChooseStateDAO getChooseStateDAO(){
        return new ChooseStateDAOimp();
    }

    public static ResultDAO getResultDAO() { return new ResultDAOimp();    }

    public static TutorTableDAO getTutorTableDAO(){return new TutorTableDAOimp();}

    public static GraduationRequirementsDAO getGraduationRequirementsDAO(){return new GraduationRequirementsDAOimp();}
}

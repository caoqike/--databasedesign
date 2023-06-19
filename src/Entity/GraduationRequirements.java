package Entity;

/**
 * 毕业要求次数记录表
 **/
public class GraduationRequirements {
    private String master_id;
    private int TeachingAssistant;
    private int AcademicActivity;
    private int Paper;
    private int Award;
    private int Standard;
    private int Report;
    private int Patent;
    private int hs_platform;
    private int textbook;
    private int ProjectCertification;

    public GraduationRequirements(){}

    public GraduationRequirements(String master_id){
        this.master_id = master_id;
        TeachingAssistant = 0;
        AcademicActivity = 0;
        Paper = 0;
        Award = 0;
        Standard = 0;
        Report = 0;
        Patent = 0;
        hs_platform = 0;
        this.textbook = 0;
        ProjectCertification = 0;
    }

    public GraduationRequirements(String master_id, int teachingAssistant, int academicActivity, int paper, int award, int standard, int report, int patent, int hs_platform, int textbook, int projectCertification) {
        this.master_id = master_id;
        TeachingAssistant = teachingAssistant;
        AcademicActivity = academicActivity;
        Paper = paper;
        Award = award;
        Standard = standard;
        Report = report;
        Patent = patent;
        this.hs_platform = hs_platform;
        this.textbook = textbook;
        ProjectCertification = projectCertification;
    }

    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public int getTeachingAssistant() {
        return TeachingAssistant;
    }

    public void setTeachingAssistant(int teachingAssistant) {
        TeachingAssistant = teachingAssistant;
    }

    public int getAcademicActivity() {
        return AcademicActivity;
    }

    public void setAcademicActivity(int academicActivity) {
        AcademicActivity = academicActivity;
    }

    public int getPaper() {
        return Paper;
    }

    public void setPaper(int paper) {
        Paper = paper;
    }

    public int getAward() {
        return Award;
    }

    public void setAward(int award) {
        Award = award;
    }

    public int getStandard() {
        return Standard;
    }

    public void setStandard(int standard) {
        Standard = standard;
    }

    public int getReport() {
        return Report;
    }

    public void setReport(int report) {
        Report = report;
    }

    public int getPatent() {
        return Patent;
    }

    public void setPatent(int patent) {
        Patent = patent;
    }

    public int getHs_platform() {
        return hs_platform;
    }

    public void setHs_platform(int hs_platform) {
        this.hs_platform = hs_platform;
    }

    public int getTextbook() {
        return textbook;
    }

    public void setTextbook(int textbook) {
        this.textbook = textbook;
    }

    public int getProjectCertification() {
        return ProjectCertification;
    }

    public void setProjectCertification(int projectCertification) {
        ProjectCertification = projectCertification;
    }

    @Override
    public String toString() {
        return  "研究生号：" + master_id  +
                ", 助教完成次数：" + TeachingAssistant +
                ", 学术交流完成次数：" + AcademicActivity +
                ", 论文数量：" + Paper +
                ", 奖励数量：" + Award +
                ", 标准数量：" + Standard +
                ", 报告数量：" + Report +
                ", 专利数量：" + Patent +
                ", 软硬件平台：" + hs_platform +
                ", 教材数量：" + textbook +
                ", 参与项目数量：" + ProjectCertification;
    }
}

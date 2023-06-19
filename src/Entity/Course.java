package Entity;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9
 */
public class Course {
    private String couseid;//课程号
    private String  subid;//对应学科
    private String tid;//教授课程教师
    private String name;//课程名称
    private int hours;//课时
    private int applications;//选课人数
    private int state;//课程状态
    private int type;//授课性质 0 选修 1 必修
    private int audience; //0本科生 1研究生

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public Course(String couseid, String subid, String tid, String name, int hours, int applications, int state) {
        this.couseid = couseid;
        this.subid = subid;
        this.tid = tid;
        this.name = name;
        this.hours = hours;
        this.applications = applications;
        this.state = state;
    }

    public Course(String couseid, String subid, String tid, String name, int hours, int applications, int state, int type, int audience, String time) {
        this.couseid = couseid;
        this.subid = subid;
        this.tid = tid;
        this.name = name;
        this.hours = hours;
        this.applications = applications;
        this.state = state;
        this.type = type;
        this.audience = audience;
        this.time = time;
    }

    public String getCouseid() {
        return couseid;
    }

    public String getSubid() {
        return subid;
    }

    public String getTid() {
        return tid;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getApplications() {
        return applications;
    }

    public int getState() {
        return state;
    }

    public void setCouseid(String couseid) {
        this.couseid = couseid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setApplications(int applications) {
        this.applications = applications;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }
}

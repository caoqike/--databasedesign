package Entity;

import java.util.Date;

public class ProjectCerification {
    /*certid 不重复证书编号（序号）
mid 学生学号 sid
projid 项目编号
starttime 开始时间
endtime 结束时间
assignment 承担工作
expenditure 承担工作的折合经费
mentorsign 导师认定
managersign 负责人认定
no 序号 没用
*/
    private String certid;
    private String sid;
    private String projid;
    private Date startdate;
    private Date enddate;
    private String assignment;
    private float expenditure;
    private int mentorsign=0;
    private int managersign=0;
    private int no = 0;//可以不用

    public ProjectCerification(String certid, String sid, String projid, Date startdate, Date enddate, String assignment, float expenditure, int mentorsign, int managersign) {
        this.certid = certid;
        this.sid = sid;
        this.projid = projid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.assignment = assignment;
        this.expenditure = expenditure;
        this.mentorsign = mentorsign;
        this.managersign = managersign;
    }

    public ProjectCerification(String certid, String sid, String projid, Date startdate, Date enddate, String assignment) {
        this.certid = certid;
        this.sid = sid;
        this.projid = projid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.assignment = assignment;
    }

    public ProjectCerification() {
    }

    public String getCertid() {
        return certid;
    }

    public void setCertid(String certid) {
        this.certid = certid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getProjid() {
        return projid;
    }

    public void setProjid(String projid) {
        this.projid = projid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public float getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(float expenditure) {
        this.expenditure = expenditure;
    }

    public int getMentorsign() {
        return mentorsign;
    }

    public void setMentorsign(int mentorsign) {
        this.mentorsign = mentorsign;
    }

    public int getManagersign() {
        return managersign;
    }

    public void setManagersign(int managersign) {
        this.managersign = managersign;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "ProjectCerification{" +
                "certid='" + certid + '\'' +
                ", sid='" + sid + '\'' +
                ", projid='" + projid + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", assignment='" + assignment + '\'' +
                ", expenditure=" + expenditure +
                ", mentorsign=" + mentorsign +
                ", managersign=" + managersign +
                '}';
    }
}

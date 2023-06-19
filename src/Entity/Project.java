package Entity;

public class Project {
    private String proid;//项目编号
    private String proname;//项目名称
    private String menid;//导师
    private String protype;//项目类型
    private String managerid;//项目负责人编号

    public Project() {
    }

    public Project(String proid, String menid, String proname, String protype, String managerid) {
        this.proid = proid;
        this.proname = proname;
        this.menid = menid;
        this.protype = protype;
        this.managerid = managerid;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getMenid() {
        return menid;
    }

    public void setMenid(String menid) {
        this.menid = menid;
    }

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    @Override
    public String toString() {
        return "Project{" +
                "proid='" + proid + '\'' +
                ", proname='" + proname + '\'' +
                ", menid=" + menid +
                ", protype='" + protype + '\'' +
                ", managerid='" + managerid + '\'' +
                '}';
    }
}


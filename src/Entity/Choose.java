package Entity;

public class Choose {

    //课程号
    String couseid;
    //硕士学号
    String mid;

    public String getCouseid() {
        return couseid;
    }

    public void setCouseid(String couseid) {
        this.couseid = couseid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Choose(String couseid, String mid) {
        this.couseid = couseid;
        this.mid = mid;
    }
}

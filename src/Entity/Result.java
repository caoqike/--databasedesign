package Entity;

/**
 * @author Administrator
 * @version 1.0
 * @data 2022/12/15 10:19
 */
public class Result {

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

    public Result(String couseid, String mid) {
        this.couseid = couseid;
        this.mid = mid;
    }


}


package Entity;

/**
 * @author caoqike
 * @date 2022-12-20 09:27:12
 */
public class TutorTableAllInfo {
    String mid;
    String mname;
    String couname;

    int type;//0 1
    int audience;
    String tname;
    String time;
    String selfdesc;
    int result;
    int size;
    String subname;

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getCouname() {
        return couname;
    }

    public void setCouname(String couname) {
        this.couname = couname;
    }

    public TutorTableAllInfo(String mid, String mname, String couname, int type, int audience, String tname, String time, String selfdesc, int result,int size,String subname) {
        this.mid = mid;
        this.mname = mname;
        this.couname = couname;
        this.type = type;
        this.audience = audience;
        this.tname = tname;
        this.time = time;
        this.selfdesc = selfdesc;
        this.result = result;
        this.size=size;
        this.subname=subname;
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

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSelfdesc() {
        return selfdesc;
    }

    public void setSelfdesc(String selfdesc) {
        this.selfdesc = selfdesc;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}


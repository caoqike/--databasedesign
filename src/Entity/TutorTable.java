package Entity;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9
 */
public class  TutorTable{
    String mid;
    String tid;
    String couid;
    byte result;
    String selfdesc;
    public TutorTable(String mid, String tid, String couid, byte result, String selfdesc) {
        this.mid = mid;
        this.tid = tid;
        this.couid = couid;
        this.result = result;
        this.selfdesc = selfdesc;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCouid() {
        return couid;
    }

    public void setCouid(String couid) {
        this.couid = couid;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public String getSelfdesc() {
        return selfdesc;
    }

    public void setSelfdesc(String selfdesc) {
        this.selfdesc = selfdesc;
    }
}
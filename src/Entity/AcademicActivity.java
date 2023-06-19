package Entity;

import java.io.*;
import java.security.cert.Certificate;
import java.sql.Date;

public class AcademicActivity {
    private String activity_id;
    private String master_id;
    private String activity_name;
    private Date date;
    private String report_name;
    //以File形式读入图片
    private String certificate;
    private String image_type;
    private boolean tutor_view;
    private boolean master_view;


    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getCertificate() {
        return certificate;
    }

    public InputStream getCertificateStream(){
        try {
            return new FileInputStream(this.certificate);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    //    public InputStream getCertificate() {
//
//        return certificate;
//    }
//
//    public void setCertificate(InputStream certificate){
//        this.certificate = certificate;
//    }
//
//    public void setCertificate(byte[] certificate) {
//        this.certificate = null;
//        String localpath = "C:\\Users\\lhdyf123\\Desktop\\";
//        String path = localpath+this.activity_id.trim().replace(':','_')+"."+this.getImage_type();
//        System.out.println(this.toString());
//        //System.out.println(path);
//        File file = new File(path);
//        FileInputStream fileInputStream = null;
//        try{
//            OutputStream output = new FileOutputStream(file);
//            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
//            bufferedOutput.write(certificate);
//            fileInputStream= new FileInputStream(file);
//            file.deleteOnExit();
//            this.certificate = fileInputStream;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //this.certificate = certificate;
//    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public boolean isTutor_view() {
        return tutor_view;
    }

    public void setTutor_view(boolean tutor_view) {
        this.tutor_view = tutor_view;
    }

    public boolean isMaster_view() {
        return master_view;
    }

    public void setMaster_view(boolean master_view) {
        this.master_view = master_view;
    }

    @Override
    public String toString() {
        return  "学术交流活动ID：" + activity_id +
                "学号：" + master_id +
                ", 学术活动名称：" + activity_name +
                ", 日期：" + date +
                ", 报告名称：" + report_name +
                ", 图片格式：" + image_type +
                ", 导师意见" + tutor_view +
                ", 学科负责人意见：" + master_view +
                '}';
    }

    public String tutorToString(){
        return  "学术交流活动ID：" + activity_id  +
                ", 学术活动名称：" + activity_name +
                ", 学术活动日期：" + date +
                ", 报告名称：" + report_name ;
    }

}

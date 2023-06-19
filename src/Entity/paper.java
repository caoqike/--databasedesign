package Entity;

import java.sql.Blob;

public class paper {
    private Master master;

    public paper(Master master) {
        this.master = master;
    }

    private String name;
    private String periodical;
    private int state;
    private String time;
    private String index_type;
    private int Attribution;
    private String materials;
    private String image_type;
    private String tutor_view;
    private String last_view;


    public paper(Master master, String image_type, String tutor_view, String last_view) {
        this.master = master;
        this.image_type = image_type;
        this.tutor_view = tutor_view;
        this.last_view = last_view;
    }

    public paper(String name, String periodical, int state, String time, String index_type, int attribution, String materials) {
        this.name = name;
        this.periodical = periodical;
        this.state = state;
        this.time = time;
        this.index_type = index_type;
        Attribution = attribution;
        this.materials = materials;
    }

    public paper() {

    }


    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getTutor_view() {
        return tutor_view;
    }

    public void setTutor_view(String tutor_view) {
        this.tutor_view = tutor_view;
    }

    public String getLast_view() {
        return last_view;
    }

    public void setLast_view(String last_view) {
        this.last_view = last_view;
    }


    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriodical() {
        return periodical;
    }

    public void setPeriodical(String periodical) {
        this.periodical = periodical;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIndex_type() {
        return index_type;
    }

    public void setIndex_type(String index_type) {
        this.index_type = index_type;
    }

    public int getAttribution() {
        return Attribution;
    }

    public void setAttribution(int attribution) {
        Attribution = attribution;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }
}

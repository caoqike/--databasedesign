package Entity;

import java.sql.Blob;

public class hs_platform {
    private Master master;

    public hs_platform(Master master) {
        this.master = master;
    }


    private String name;
    private String unit;
    private String time;
    private int ranking;
    private String materials;
    private String image_type;
    private String tutor_view;
    private String last_view;
    public hs_platform(Master master, String image_type, String tutor_view, String last_view) {
        this.master = master;
        this.image_type = image_type;
        this.tutor_view = tutor_view;
        this.last_view = last_view;
    }

    public hs_platform( String name, String unit, String time, int ranking, String materials) {
        this.name = name;
        this.unit = unit;
        this.time = time;
        this.ranking = ranking;
        this.materials = materials;
    }

    public hs_platform() {

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }
}

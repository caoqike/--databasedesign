package Entity;

import java.sql.Blob;

public class standard {
    private Master master;


    public standard(Master master) {
        this.master = master;
    }

    private String id_standard;
    private String name;
    private int standard_level;
    private String time;
    private String materials;

    private String image_type;
    private String tutor_view;
    private String last_view;


    public standard(Master master, String image_type, String tutor_view, String last_view) {
        this.master = master;
        this.image_type = image_type;
        this.tutor_view = tutor_view;
        this.last_view = last_view;
    }

    public standard(String id_standard, String name, int standard_level, String time, String materials) {
        this.id_standard = id_standard;
        this.name = name;
        this.standard_level = standard_level;
        this.time = time;
        this.materials = materials;
    }

    public standard() {

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


    public String getId_standard() {
        return id_standard;
    }

    public void setId_standard(String id_standard) {
        this.id_standard = id_standard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard_level() {
        return standard_level;
    }

    public void setStandard_level(int standard_level) {
        this.standard_level = standard_level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }
}
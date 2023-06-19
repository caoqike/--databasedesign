package Entity;

import java.sql.Blob;

public class patent {
    private Master master;

    public patent(Master master) {
        this.master = master;
    }

    private String name;
    private int type;
    private String number;
    private String time;
    private String state;
    private int ranking;
    private String materials;

    private String image_type;
    private String tutor_view;
    private String last_view;

    public patent(Master master, String image_type, String tutor_view, String last_view) {
        this.master = master;
        this.image_type = image_type;
        this.tutor_view = tutor_view;
        this.last_view = last_view;
    }

    public patent(String name, int type, String number, String time, String state, int ranking, String materials) {
        this.name = name;
        this.type = type;
        this.number = number;
        this.time = time;
        this.state = state;
        this.ranking = ranking;
        this.materials = materials;
    }

    public patent() {

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

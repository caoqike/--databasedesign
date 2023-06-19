package Entity;

import java.sql.Blob;

public class textbook {
    private Master master;

    public textbook() {

    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public textbook(Master master) {
        this.master = master;
    }

    private String name;
    private String press;
    private String time;
    private int ranking;
    private String materials;

    private String image_type;
    private String tutor_view;
    private String last_view;

    public textbook(Master master, String image_type, String tutor_view,String last_view) {
        this.master = master;
        this.image_type = image_type;
        this.tutor_view = tutor_view;
        this.last_view = last_view;
    }

    public textbook(String name, String press, String time, int ranking, String materials) {
        this.name = name;
        this.press = press;
        this.time = time;
        this.ranking = ranking;
        this.materials = materials;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
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

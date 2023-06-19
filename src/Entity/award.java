package Entity;


import DAOS.awardDAO;
import DAOS.awardDAOImpl;

public class award {

    private Master master;
    private String name;
    private int reward_grade;
    private int award_grade;
    private int ranking;
    private String time;
    private String materials;
    private String image_type;
    private String tutor_view;
    private String last_view;

    public award(){

    }

    public award(Master master, String image_type, String tutor_view, String last_view) {
        this.master = master;
        this.image_type = image_type;
        this.tutor_view = tutor_view;
        this.last_view = last_view;
    }

    public award( String name, int reward_grade, int award_grade, int ranking, String time, String materials) {
        this.name = name;
        this.reward_grade = reward_grade;
        this.award_grade = award_grade;
        this.ranking = ranking;
        this.time = time;
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

    public int getReward_grade() {
        return reward_grade;
    }

    public void setReward_grade(int reward_grade) {
        this.reward_grade = reward_grade;
    }

    public int getAward_grade() {
        return award_grade;
    }

    public void setAward_grade(int award_grade) {
        this.award_grade = award_grade;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
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

//   public static void main(String[] args) {
//        Master master = new Master();
//        master.setSid("0022352");
//        award award = new award();
//        award.setId_award("00221");
//        award.setMaster(master);
//        award.setName("奖项");
//        award.setAward_grade(2);
//        award.setRanking(34);
//        award.setReward_grade(3);
//        award.setTime("2022-3-4");
//        award.setMaterials("D:\\school.jpg");
//        awardDAOImpl test = new awardDAOImpl();
//        test.submitaward(award);
//    }

}

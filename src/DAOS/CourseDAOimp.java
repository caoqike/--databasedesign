package DAOS;

import Entity.Course;
import Entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9 20:33
 */
public class CourseDAOimp extends DAOBase implements CourseDAO{

    @Override
    public void changeCourseState(String courseid, int status) {
        Connection con=null;
        con=getConnection();
        String sql="update Course set state=? where couseid=?";
        try {
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setInt(1,status);
            psmt.setString(2,courseid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCourse(Course course) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into Course values(?,?,?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, course.getCouseid());
            psmt.setString(2, course.getSubid());
            psmt.setString(3, course.getTid());
            psmt.setString(4, course.getName());
            psmt.setInt(5, course.getHours());
            psmt.setInt(6, course.getApplications());
            psmt.setInt(7, course.getState());

            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCourse(Course course) {

    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public Course getCourse(String courseid) {
        String sql="select * from Course where couseid=?";

        Connection con = null;
        Course course =null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, courseid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                course = new Course(rs.getString("couseid"), rs.getString("subid"),
                        rs.getString("tid"),
                        rs.getString("name"),
                        rs.getInt("hours"),
                        rs.getInt("applications"),
                        rs.getInt("state"));
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert con != null;
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return course;


    }

    //每个学科负责需要查看对应学科下的所有课程信息，以便确定需要助教的课程列表
    @Override
    public LinkedList<Course> getAllCourses(String subid) {
        LinkedList<Course>list=new LinkedList<>();
        String sql="select * from Course where subid=?";


        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,subid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Course course = new Course(rs.getString("couseid"),
                        rs.getString("subid"),
                        rs.getString("tid"),
                        rs.getString("name"),
                        rs.getInt("hours"),
                        rs.getInt("applications"),
                        rs.getInt("state")
                        );
                list.add(course);
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert con != null;
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;

    }

    @Override
    public LinkedList<Course> getStateCourses(String subid,int state) {
        LinkedList<Course>list=new LinkedList<>();
        String sql="select * from Course where subid=? and state =?";


        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,subid);
            psmt.setInt(2,state);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Course course = new Course(rs.getString("couseid"),
                        rs.getString("subid"),
                        rs.getString("tid"),
                        rs.getString("name"),
                        rs.getInt("hours"),
                        rs.getInt("applications"),
                        rs.getInt("state")
                );
                list.add(course);
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert con != null;
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    //每个教师获取自己教授的需要助教的课程
    @Override
    public LinkedList<Course> getteachCourses(String tid) {
        String sql="select * from Course where tid=?";
        LinkedList<Course> courses=new LinkedList<>();
        Connection conn=null;

        try{
            conn=getConnection();
            PreparedStatement psmt=conn.prepareStatement(sql);
            psmt.setString(1,tid);

            ResultSet rs= psmt.executeQuery();
            while(rs.next()){
                Course course = new Course(rs.getString("couseid"),
                        rs.getString("subid"),
                        rs.getString("tid"),
                        rs.getString("name"),
                        rs.getInt("hours"),
                        rs.getInt("applications"),
                        rs.getInt("state"),
                        rs.getInt("type"),
                        rs.getInt("audience"),
                        rs.getString("time")
                );
                courses.add(course);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert conn != null;
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return courses;
    }
}

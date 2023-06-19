package DAOS;

import Entity.Course;
import Entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProjectDAOimp extends DAOBase implements ProjectDAO{

    public void addProject(Project project) {
        String sql = "insert into Project values(?,?,?,?,?)";
        Connection con = null;
        con = getConnection();
        try {

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, project.getProid());
            psmt.setString(2, project.getMenid());
            psmt.setString(3, project.getProname());
            psmt.setString(4, project.getProtype());
            psmt.setString(5, project.getManagerid());

            psmt.executeUpdate();
            psmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    public void deleteProject(String projid) {
        String sql = "delete from Project where projid=?";
        Connection con = null;
        con = getConnection();
        try {

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, projid);


            psmt.executeUpdate();
            psmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void updateProject(Project project) {
        String sql = "update Project set projid=?,menid=?,name=?,type=?,managerid=? where projid=?";
        Connection con = null;
        con = getConnection();
        try {

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, project.getProid());
            psmt.setString(2, project.getMenid());
            psmt.setString(3, project.getProname());
            psmt.setString(4, project.getProtype());
            psmt.setString(5, project.getManagerid());
            psmt.setString(6, project.getProid());

            psmt.executeUpdate();
            psmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Project getProject(String projectid) {
        String sql = "select * from Project where projid=?";
        Connection con = null;
        con = getConnection();
        Project project = null;
        try {

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, projectid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                project = new Project(rs.getString("projid"),
                        rs.getString("menid"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("managerid"));
            }
            psmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return project;
    }

    @Override
    public LinkedList<Project> getMentorProject(String menid) {
        LinkedList<Project>list = new LinkedList<>();
        String sql = "select * from Project where menid = ?";

        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,menid);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Project project = new Project(rs.getString("projid"),
                        rs.getString("menid"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("managerid"));
                list.add(project);
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;

    }

    @Override
    public LinkedList<Project> getManagerProject(String menid) {
        LinkedList<Project>list = new LinkedList<>();
        String sql = "select * from Project where managerid = ?";

        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,menid);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Project project = new Project(rs.getString("projid"),
                        rs.getString("menid"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("managerid"));
                list.add(project);
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }
}


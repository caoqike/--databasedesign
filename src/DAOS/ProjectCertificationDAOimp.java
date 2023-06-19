package DAOS;

import Entity.Project;
import Entity.ProjectCerification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/*
certid 不重复证书编号（序号）
mid 学生学号
projid 项目编号
starttime 开始时间
endtime 结束时间
assignment 承担工作
expenditure 承担工作的折合经费
mentorsign 导师认定
managersign 负责人认定
no 序号 没用
*/
public class ProjectCertificationDAOimp extends DAOBase implements ProjectCertificationDAO{
    @Override
    public void addProjectCertification(ProjectCerification projectcertification) {
        String sql = "insert into proj_certification values(?,?,?,?,?,?,?,?,?,?)";
        Connection con = null;
        con = getConnection();
        try {

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, projectcertification.getCertid());
            psmt.setString(2, projectcertification.getSid());
            psmt.setString(3, projectcertification.getProjid());
            psmt.setString(4, projectcertification.getStartdate().toString());
            psmt.setString(5, projectcertification.getEnddate().toString());
            psmt.setString(6, projectcertification.getAssignment());
            psmt.setFloat(7, projectcertification.getExpenditure());
            psmt.setInt(8, projectcertification.getMentorsign());
            psmt.setInt(9, projectcertification.getManagersign());
            psmt.setInt(10, projectcertification.getNo());

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

    @Override
    public void deleteProjectCertification(ProjectCerification projectcertification) {
        String sql = "delete from proj_certification where certid=?";

        Connection con = null;
        con = getConnection();
        try {

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, projectcertification.getCertid());


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

    @Override
    public void updateProjectCertification(ProjectCerification projectcertification) {
        String sql = "update proj_certification set certid=?," +
                "mid=?," +
                "projid=?," +
                "starttime=?," +
                "endtime=?," +
                "assignment=?," +
                "expenditure=?," +
                "mentorsign=?," +
                "managersign=?," +
                "no=? " +
                "where certid=?";

        Connection con = null;
        con = getConnection();
        try {

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, projectcertification.getCertid());
            psmt.setString(2, projectcertification.getSid());
            psmt.setString(3, projectcertification.getProjid());
            psmt.setString(4, projectcertification.getStartdate().toString());
            psmt.setString(5, projectcertification.getEnddate().toString());
            psmt.setString(6, projectcertification.getAssignment());
            psmt.setFloat(7, projectcertification.getExpenditure());
            psmt.setInt(8, projectcertification.getMentorsign());
            psmt.setInt(9, projectcertification.getManagersign());
            psmt.setInt(10, projectcertification.getNo());
            psmt.setString(11, projectcertification.getCertid());

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

    @Override
    public ProjectCerification getProjectCertification(String certid) {
        String sql = "select * from proj_certification where certid=?";
        Connection con = null;
        con = getConnection();
        ProjectCerification projectcerification = null;
        try {
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, certid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                projectcerification = new ProjectCerification(rs.getString("certid"),
                        rs.getString("mid"),
                        rs.getString("projid"),
                        rs.getDate("starttime"),
                        rs.getDate("endtime"),
                        rs.getString("assignment"),
                        rs.getFloat("expenditure"),
                        rs.getInt("mentorsign"),
                        rs.getInt("managersign"));
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


        return projectcerification;

    }

    @Override
    public LinkedList<ProjectCerification> getGivenProjectCertification(String projid) {
        String sql = "select * from proj_certification where projid=?";
        LinkedList<ProjectCerification>list = new LinkedList<>();

        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,projid);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                ProjectCerification projectcertification = new ProjectCerification(rs.getString("certid"),
                        rs.getString("mid"),
                        rs.getString("projid"),
                        rs.getDate("starttime"),
                        rs.getDate("endtime"),
                        rs.getString("assignment"),
                        rs.getFloat("expenditure"),
                        rs.getInt("mentorsign"),
                        rs.getInt("managersign"));
                list.add(projectcertification);
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
}


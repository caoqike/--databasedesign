package DAOS;

import Entity.GraduationRequirements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GraduationRequirementsDAOimp extends DAOBase implements GraduationRequirementsDAO {
    @Override
    public void AddTeachingAssistantTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set TeachingAssistant=TeachingAssistant+1 where mid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddAcademicActivityTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set AcademicActivity=AcademicActivity+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddPaperTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set Paper=Paper+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddAwardTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set Award=Award+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddStandardTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set Award=Award+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddReportTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set Award=Award+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddPatentTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set Award=Award+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void Addhs_platformTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set hs_platform=hs_platform+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddtextbookTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set textbook=textbook+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddProjectCertificationTimes(String masterid) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update GraduationRequirements set ProjectCertification=ProjectCertification+1 where masterid = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void AddNewmaster(String masterid){
        //构造连接
        Connection con;
        con = getConnection();
        try {
            //增加一条记录
            //待改
            String sql="insert into GraduationRequirements(mid) values(?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, masterid);
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void Addmaster(GraduationRequirements gr) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            //增加一条记录
            //待改
            String sql="insert into GraduationRequirements(mid,TeachingAssistant,AcademicActivity,Paper,Award,Standard,Report,Patent,hs_platform,textbook,ProjectCertification) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, gr.getMaster_id());
            psmt.setInt(2, gr.getTeachingAssistant());
            psmt.setInt(3, gr.getAcademicActivity());
            psmt.setInt(4, gr.getPaper());
            psmt.setInt(5, gr.getAward());
            psmt.setInt(6, gr.getStandard());
            psmt.setInt(7, gr.getReport());
            psmt.setInt(8, gr.getPatent());
            psmt.setInt(9, gr.getHs_platform());
            psmt.setInt(10, gr.getTextbook());
            psmt.setInt(11, gr.getProjectCertification());
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<GraduationRequirements> showalllog() {
        List<GraduationRequirements> grlist = new ArrayList<>();
        Connection con = null;
        try{
            con = getConnection();
            String sql="select * from GraduationRequirements";
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                GraduationRequirements g = new GraduationRequirements();
                g.setAward(rs.getInt("Award"));
                g.setAcademicActivity(rs.getInt("AcademicActivity"));
                g.setHs_platform(rs.getInt("hs_platform"));
                g.setPaper(rs.getInt("Paper"));
                g.setPatent(rs.getInt("Patent"));
                g.setMaster_id(rs.getString("mid"));
                g.setReport(rs.getInt("report"));
                g.setStandard(rs.getInt("Standard"));
                g.setProjectCertification(rs.getInt("ProjectCertification"));
                g.setTextbook(rs.getInt("textbook"));
                g.setTeachingAssistant(rs.getInt("TeachingAssistant"));
                grlist.add(g);
            }
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert con != null;
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return grlist;
    }

    @Override
    public GraduationRequirements GetLogById(String masterid) {
        Connection con = null;
        GraduationRequirements g = new GraduationRequirements();
        try{
            con = getConnection();
            String sql="select * from GraduationRequirements where mid = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,masterid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                g.setAward(rs.getInt("Award"));
                g.setAcademicActivity(rs.getInt("AcademicActivity"));
                g.setHs_platform(rs.getInt("hs_platform"));
                g.setPaper(rs.getInt("Paper"));
                g.setPatent(rs.getInt("Patent"));
                g.setMaster_id(rs.getString("mid"));
                g.setReport(rs.getInt("report"));
                g.setStandard(rs.getInt("Standard"));
                g.setProjectCertification(rs.getInt("ProjectCertification"));
                g.setTextbook(rs.getInt("textbook"));
                g.setTeachingAssistant(rs.getInt("TeachingAssistant"));
            }
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert con != null;
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return g;
    }
}

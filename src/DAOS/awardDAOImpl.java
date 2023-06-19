package DAOS;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import Entity.award;

public class awardDAOImpl extends DAOBase implements awardDAO{
    private static final String INSERT_SQL = "INSERT INTO award(mid,name,level,grade,ranking,time,materials) VALUES(?,?,?,?,?,?,?)";

    @Override
    public void submitaward(award award) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(award.getMaterials()));
            psmt.setString(1,award.getMaster().getSid());
            psmt.setString(2,award.getName());
            psmt.setInt(3,award.getAward_grade());
            psmt.setInt(4,award.getReward_grade());
            psmt.setInt(5,award.getRanking());
            psmt.setString(6,award.getTime());
            psmt.setBlob(7,in);
            psmt.executeUpdate();
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
    }


    private static final String SELECT_SQL = "SELECT name,level,grade,ranking,time,materials FROM award WHERE mid = ?";

    public ArrayList<award> getAward(String mid){
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1, mid);
            ResultSet rs = psmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            //???ResultSetMetaData?????????е?????
            int count = rsm.getColumnCount();
            ArrayList<award> list = new ArrayList<>();
            int num = 1;
            while (rs.next()) {
                award award = new award();
                String path ="src/award_materials" + num + "--" + mid + ".jpg" ;
                award.setName(rs.getString("name"));
                award.setAward_grade(rs.getInt("grade"));
                award.setRanking(rs.getInt("ranking"));
                award.setTime(rs.getString("time"));
                award.setReward_grade(rs.getInt("level"));
                award.setMaterials(path);
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/award_materials" + num + "--" + mid + ".jpg"  ));
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                list.add(award);
                num ++;
            }
            psmt.close();
            return list;
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
        return null;
    }


    private static final String TUTOR_INSERT_SQL = "UPDATE award set tutor_view = ? where name = ?";

    public void firstsubmit( award award) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setString(1,award.getTutor_view());
            psmt.setString(2,award.getName());
            psmt.executeUpdate();
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
    }


    private static final String LAST_SELECT_SQL = "SELECT name,level,grade,ranking,time,materials,tutor_view FROM award WHERE mid = ?";

    public ArrayList<award> last_getAward(String mid){
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_SELECT_SQL);
            psmt.setString(1, mid);
            ResultSet rs = psmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            //???ResultSetMetaData?????????е?????
            int count = rsm.getColumnCount();
            ArrayList<award> list = new ArrayList<>();
            int num = 1;
            while (rs.next()) {
                award award = new award();
                String path ="src/award_materials" + num + "--" + mid + ".jpg" ;
                award.setName(rs.getString("name"));
                award.setAward_grade(rs.getInt("grade"));
                award.setRanking(rs.getInt("ranking"));
                award.setTime(rs.getString("time"));
                award.setReward_grade(rs.getInt("level"));
                award.setMaterials(path);
                award.setTutor_view(rs.getString("tutor_view"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/award_materials" + num + "--" + mid + ".jpg"  ));
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                list.add(award);
                num ++;
            }
            psmt.close();
            return list;
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
        return null;
    }


    private static final String LAST_INSERT_SQL = "UPDATE award set last_view = ? where name = ?";

    public void lastsubmit( award award) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setString(1,award.getLast_view());
            psmt.setString(2,award.getName());
            psmt.executeUpdate();
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
    }



}

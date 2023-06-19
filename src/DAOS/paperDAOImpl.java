package DAOS;

import Entity.award;
import Entity.paper;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class paperDAOImpl extends DAOBase implements paperDAO{
    private static final String INSERT_SQL = "INSERT INTO thesis(mid,name,periodical,state,time,index_type,Attribution,materials) VALUES(?,?,?,?,?,?,?,?)";

    @Override
    public void submitpaper(paper paper) {
        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(paper.getMaterials()));
            psmt.setString(1,paper.getMaster().getSid());
            psmt.setString(2,paper.getName());
            psmt.setString(3,paper.getPeriodical());
            psmt.setInt(4,paper.getState());
            psmt.setString(5,paper.getTime());
            psmt.setString(6,paper.getIndex_type());
            psmt.setInt(7,paper.getAttribution());
            psmt.setBlob(8,in);
            psmt.executeUpdate();
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private static final String SELECT_SQL = "SELECT name,periodical,state,time,index_type,Attribution,materials,tutor_view FROM thesis WHERE mid = ?";

    public ArrayList<paper> getPaper(String mid) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1, mid);
            ResultSet rs = psmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int count = rsm.getColumnCount();
            ArrayList<paper> list = new ArrayList<paper>();
            int num = 1;
            while (rs.next()) {
                paper paper = new paper();
                String path = "src/paper_materials" + num +  "--" + mid +".jpg" ;
                paper.setName(rs.getString("name"));
                paper.setPeriodical(rs.getString("periodical"));
                paper.setState(rs.getInt("state"));
                paper.setTime(rs.getString("time"));
                paper.setIndex_type(rs.getString("index_type"));
                paper.setAttribution(rs.getInt("state"));
                paper.setMaterials(path);
                paper.setTutor_view(rs.getString("tutor_view"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File( "src/paper_materials" + num +  "--" + mid +".jpg" ));
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                list.add(paper);
                num++;
            }
            psmt.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    private static final String TUTOR_INSERT_SQL = "UPDATE thesis set tutor_view = ? where name = ?";

    public void firstsubmit(paper paper) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setString(1,paper.getTutor_view());
            psmt.setString(2,paper.getName());
            psmt.executeUpdate();
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static final String LAST_INSERT_SQL = "UPDATE thesis set last_view = ? where name = ?";

    public void lastsubmit(paper paper) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setString(1,paper.getLast_view());
            psmt.setString(2,paper.getName());
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

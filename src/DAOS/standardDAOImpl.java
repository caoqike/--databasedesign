package DAOS;

import Entity.paper;
import Entity.standard;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class standardDAOImpl extends DAOBase implements standardDAO {
    private static final String INSERT_SQL = "INSERT INTO standard(mid, name, level, time, materials) VALUES(?,?,?,?,?)";

    @Override
    public void submitstandard(standard standard) {
        Connection con = null;
        try {
            con = getConnection();
            InputStream in = new FileInputStream(new File(standard.getMaterials()));
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1,standard.getMaster().getSid());
            psmt.setString(2, standard.getName());
            psmt.setInt(3, standard.getStandard_level());
            psmt.setString(4, standard.getTime());
            psmt.setBlob(5, in);
            psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final String SELECT_SQL = "SELECT name, level, time, materials , tutor_view FROM standard WHERE mid = ?";

    public ArrayList<standard> getstandard(String mid){
        Connection con = null;
        standard standard = new standard();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,mid);
            ResultSet rs = psmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int count = rsm.getColumnCount();
            ArrayList<standard> list = new ArrayList<standard>();
            int num = 1;
            while(rs.next()){
                paper paper = new paper();
                String path = "src/standard_materials" + num +  "--" + mid +".jpg" ;
                standard.setName(rs.getString("name"));
                standard.setStandard_level(rs.getInt("level"));
                standard.setTime(rs.getString("time"));
                standard.setMaterials(path);
                standard.setTutor_view(rs.getString("tutor_view"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/standard_materials" + num +  "--" + mid +".jpg" ));
                byte[] buf = new byte[1024];
                int len =0;
                while((len = in.read(buf))!= -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                list.add(standard);
                num++;
            }
            psmt.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    private static final String TUTOR_INSERT_SQL = "UPDATE standard set tutor_view = ? where name = ?";

    public void firstsubmit(standard standard) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setString(1,standard.getTutor_view());
            psmt.setString(2,standard.getName());
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

    private static final String LAST_INSERT_SQL = "UPDATE standard set last_view = ? where name = ?";

    public void lastsubmit(standard standard) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setString(1,standard.getLast_view());
            psmt.setString(2,standard.getName());
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


}
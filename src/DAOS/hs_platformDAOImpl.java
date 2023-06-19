package DAOS;

import Entity.hs_platform;
import Entity.patent;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class hs_platformDAOImpl extends DAOBase implements hs_platformDAO{
    private static final String INSERT_SQL = "INSERT INTO hs_platform(mid, name, unit, time, ranking, materials) VALUES(?,?,?,?,?,?)";

    @Override
    public void submiths_platform (hs_platform platform) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(platform.getMaterials()));
            psmt.setString(1, platform.getMaster().getSid());
            psmt.setString(2, platform.getName());
            psmt.setString(3,platform.getUnit());
            psmt.setString(4,platform.getTime());
            psmt.setInt(5, platform.getRanking());
            psmt.setBlob(6,in);
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

    private static final String SELECT_SQL = "SELECT  name, unit, time, ranking, materials , tutor_view FROM hs_platform WHERE mid = ?";

    public ArrayList<hs_platform> geths_platform(String mid){
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,mid);
            ResultSet rs = psmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int count = rsm.getColumnCount();
            ArrayList<hs_platform> list = new ArrayList<hs_platform>();
            int num = 1;
            while(rs.next()){
                hs_platform hs_platform = new hs_platform();
                String path = "src/platform_materials" + num +  "--" + mid +".jpg" ;
                hs_platform.setName(rs.getString("name"));
                hs_platform.setUnit(rs.getString("unit"));
                hs_platform.setTime(rs.getString("time"));
                hs_platform.setRanking(rs.getInt("ranking"));
                hs_platform.setTutor_view(rs.getString("tutor_view"));
                hs_platform.setMaterials(path);
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/platform_materials" + num +  "--" + mid +".jpg"));
                byte[] buf = new byte[1024];
                int len =0;
                while((len = in.read(buf))!= -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                list.add(hs_platform);
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

    private static final String TUTOR_INSERT_SQL = "UPDATE hs_platform set tutor_view = ? where name = ?";

    public void firstsubmit(hs_platform hs_platform) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setString(1,hs_platform.getTutor_view());
            psmt.setString(2,hs_platform.getName());
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


    private static final String LAST_INSERT_SQL = "UPDATE hs_platform set last_view = ? where name = ?";

    public void lastsubmit(hs_platform hs_platform) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setString(1,hs_platform.getLast_view());
            psmt.setString(2,hs_platform.getName());
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
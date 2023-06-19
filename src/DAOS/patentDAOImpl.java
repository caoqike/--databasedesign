package DAOS;

import Entity.patent;
import Entity.patent;
import Entity.report;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class patentDAOImpl extends DAOBase implements patentDAO {
    private static final String INSERT_SQL = "INSERT INTO patent(mid , name,  type,  time, state, ranking, materials, number) VALUES(?,?,?,?,?,?,?,?)";

    @Override
    public void submitpatent(patent patent) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(patent.getMaterials()));
            psmt.setString(1,patent.getMaster().getSid());
            psmt.setString(2, patent.getName());
            psmt.setInt(3, patent.getType());
            psmt.setString(8, patent.getNumber());
            psmt.setString(4, patent.getTime());
            psmt.setString(5, patent.getState());
            psmt.setInt(6, patent.getRanking());
            psmt.setBlob(7, in);
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


    private static final String SELECT_SQL = "SELECT name,  type,  number,  time,  state, ranking, materials, tutor_view FROM patent WHERE mid = ?";

    public ArrayList<patent> getpatent(String mid){
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,mid);
            ResultSet rs = psmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            //ͨ��ResultSetMetaData��ȡ������е�����
            int count = rsm.getColumnCount();
            ArrayList<patent> list = new ArrayList<patent>();
            int num = 1;
            while(rs.next()){
                patent patent = new patent();
                String path = "src/patent_materials" + num +  "--" + mid +".jpg" ;
                patent.setName(rs.getString("name"));
                patent.setType(rs.getInt("type"));
                patent.setNumber(rs.getString("number"));
                patent.setTime(rs.getString("time"));
                patent.setRanking(rs.getInt("ranking"));
                patent.setState(rs.getString("state"));
                patent.setTutor_view(rs.getString("tutor_view"));
                patent.setMaterials(path);
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/patent_materials" + num +  "--" + mid +".jpg"));
                byte[] buf = new byte[1024];
                int len =0;
                while((len = in.read(buf))!= -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                list.add(patent);
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


    private static final String TUTOR_INSERT_SQL = "UPDATE patent set tutor_view = ? where name = ?";

    public void firstsubmit(patent patent) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setString(1,patent.getTutor_view());
            psmt.setString(2,patent.getName());
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

    private static final String LAST_INSERT_SQL = "UPDATE patent set last_view = ? where name = ?";

    public void lastsubmit(patent patent) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setString(1,patent.getLast_view());
            psmt.setString(2,patent.getName());
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
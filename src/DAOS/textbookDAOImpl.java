package DAOS;

import Entity.patent;
import Entity.textbook;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class textbookDAOImpl extends DAOBase implements textbookDAO{
    private static final String INSERT_SQL = "INSERT INTO textbook(mid, name, press,time,ranking,materials) VALUES(?,?,?,?,?,?)";

    @Override
    public void submittextbook(textbook textbook) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(textbook.getMaterials()));
            psmt.setString(1, textbook.getMaster().getSid());
            psmt.setString(2,textbook.getName());
            psmt.setString(3,textbook.getPress());
            psmt.setString(4,textbook.getTime());
            psmt.setInt(5,textbook.getRanking());
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


    private static final String SELECT_SQL = "SELECT name, press,time,ranking,materials,tutor_view FROM textbook WHERE mid = ?";

    public ArrayList<textbook> gettextbook(String mid){
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,mid);
            ResultSet rs = psmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int count = rsm.getColumnCount();
            ArrayList<textbook> list = new ArrayList<textbook>();
            int num = 1;
            while(rs.next()){
                textbook textbook = new textbook();
                String path = "src/textbook_materials" + num +  "--" + mid +".jpg" ;
                textbook.setName(rs.getString("name"));
                textbook.setPress(rs.getString("press"));
                textbook.setTime(rs.getString("time"));
                textbook.setRanking(rs.getInt("ranking"));
                textbook.setTutor_view(rs.getString("tutor_view"));
                textbook.setMaterials(path);
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/textbook_materials" + num +  "--" + mid +".jpg"));
                byte[] buf = new byte[1024];
                int len =0;
                while((len = in.read(buf))!= -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                list.add(textbook);
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

    private static final String TUTOR_INSERT_SQL = "UPDATE textbook set tutor_view = ? where name = ?";

    public void firstsubmit(textbook textbook) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setString(1,textbook.getTutor_view());
            psmt.setString(2,textbook.getName());
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

    private static final String LAST_INSERT_SQL = "UPDATE textbook set last_view = ? where name = ?";

    public void lastsubmit(textbook textbook) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setString(1,textbook.getLast_view());
            psmt.setString(2,textbook.getName());
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

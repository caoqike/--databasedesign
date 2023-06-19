package DAOS;

import Entity.paper;
import Entity.report;
import Entity.standard;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class reportDAOImpl extends DAOBase implements reportDAO{
    private static final String INSERT_SQL = "INSERT INTO report(mid, name, type, unit, time, ranking, materials) VALUES(?,?,?,?,?,?,?)";

    @Override
    public void submitreport(report report) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(report.getMaterials()));
            psmt.setString(1, report.getMaster().getSid());
            psmt.setString(2, report.getName());
            psmt.setInt(3, report.getType());
            psmt.setString(4, report.getUnit());
            psmt.setString(5, report.getTime());
            psmt.setInt(6, report.getRanking());
            psmt.setBlob(7,in);
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

    private static final String SELECT_SQL = "SELECT  name, type, unit, time, ranking, materials , tutor_view FROM report WHERE mid = ?";

    public ArrayList<report> getreport(String mid){
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,mid);
            ResultSet rs = psmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            //ͨ��ResultSetMetaData��ȡ������е�����
            int count = rsm.getColumnCount();
            ArrayList<report> list = new ArrayList<report>();
            int num = 1;
            while(rs.next()){
                report report = new report();
                String path = "src/report_materials" + num +  "--" + mid +".jpg" ;
                report.setName(rs.getString("name"));
                report.setType(rs.getInt("type"));
                report.setUnit(rs.getString("unit"));
                report.setTime(rs.getString("time"));
                report.setRanking(rs.getInt("ranking"));
                report.setMaterials(path);
                report.setTutor_view(rs.getString("tutor_view"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/report_materials" + num +  "--" + mid +".jpg"));
                byte[] buf = new byte[1024];
                int len =0;
                while((len = in.read(buf))!= -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                list.add(report);
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

    private static final String TUTOR_INSERT_SQL = "UPDATE report set tutor_view = ? where name = ?";

    public void firstsubmit(report report) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setString(1,report.getTutor_view());
            psmt.setString(2,report.getName());
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

    private static final String LAST_INSERT_SQL = "UPDATE report set tutor_view = ? where name = ?";

    public void lastsubmit(report report) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setString(1,report.getLast_view());
            psmt.setString(2,report.getName());
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

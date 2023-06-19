
package DAOS;

import Entity.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/10 9:01
 */
public class MasterDAOimp extends DAOBase implements MasterDAO {
    @Override
    public void addMaster(Master master) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into Master values(?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, master.getSid());
            psmt.setString(2, master.getMenid());
            psmt.setString(3, master.getName());

            System.out.println(master.getAddmissiontime().toString());
            psmt.setDate(4, master.getAddmissiontime());
            psmt.setInt(5, master.getStype());
            psmt.executeUpdate();
            psmt.close();

            //设置该学生的登录账号
            //UserManage.saveInfo(master);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMaster(Master master) {

    }

    @Override
    public void updateMaster(Master master) {

    }

    @Override
    public Master getMaster(String id) {
        String sql="select * from Master where mid=?";

        Connection con = null;
       Master master =null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                master = new Master(rs.getString("mid"), rs.getString("name"),rs.getString("menid"),rs.getDate("admissiontime"),rs.getInt("type"));
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
        return master;

    }

    @Override
    public LinkedList<String> Masterlist(String subid) {
        String sql="select distinct choose.mid from Master,choose,Mentor where Master.mid=choose.mid and Master.menid=Mentor.menid and subid=?";

        Connection con = null;
        LinkedList<String> masters= new LinkedList<>();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, subid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                String master = rs.getString("mid");
                masters.add(master);
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
        return masters;
    }

    @Override
    public List<Master> getMasterByMentor(String MentorId){
        Connection con = null;
        List<Master> masterlist = new ArrayList<>();
        try{
            con = getConnection();
            String sql="select * from Master where menid = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,MentorId);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                Master temp = new Master();
                temp.setSid(rs.getString("mid"));
                temp.setName(rs.getString("name"));
                temp.setMenid(rs.getString("menid"));
                temp.setAddmissiontime(rs.getDate("admissiontime"));
                temp.setStype(rs.getInt("type"));
                masterlist.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masterlist;
    }




}
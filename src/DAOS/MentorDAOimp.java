package DAOS;

import Entity.Mentor;
import User.UserManage;
import User.UserType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Administrator
 * @version 1.0
 * @data 2022/12/10 9:19
 */
public class MentorDAOimp extends DAOBase implements MentorDAO{
    @Override
    public void addMentor(Mentor mentor) {
        //��������
        Connection con = null;
        con = getConnection();
        try {
            //����һ����¼
            String sql="insert into Mentor values(?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, mentor.getMenid());
            psmt.setString(2, mentor.getSubid());
            psmt.setString(3, mentor.getName());
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMaster(Mentor mentor) {

    }

    @Override
    public void updateMaster(Mentor mentor) {

    }
    private static final String MENTOR_SELECT_SQL = "SELECT * FROM Mentor WHERE menid=?";
    @Override
    public Mentor getMentor(String mid) {
        Connection con = null;
        Mentor mentor =null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(MENTOR_SELECT_SQL);
            psmt.setString(1, mid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){

                mentor=new Mentor(rs.getString("menid"),rs.getString("subid"),rs.getString("name"));
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return mentor;

    }
}

package DAOS;

import Entity.Teacher;
import Entity.TutorTable;
import User.UserManage;
import User.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author caoqike
 * @date 2022-12-19 19:38:39
 */
public class TutorTableDAOimp extends DAOBase implements  TutorTableDAO{

    @Override
    public void addTutorTable(TutorTable tutorTable) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into TutorTable values(?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, tutorTable.getMid());
            psmt.setString(2, tutorTable.getTid());
            psmt.setString(3, tutorTable.getCouid());
            psmt.setByte(4, tutorTable.getResult());
            psmt.setString(5,tutorTable.getSelfdesc());
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TutorTable getTutoTable(String couid) {
        //构造连接
        Connection con = null;
        con = getConnection();
        TutorTable tutorTable=null;
        try {
            //增加一条记录
            String sql="select * from TutorTable where couid=?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,couid);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                tutorTable=new TutorTable(rs.getString("mid"),
                        rs.getString("tid"),
                        rs.getString("couid"),
                        rs.getByte("result"),
                        rs.getString("selfdes"));
            }
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tutorTable;
    }

    @Override
    public void updateTutorTable(TutorTable tutorTable) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //更新一条记录
            String sql="update Tutortable set result=? where couid=?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setByte(1, tutorTable.getResult());
            psmt.setString(2, tutorTable.getCouid());
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


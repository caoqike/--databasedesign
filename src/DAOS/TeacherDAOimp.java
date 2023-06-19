package DAOS;

import Entity.Mentor;
import Entity.Teacher;
import User.UserManage;
import User.UserType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhuwentao
 * @version 1.0
 * @date 2022/12/9 19:35
 */
public class TeacherDAOimp extends DAOBase implements TeacherDAO{
    @Override
    public void addTeacher(Teacher teacher) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into Teacher values(?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, teacher.getId());
            psmt.setString(2, teacher.getName());
            psmt.executeUpdate();
            psmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(Teacher teacher) {

    }

    @Override
    public void updateTeacher(Teacher teacher) {

    }
    private static final String TEACHER_SELECT_SQL = "SELECT * FROM Teacher WHERE tid=?";
    @Override
    public Teacher getTeacher(String id) {
        Connection con = null;
        Teacher teacher =null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TEACHER_SELECT_SQL);
            psmt.setString(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                teacher=new Teacher(id,rs.getString("name"));
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
        return teacher;
    }
}

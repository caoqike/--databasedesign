package DAOS;
import Entity.AcademicActivity;
import Entity.Mentor;
import Entity.Subject;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caoqike
 * @date 2022-12-09 08:51:54
 */
public class SubjectDAOimp extends DAOBase implements SubjectDAO {

    private static final String SUBJECT_INSERT_SQL = "INSERT INTO Subject(subid,name) VALUES(?,?)";
    @Override
    public void addSubject(Subject subject) {
        Connection con = null;
        con = getConnection();
        try {
            PreparedStatement psmt = con.prepareStatement(SUBJECT_INSERT_SQL);
            psmt.setString(1, subject.subid);
            psmt.setString(2, subject.name);

            psmt.executeUpdate();
            psmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubject(Subject subject) {

    }

    @Override
    public void deleteSubject(Subject subject) {

    }

    @Override
    public Subject getSubject(String id) {
        Subject subject=null;
        Connection con = null;
        con = getConnection();
        try {
            String sql="select * from Subject where subid=?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, id);

            ResultSet rs=psmt.executeQuery();
            String subid="";
            String name="";
            while (rs.next()){
                subid=rs.getString("subid");
                name=rs.getString("name");
            }

            subject=new Subject(subid,name);
            psmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }


}

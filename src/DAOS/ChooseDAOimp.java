package DAOS;

import Entity.Choose;
import Entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author caoqike
 * @date 2022-12-10 15:40:12
 */
public class ChooseDAOimp extends DAOBase implements ChooseDAO{
    @Override
    public void addChoose(Choose choose) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into choose values(?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, choose.getCouseid());
            psmt.setString(2, choose.getMid());
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("添加选课记录成功！");

    }

    @Override
    public void updateChoose(Choose choose) {

    }
    private static final String CHOOSE_DELETE_SQL = "DELETE FROM choose WHERE mid=? and couseid=?";
    @Override
    public void deleteChoose(Choose choose) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(CHOOSE_DELETE_SQL);
            psmt.setString(1, choose.getMid());
            psmt.setString(2, choose.getCouseid());
            psmt.executeUpdate();
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


    }

    @Override
    public Choose getChoose(String couid, String mid) {
        String sql="select * from choose where couseid=? and mid=?";
        Connection conn=null;
        Choose choose=null;
        try{
            conn = getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,couid);
            psmt.setString(2,mid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                choose = new Choose(rs.getString("couseid"),
                        rs.getString("mid")
                );
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert conn != null;
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return choose;

    }


    //课程助教确定后，返回助教学生
    @Override
    public Choose getassistant(String couid) {
        String sql="select * from choose where couseid=?";
        Connection conn=null;
        Choose choose=null;
        try{
            conn = getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,couid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                choose = new Choose(rs.getString("couseid"),
                        rs.getString("mid")
                );
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert conn != null;
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return choose;
    }

    @Override
    //为授课教师返回对应课程的所有学生选课记录
    public LinkedList<Choose> getassistantlist(String couid) {
        String sql="select * from choose where couseid=?";
        Connection conn=null;

        LinkedList<Choose> chooses=new LinkedList<>();
        try{
            conn = getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,couid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Choose choose = new Choose(rs.getString("couseid"),
                        rs.getString("mid")
                );
                chooses.add(choose);
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert conn != null;
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return chooses;
    }

    @Override
    public LinkedList<Choose> getAllChooses() {
        LinkedList<Choose>list=new LinkedList<>();
        String sql="select * from choose";

        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Choose choose = new Choose(rs.getString("couseid"),
                        rs.getString("mid")
                );
                list.add(choose);
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
        return list;
    }


    //当授课教师确定了某个课程的助教后，则choose记录中需要删除其他选择该课的学生记录
    @Override
    public void deleteothermaster(Choose choose) {

//        String sql="delete from choose where couseid=? and mid <>?";
//        Connection conn=null;
//
//
//        try{
//            conn=getConnection();
//            PreparedStatement psmt=conn.prepareStatement(sql);
//            psmt.setString(1,choose.getCouseid());
//            psmt.setString(2,choose.getMid());
//            int row=psmt.executeUpdate();
//            System.out.println("成功删除"+row+"行记录！");
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            try{
//                conn.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void deleteAllChooses(){
        String sql="delete from choose";

        Connection con = null;
        con = getConnection();
        PreparedStatement psmt = null;
        try {
            psmt = con.prepareStatement(sql);
            psmt.executeUpdate();
            psmt.close();
            System.out.println("choose all del");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}


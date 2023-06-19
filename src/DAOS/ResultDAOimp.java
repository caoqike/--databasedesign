package DAOS;

import Entity.Choose;
import Entity.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/15 10:24
 */
public class ResultDAOimp extends DAOBase implements ResultDAO{
    @Override
    public void addResult(Result result) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into result values(?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, result.getCouseid());
            psmt.setString(2, result.getMid());
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("选择该助教成功！");
    }

    @Override
    public void updateResult(Result result) {

    }

    @Override
    public void deleteResult(Result result) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //删除一条记录
            String sql="delete from result where couseid=? and mid=?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, result.getCouseid());
            psmt.setString(2, result.getMid());
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //查找记录
    @Override
    public Result getResult(String couid) {
        Result result=null;
        String sql="select * from result where couseid=?";
        Connection conn=null;
        try{
            conn = getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,couid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                result = new Result(rs.getString("couseid"),
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
        return result;
    }

    @Override
    public Result getassistant(String couid) {
        String sql="select * from result where couseid=?";
        Connection conn=null;
        Result result=null;
        try{
            conn = getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,couid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                result = new Result(rs.getString("couseid"),
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
        return result;
    }

    @Override
    public LinkedList<Result> getmResultList(String mid) {
        LinkedList<Result>list=new LinkedList<>();
        String sql="select * from result where mid=?";
        Connection conn=null;
        conn = getConnection();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1,mid);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()){
                Result result = new Result(rs.getString("couseid"),
                        rs.getString("mid")
                );
                list.add(result);
            }
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    public int cntMasterResult(String mid) {
        String sql="select * from result where mid=?";
        int cnt=0;
        Connection conn=null;
        conn = getConnection();
        PreparedStatement psmt = null;
        try {
            psmt = conn.prepareStatement(sql);

            psmt.setString(1,mid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Result result = new Result(rs.getString("couseid"),
                        rs.getString("mid")
                );
                if (result.getMid().trim().equals(mid.trim())){
                    System.out.println("1");
                    cnt++;
                }
            }

            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return cnt;
    }


}


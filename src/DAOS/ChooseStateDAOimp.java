package DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author caoqike
 * @date 2022-12-19 16:03:11
 */
public class ChooseStateDAOimp extends DAOBase implements ChooseStateDAO{

    @Override
    public void updateChooseState(int state) {
        String sql="update ChooseState set state=? where row=1";
        Connection conn=null;
        conn = getConnection();
        PreparedStatement psmt = null;
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,state);
            if (state==3){//即将第二轮选课，清空第一次的choose表
                //deleteall
                DAOFactory.getChooseDAO().deleteAllChooses();
            }
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getChooseState() {
        String sql="select state from ChooseState where row=1";
        Connection conn=null;
        conn = getConnection();
        PreparedStatement psmt = null;
        try {
            psmt = conn.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                int state=rs.getInt("state");
                return state;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}


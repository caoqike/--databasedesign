package DAOS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author caoqike
 * @date 2022-12-03 20:15:13
 */
public class DAOBase implements DAO{

    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlserver://101.42.0.126:1433; DatabaseName=MasterGraduate;user=cqk;password=123456;TrustServerCertificate=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                conn = DriverManager.getConnection(url);
                //System.out.println("succeed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }

        return conn;
    }

//    private static final Druid druid = new Druid();
//    public Connection getConnection() {
//        try{
//            return Druid.getDataSource().getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
}

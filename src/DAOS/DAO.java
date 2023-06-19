package DAOS;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author caoqike
 * @date 2022-12-03 20:14:53
 */
public interface DAO {
    Connection getConnection();
}

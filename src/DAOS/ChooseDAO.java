package DAOS;

import Entity.Choose;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author caoqike
 * @date 2022-12-10 15:40:48
 */
public interface ChooseDAO {
    void addChoose(Choose choose);
    void updateChoose(Choose choose);
    void deleteChoose(Choose choose);
    Choose getChoose(String couid,String mid);
    Choose getassistant(String couid);
    LinkedList<Choose> getassistantlist(String couid);
    LinkedList<Choose> getAllChooses();
    void deleteothermaster(Choose choose);

    void deleteAllChooses() throws SQLException;
}


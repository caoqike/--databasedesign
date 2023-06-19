package DAOS;

import Entity.Choose;

/**
 * @author caoqike
 * @date 2022-12-19 16:02:54
 */
public interface ChooseStateDAO {
    void updateChooseState(int state);
    int getChooseState();
}


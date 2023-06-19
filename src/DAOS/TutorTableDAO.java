package DAOS;

import Entity.TutorTable;

/**
 * @author caoqike
 * @date 2022-12-19 19:38:23
 */
public interface TutorTableDAO {
    void addTutorTable(TutorTable tutorTable);
    TutorTable getTutoTable(String couid);
    void updateTutorTable(TutorTable tutorTable);


}


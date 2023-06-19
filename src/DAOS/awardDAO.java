package DAOS;

import Entity.award;

import java.util.ArrayList;

public interface awardDAO {
    void submitaward(award award);

    ArrayList<award> getAward(String mid);

    public void firstsubmit( award award);

    public ArrayList<award> last_getAward(String mid);


    public void lastsubmit( award award);
}

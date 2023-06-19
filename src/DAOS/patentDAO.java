package DAOS;

import Entity.patent;

import java.util.ArrayList;

public interface patentDAO {
    void submitpatent(patent patent);

    public ArrayList<patent> getpatent(String mid);

    public void firstsubmit(patent patent);

    public void lastsubmit(patent patent);
}

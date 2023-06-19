package DAOS;

import Entity.paper;

import java.util.ArrayList;

public interface paperDAO {
    void submitpaper(paper paper);

    ArrayList<paper> getPaper(String master_sid);

    public void firstsubmit(paper paper);

    public void lastsubmit(paper paper);
}

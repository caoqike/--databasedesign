package DAOS;

import Entity.standard;

import java.util.ArrayList;

public interface standardDAO {
    void submitstandard(standard standard);

    ArrayList<standard> getstandard(String master_sid);

    public void firstsubmit(standard standard);

    public void lastsubmit(standard standard);
}

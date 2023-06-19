package DAOS;

import Entity.report;

import java.util.ArrayList;

public interface reportDAO {
    void submitreport(report report);
    ArrayList<report> getreport(String master_sid);

    public void firstsubmit(report report);

    public void lastsubmit(report report);
}

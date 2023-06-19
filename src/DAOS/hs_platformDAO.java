package DAOS;

import Entity.hs_platform;

import java.util.ArrayList;

public interface hs_platformDAO {
    void submiths_platform(hs_platform platform);

    ArrayList<hs_platform> geths_platform(String mid);

    public void firstsubmit(hs_platform hs_platform);

    public void lastsubmit(hs_platform hs_platform);
}

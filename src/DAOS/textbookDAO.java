package DAOS;

import Entity.textbook;

import java.util.ArrayList;

public interface textbookDAO {
    void submittextbook(textbook textbook);

    ArrayList<textbook> gettextbook(String master_sid);

    public void firstsubmit(textbook textbook);

    public void lastsubmit(textbook textbook);
}

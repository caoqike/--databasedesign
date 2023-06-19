package DAOS;

import Entity.Choose;
import Entity.Result;

import java.util.LinkedList;

/**
 * @author Administrator
 * @version 1.0
 * @data 2022/12/15 10:21
 */
public interface ResultDAO {

    void addResult(Result result);
    void updateResult(Result result);
    void deleteResult(Result result);
    Result getResult(String couid);
    Result getassistant(String couid);
    LinkedList<Result> getmResultList(String mid);

    int cntMasterResult(String mid);



}


package DAOS;

import Entity.Mentor;

/**
 * @author Administrator
 * @version 1.0
 * @data 2022/12/10 9:20
 */
public interface MentorDAO {
    //����
    public void addMentor(Mentor mentor);
    //ɾ��
    public void deleteMaster(Mentor mentor);
    //�޸�
    public void updateMaster(Mentor mentor);
    //����
    public Mentor getMentor(String id);
}


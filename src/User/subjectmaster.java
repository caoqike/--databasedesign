package User;

import DAOS.DAOFactory;
import Entity.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class subjectmaster extends User implements Menu{

    private SubjectMaster s;
    private List<Mentor> SubMentor;
    public subjectmaster(UserType type, String loadname, String passwd) {
        super(type, loadname, passwd);
        s = DAOFactory.getSubjectMasterDAO().getSubjectMaster(loadname);
        //获取该学科下所有的导师
        SubMentor = DAOFactory.getSubjectMasterDAO().getAllMentor(s);
    }
    final int MAX = 131072;

    public void menu() {
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------学科负责人功能菜单---------------");
            System.out.println("1.确定待选课程列表");
            System.out.println("2.两轮选择后课程助教随机分配");
            System.out.println("3.研究生学术活动审核");
            System.out.println("4.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                //choose = sc.next();
                choose = sc.nextLine();
                switch (choose.trim()) {
                    case "1":
                        makeCourseList();
                        flag = false;
                        break;
                    case "2":
                        random_master();
                        break;
                    case "3":
                        AcademicActivityJudge();
                        flag = false;
                        break;
                    case "4":
                        flag = false;
                        if_continue = false;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入:");
                }
            }

        }


    }



    private void  showCourseList(LinkedList<Course> courses){

        Integer i=1;
        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()){
            Course course=itr.next();

            String stateString=null;
            if (course.getState()==1){
                stateString="助教未确定";
            }
            else if(course.getState()==2)
            {
                stateString="助教已确定";
            }
            else
            {
                stateString="尚无助教需求";
            }
            System.out.println(i.toString()+"\t"+course.getCouseid()+"\t"+course.getName()+"\t"+course.getHours()+"\t"+course.getApplications()+"\t"+stateString);
            i++;
        }
    }

    private void makeCourseList(){
        //查看自己学科的所有课程列表
        LinkedList<Course> courses= DAOFactory.getCourseDAO().getAllCourses(s.getSubid().trim());
        Iterator<Course> itr = courses.iterator();
        LinkedList<Course> needTutorCourses=new LinkedList<>();
        LinkedList<Course> noChooseCourse=new LinkedList<>();
        Integer i=1;//行号

        //遍历一次，把0 与12 状态分别放到不同的课程列表
        while (itr.hasNext()){
            Course course=itr.next();

            //说明已经添加到了助教课程列表
            if (course.getState()!=0){  //如果是0：无需助教  1：需要助教，且助教尚未确定  2：需要助教，且助教已确定
                needTutorCourses.add(course);
                continue;
            }
            // System.out.println(i.toString()+"\t"+course.getCouseid()+"\t"+course.getName()+"\t"+course.getHours()+"\t"+course.getApplications()+"\t������������");
            noChooseCourse.add(course);
            i++;
        }


        System.out.println("助教课程列表最新情况如下：");
        if(needTutorCourses.size()==0){
            System.out.println("助教课程列表为空！");
        }
        else
        {
            showCourseList(needTutorCourses);
        }
        while (true){
            System.out.println("待加入助教课程列表的课程信息如下：");
            showCourseList(noChooseCourse);
            System.out.println("请选择对助教课程列表的操作");
            System.out.println("1.增加记录");
            System.out.println("2.删除记录");

            System.out.println("3.返回上级菜单");
            int choose=0;
            //System.out.println("");
            Scanner sc=new Scanner(System.in);
            choose=sc.nextInt();

            switch (choose){
                case 1:
                    System.out.println("请输入要加入列表课程的行号");
                    int row=sc.nextInt();

                    Course tmp=noChooseCourse.get(row-1);
                    noChooseCourse.remove(tmp);
                    tmp.setState(1);
                    needTutorCourses.add(tmp);

                    DAOFactory.getCourseDAO().changeCourseState(tmp.getCouseid(), 1);
                    System.out.println("更新后的助教列表如下");
                    showCourseList(needTutorCourses);
                    break;
                case 2:
                    System.out.println("请输入要删除列表课程的行号");
                    break;
                case 3:
                    return;
            }
        }
    }

    private void random_master(){
        while(true){
            //查找该学科下需要助教且还没有助教的课程信息
            LinkedList<Course> courses=DAOFactory.getCourseDAO().getStateCourses(s.getSubid(),1);

            System.out.println("该学科下需要助教且还没有助教的课程信息如下");
            System.out.println("---------------");
            showCourseList(courses);

            //查找该学科下第二轮选择的同学学号
            LinkedList<String> mids=DAOFactory.getMasterDAO().Masterlist(s.getSubid().trim());

            LinkedList<String>tmps=new LinkedList<>();


            Iterator<String> itr=mids.iterator();
            while (itr.hasNext()){
                tmps.add(itr.next());
            }
            //筛选落选同学学号
            for(String tmp:tmps){
                if(DAOFactory.getResultDAO().cntMasterResult(tmp)!=0){
                    mids.remove(tmp);
                }
            }
            //获取学生信息
            LinkedList<Master> masters= new LinkedList<>();
            for(String mid:mids){
                masters.add(DAOFactory.getMasterDAO().getMaster(mid));
            }
            System.out.println();
            System.out.println("该学科下第二轮仍落选学生信息如下");
            System.out.println("---------------");
            System.out.println("学号\t姓名");
            for(Master master:masters){
                System.out.println(master.getSid().trim()+"\t"+
                        master.getName().trim());
            }

            System.out.println("请选择要分配助教的课程号");
            Scanner scanner=new Scanner(System.in);
            String cid=scanner.next();

            while(true){
                int flag=0;
                for(Course c:courses){
                    if(c.getCouseid().trim().equals(cid.trim())){
                        flag=1;
                        break;
                    }
                }
                if(flag!=1){
                    System.out.println("课程号输入错误，请重新输入!");
                }
                else{
                    break;
                }
            }

            System.out.println("请输入分配的助教学生学号：");
            String mid=scanner.next();
            while(true){
                int flag=0;
                for(Master m:masters){
                    if(m.getSid().trim().equals(mid.trim())){
                        flag=1;
                        break;
                    }
                }
                if(flag!=1){
                    System.out.println("学号输入错误，请重新输入!");
                }
                else{
                    break;
                }
            }
            System.out.println("分配助教成功！");
            DAOFactory.getCourseDAO().changeCourseState(cid,2);
            System.out.println("更新课程状态成功！");
            Result result=new Result(cid,mid);
            DAOFactory.getResultDAO().addResult(result);
            System.out.println("更新result表成功！");
            System.out.println("请选择是否要继续进行随机分配? 1.是 2.否");
            int n=scanner.nextInt();
            while(true){
                if(n==2){
                    return;
                }
                else if(n==1){
                    break;
                }
                else {
                    System.out.println("选择输入错误，请重新输入!");
                }
                n=scanner.nextInt();
            }
        }
    }

    //终审学术交流活动
    private void AcademicActivityJudge(){
        System.out.println("------研究生学术交流活动二次审核------");
        Iterator<Mentor> mentorIterator = SubMentor.listIterator();
        int count = 0;
        String [] logActivityId = new String[MAX];
        //遍历该学科下所有导师
        while(mentorIterator.hasNext()){
            Mentor mentortemp = mentorIterator.next();
            //找到该导师下所有学生
            List<Master> masterlist = DAOFactory.getMasterDAO().getMasterByMentor(mentortemp.getMenid());
            Iterator<Master> iterator = masterlist.iterator();
            while(iterator.hasNext()){
                Master temp = iterator.next();
                //System.out.println(temp.toString());
                List<AcademicActivity> a = DAOFactory.getAcademicActivityDAO().getAcademicActivity(temp.getSid());
                Iterator<AcademicActivity> iter = a.listIterator();
                while(iter.hasNext()){
                    AcademicActivity atemp = iter.next();
                    if(atemp.isTutor_view() && atemp.getImage_type()!=null && !atemp.isMaster_view()){
                        //System.out.println(atemp.isMaster_view());
                        System.out.print("\t编号："+(count+1)+"\t学生号："+temp.getSid());
                        System.out.println('\t'+atemp.tutorToString());
                        //new ShowPicture(atemp.getCertificate());
                        logActivityId[count] = atemp.getActivity_id();
                        count++;
                        //System.out.println(count);
                    }
                }
            }
        }
        if(count == 0)
            System.out.println("暂无学术交流活动记录！");
        else{
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要审核的学术交流活动编号：");
            int choice = sc.nextInt();
            if(choice>0 && choice <= count){
                AcademicActivity atemp = DAOFactory.getAcademicActivityDAO().getAcademicActivitybyId(logActivityId[choice-1]);
                new ShowPicture(atemp.getCertificate());
                System.out.println("请选择：\nY.通过 \nF.不通过 \n其它任意键退出审核");
                String c = sc.next();
                if(c.trim().equals("Y") || c.trim().equals("y")){
                    DAOFactory.getAcademicActivityDAO().updateSubjectMasterView(true,logActivityId[choice-1]);
                    //补充，在毕业认定表相关项中增加次数
                    DAOFactory.getGraduationRequirementsDAO().AddAcademicActivityTimes(atemp.getMaster_id());
                    System.out.println("记录（ActivityId:"+logActivityId[choice-1]+")已通过终审！");
                }
                else if(c.trim().equals("F") || c.trim().equals("f")){
                    DAOFactory.getAcademicActivityDAO().deleteAcademicActivity(logActivityId[choice-1]);
                    System.out.println("记录（ActivityId:"+logActivityId[choice-1]+")已判定为不合格！");
                }else{
                    System.out.println("记录未做修改！");
                }
            }else{
                System.out.println("您输入的编号有误！");
            }
        }

    }



}

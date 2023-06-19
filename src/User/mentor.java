package User;

import DAOS.AcademicActivityDAOimp;
import DAOS.DAOFactory;
import Entity.*;

import java.util.*;

import java.util.Scanner;

public class mentor extends User implements Menu{
    final int MAX = 32767;
    private Mentor m;
    private List<Master> masterlist;
    public mentor(UserType type, String loadname, String passwd) {
        super(type, loadname, passwd);
        System.out.println(loadname);
        m = DAOFactory.getMentorDAO().getMentor(loadname);
        masterlist = DAOFactory.getMasterDAO().getMasterByMentor(m.getMenid());
    }

    //展示该导师所有研究生信息。
    private void ShowStudent(){
        System.out.println(m.getMenid());
        //List<Master> masterlist = DAOFactory.getMasterDAO().getMasterByMentor(m.getMenid());
        if(masterlist.size() == 0){
            System.out.println("未查找到研究生！");
        }
        else{
            System.out.println("研究生编号\t研究生姓名\t研究生入学年份\t类型");
            Iterator<Master> iterator = masterlist.iterator();
            while(iterator.hasNext()){
                Master temp = iterator.next();
                String type;
                if(temp.getStype() == 1){
                    type = "硕士研究生";
                }
                else{
                    type = "博士研究生";
                }
                System.out.println(temp.getSid()+'\t'+temp.getName()+'\t'+temp.getAddmissiontime()+'\t'+type);
            }
        }

    }

    private void Academicmodule(){
        System.out.println("--------学生学术交流活动初审认定--------");
        //将该导师下面所有研究生的所有成果进行展示。
        Iterator<Master> iterator = masterlist.iterator();
        //编号
        int count = 0;
        //按照编号将学术交流活动主键存储在列表中
        String [] logActivityId = new String[MAX];
        while(iterator.hasNext()){
            Master temp = iterator.next();
            System.out.println("\t学号："+temp.getSid()+"\t学生姓名："+temp.getName());
            List<AcademicActivity> a = DAOFactory.getAcademicActivityDAO().getAcademicActivity(temp.getSid());
            Iterator<AcademicActivity> iter = a.listIterator();
            while(iter.hasNext()){
                AcademicActivity atemp = iter.next();
                if(!atemp.isTutor_view()){
                    //System.out.println(atemp.isMaster_view());
                    System.out.print("\t编号："+(count+1));
                    System.out.println('\t'+atemp.tutorToString());
                    logActivityId[count] = atemp.getActivity_id();
                    count++;
                    //System.out.println(count);
                }
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要审核的学术交流活动编号：");
        int choice = sc.nextInt();
        if(choice>0 && choice <= count){
            System.out.println("请选择：\nY.通过 \nF.不通过 \n其它任意键退出审核");
            String c = sc.next();
            if(c.trim().equals("Y") || c.trim().equals("y")){
                DAOFactory.getAcademicActivityDAO().updateTutorView(true,logActivityId[choice-1]);
                System.out.println("记录（ActivityId:"+logActivityId[choice-1]+")已通过初审！");
            }
            else if(c.trim().equals("F") || c.trim().equals("f")){
                DAOFactory.getAcademicActivityDAO().deleteAcademicActivity(logActivityId[choice-1]);
                System.out.println("记录（ActivityId:"+logActivityId[choice-1]+")已判定为不合格！");
            }
        }else{
            System.out.println("您输入的编号有误！");
        }
    }

    private static void Achievemodule(){
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------初审学生成果---------------");
            System.out.println("1.初审论文成果");
            System.out.println("2.初审奖励成果");
            System.out.println("3.初审标准成果");
            System.out.println("4.初审报告成果");
            System.out.println("5.初审专利成果");
            System.out.println("6.初审软硬件平台成果");
            System.out.println("7.初审教材成果");
            System.out.println("8.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                choose = sc.next();
                switch (choose) {
                    case "1":
                        ViewPaper();
                        flag = false;
                        break;

                    case "2":
                        ViewAward();
                        flag = false;
                        break;
                    case "3":
                        ViewStandard();
                        flag = false;
                        break;
                    case "4":
                        ViewReport();
                        flag = false;
                        break;
                    case "5":
                        ViewPatent();
                        flag = false;
                        break;
                    case "6":
                        ViewPlatform();
                        flag = false;
                        break;
                    case "7":
                        ViewTextbook();
                        flag = false;
                        break;
                    case "8":
                        flag = false;
                        if_continue = false;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入:");
                }
            }

        }


    }


    public void menu() {
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------研究生功能菜单---------------");
            System.out.println("1.查看学生基本信息");
            System.out.println("2.学术交流活动认证模块");
            System.out.println("3.成果初审模块");
            System.out.println("4.研究生项目认定");
            System.out.println("5.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                choose = sc.next();
                switch (choose) {
                    case "1":
                        ShowStudent();
                        flag = false;
                        break;

                    case "2":
                        Academicmodule();
                        flag = false;
                        break;
                    case "3":
                        Achievemodule();
                        flag = false;
                        break;
                    case "4":
                        projectMenu();
                        flag = false;
                        break;
                    case "5":
                        flag = false;
                        if_continue = false;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入:");
                }
            }

        }
    }


    private static void ViewAward() {
        Scanner sc=new Scanner(System.in);
        System.out.println("------------初审奖励成果-------------");
        System.out.println("请输入要审核奖励的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<award> awardlist;

        awardlist = DAOFactory.getawardDAO().getAward(master.getSid());

        System.out.println("学生" + mid + "所获奖励如下");

        System.out.printf("%-10s  %-10s  %-10s  %-10s  %-10s  %-10s \n","name","level","grade","ranking","time","materials");

        for (int i = 0; i < awardlist.size(); i++){
            award award = new award();
            award = awardlist.get(i);
            System.out.printf("%-10s  %-10d  %-10d  %-10d  %-10s  %-10s \n", award.getName() , award.getReward_grade() ,  award.getAward_grade() ,
                    award.getRanking() , award.getTime() , award.getMaterials());
        }

        System.out.println("请对该学生的奖励成果进行初审 :");

        for (int i = 0; i < awardlist.size(); i++){
            award award = new award();
            award = awardlist.get(i);
            int num = i+1;
            System.out.println("请输入第" + num +"个奖励初审结果:(pass或no_pass)");
            String res = sc.next();
            award.setTutor_view(res);
            DAOFactory.getawardDAO().firstsubmit(award);
        }
        System.out.println("审核完成，辛苦老师！");
    }

    private static void ViewPaper() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------初审论文成果-------------");
        System.out.println("请输入要审核论文的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<paper> paperlist;

        paperlist = DAOFactory.getpaperDAO().getPaper(master.getSid());

        System.out.println("学生" + mid + "所有论文如下");


        System.out.printf("%-10s  %-10s  %-10s  %-10s  %-10s %-10s %-10s \n","name","periodical","state","time","index_type","Attribution","materials");

        for (int i = 0; i < paperlist.size(); i++){
            paper paper= new paper();
            paper = paperlist.get(i);
            System.out.printf("%-10s  %-10s  %-10d  %-10s  %-10s  %-10d %-10s \n", paper.getName(),paper.getPeriodical(),paper.getState(),paper.getTime()
            ,paper.getIndex_type(),paper.getAttribution(),paper.getMaterials());
        }

        System.out.println("请对该学生的奖励成果进行初审 :");

        for (int i = 0; i < paperlist.size(); i++){
            paper paper = new paper();
            paper = paperlist.get(i);
            int num = i+1;
            System.out.println("请输入第" + num +"个论文初审结果:(pass或no_pass)");
            String res = sc.next();
            paper.setTutor_view(res);
            DAOFactory.getpaperDAO().firstsubmit(paper);
        }
        System.out.println("审核完成，辛苦老师！");
    }

    private static void ViewStandard() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------初审标准成果-------------");
        System.out.println("请输入要审核标准的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<standard> standardlist;


        standardlist = DAOFactory.getstandardDAO().getstandard(master.getSid());

        System.out.println("学生" + mid + "所有标准如下");


        System.out.printf("%-10s  %-10s  %-10s  %-10s \n","name","level","time","materials");

        for (int i = 0; i < standardlist.size(); i++){
            standard standard= new standard();
            standard = standardlist.get(i);
            System.out.printf("%-10s  %-10d  %-10s  %-10s\n", standard.getName(),standard.getStandard_level(),
                    standard.getTime(),standard.getMaterials());
        }

        System.out.println("请对该学生的标准成果进行初审 :");

        for (int i = 0; i < standardlist.size(); i++){
            standard standard = new standard();
            standard = standardlist.get(i);
            int num = i+1;
            System.out.println("请输入第" + num +"个标准初审结果:(pass或no_pass)");
            String res = sc.next();
            standard.setTutor_view(res);
            DAOFactory.getstandardDAO().firstsubmit(standard);
        }

        System.out.println("审核完成，辛苦老师！");

    }

    private static void ViewReport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------初审报告成果-------------");
        System.out.println("请输入要审核报告的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<report> reportlist;

        reportlist = DAOFactory.getreportDAO().getreport(master.getSid());

        System.out.println("学生" + mid + "所有报告如下");

        System.out.printf("%-10s  %-10s  %-10s  %-10s  %-10s %-10s \n","name","type","unit","time","ranking","materials");

        for (int i = 0; i < reportlist.size(); i++){
            report report= new report();
            report = reportlist.get(i);
            System.out.printf("%-10s  %-10s %-10s  %-10s  %-10d %-10s \n", report.getName(),report.getType(),report.getUnit(),
                    report.getTime(),report.getRanking(),report.getMaterials());
        }

        System.out.println("请对该学生的报告成果进行初审 :");

        for (int i = 0; i < reportlist.size(); i++){
            report report = new report();
            report = reportlist.get(i);
            int num = i+1;
            System.out.println("请输入第" + num +"个报告初审结果:(pass或no_pass)");
            String res = sc.next();
            report.setTutor_view(res);
            DAOFactory.getreportDAO().firstsubmit(report);
        }
        System.out.println("审核完成，辛苦老师！");
    }


    private static void ViewPatent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------初审专利成果-------------");
        System.out.println("请输入要审核专利的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<patent> patentlist;

        patentlist = DAOFactory.getpatentDAO().getpatent(master.getSid());

        System.out.println("学生" + mid + "所有专利如下");


        System.out.printf("%-10s  %-10s  %-10s %-10s %-10s  %-10s %-10s \n","name","type","number","time","state","ranking","materials");

        for (int i = 0; i < patentlist.size(); i++){
            patent patent= new patent();
            patent = patentlist.get(i);
            System.out.printf("%-10s  %-10d %-10s  %-10s  %-10s  %-10d %-10s \n", patent.getName(),patent.getType(),patent.getNumber(),
                    patent.getTime(),patent.getState(),patent.getRanking(),patent.getMaterials());
        }

        System.out.println("请对该学生的专利成果进行初审 :");

        for (int i = 0; i < patentlist.size(); i++){
            patent patent = new patent();
            patent = patentlist.get(i);
            int num = i+1;
            System.out.println("请输入第" + num +"个专利初审结果:(pass或no_pass)");
            String res = sc.next();
            patent.setTutor_view(res);
            DAOFactory.getpatentDAO().firstsubmit(patent);
        }
        System.out.println("审核完成，辛苦老师！");
    }


    private static void ViewPlatform() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------初审软硬件平台成果-------------");
        System.out.println("请输入要审核软硬件平台的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<hs_platform> platformlist;

        platformlist = DAOFactory.getplatformDAO().geths_platform(master.getSid());

        System.out.println("学生" + mid + "所有软硬件平台如下");


        System.out.printf("%-10s  %-10s  %-10s %-10s %-10s  \n","name","unit","time","ranking","materials");

        for (int i = 0; i < platformlist.size(); i++){
            hs_platform platform= new hs_platform();
            platform = platformlist.get(i);
            System.out.printf("%-10s %-10s  %-10s  %-10d %-10s \n", platform.getName(),platform.getUnit(),
                    platform.getTime(),platform.getRanking(),platform.getMaterials());
        }

        System.out.println("请对该学生的软硬件平台成果进行初审 :");

        for (int i = 0; i < platformlist.size(); i++){
            hs_platform platform = new hs_platform();
            platform = platformlist.get(i);
            int num = i+1;
            System.out.println("请输入第" + num +"个软硬件平台初审结果:(pass或no_pass)");
            String res = sc.next();
            platform.setTutor_view(res);
            DAOFactory.getplatformDAO().firstsubmit(platform);
        }
        System.out.println("审核完成，辛苦老师！");
    }


    private static void ViewTextbook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------初审教材成果-------------");
        System.out.println("请输入要审核教材的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<textbook> textbooklist;

        textbooklist = DAOFactory.gettextbookDAO().gettextbook(master.getSid());

        System.out.println("学生" + mid + "所有教材如下");


        System.out.printf("%-10s  %-10s  %-10s %-10s %-10s  \n","name","press","time","ranking","materials");

        for (int i = 0; i < textbooklist.size(); i++){
            textbook textbook= new textbook();
            textbook = textbooklist.get(i);
            System.out.printf("%-10s  %-10s %-10s  %-10d %-10s \n", textbook.getName(),textbook.getPress(),
                    textbook.getTime(),textbook.getRanking(),textbook.getMaterials());
        }

        System.out.println("请对该学生的教材成果进行初审 :");

        for (int i = 0; i < textbooklist.size(); i++){
            textbook textbook = new textbook();
            textbook = textbooklist.get(i);
            int num = i+1;
            System.out.println("请输入第" + num +"个教材初审结果:(pass或no_pass)");
            String res = sc.next();
            textbook.setTutor_view(res);
            DAOFactory.gettextbookDAO().firstsubmit(textbook);
        }
        System.out.println("审核完成，辛苦老师！");

    }

    //zxy
    private void projectMenu(){
        while (true){
            System.out.println("---------研究生项目认定表相关功能菜单---------");

            System.out.println("1.填写研究生项目认定表经费");
            System.out.println("2.研究生项目认定表导师认定");
            System.out.println("3.研究生项目认定表项目负责人认定");
            System.out.println("4.返回上级菜单");

            System.out.println("请选择：");
            Scanner sc=new Scanner(System.in);
            int choose=sc.nextInt();
            switch (choose){
                case 1:
                    fillExpenditure();
                    break;

                case 2:
                    fillMentorSign();
                    break;

                case 3:
                    fillManagerSign();
                    break;

                case 4:
                    System.out.println("感谢使用！");
                    return;
            }

        }
    }


    //zxy
    private void fillExpenditure(){
        //先查询自己名下所有项目
        LinkedList<Project>list = null;
        list = DAOFactory.getProjectDAO().getMentorProject(m.getMenid());


        if(list.size() == 0){
            System.out.println("您没有指导的项目");
        }else {
            System.out.println("您指导的项目编号：");
            for(int i = 0; i<list.size(); i++){
                System.out.println(list.get(i).getProid());
            }
            System.out.println("请选择要填报折合经费的项目编号：");
            Scanner sc=new Scanner(System.in);
            String projid=sc.next();
            //查询该项目的所有证明
            LinkedList<ProjectCerification> list1 = null;
            list1 = DAOFactory.getProjectCertificationDAO().getGivenProjectCertification(projid);
            if(list1.size() == 0){
                System.out.println("该项目没有需要填写的认定表");
            }else {
                System.out.println("认定表编号：");
                for(int j = 0; j<list1.size(); j++){
                    System.out.println(list1.get(j).getCertid());
                }
                System.out.println("请选择要填报折合经费的认定表编号：");
                String certid = sc.next();
                ProjectCerification projectcertification = DAOFactory.getProjectCertificationDAO().getProjectCertification(certid);
                System.out.println(projectcertification.toString());
                if(projectcertification.getMentorsign()==1||projectcertification.getManagersign() == 1){
                    System.out.println("该认定表已经被认定，无法修改折合经费！");
                    return;
                }

                //填写折合经费
                System.out.println("请填写承担工作折合经费（单位：万元）：");
                float expenditure = sc.nextFloat();
                while (expenditure<6){
                    System.out.println("承担工作折合经费需要大于6万元，请重新填写：");
                    expenditure = sc.nextFloat();
                }
                projectcertification.setExpenditure(expenditure);
                DAOFactory.getProjectCertificationDAO().updateProjectCertification(projectcertification);
                System.out.println("承担工作折合经费填写成功！");

            }
        }
    }

    //zxy
    // 导师签字项目认定表
    private void fillMentorSign(){
        //先查询自己名下所有项目
        LinkedList<Project>list = null;
        list = DAOFactory.getProjectDAO().getMentorProject(m.getMenid());

        if(list.size() == 0){
            System.out.println("您没有指导的项目");
        }else {
            System.out.println("您指导的项目编号：");
            for(int i = 0; i<list.size(); i++){
                System.out.println(list.get(i).getProid());
            }
            System.out.println("请选择要认定折合经费的项目编号：");
            Scanner sc=new Scanner(System.in);
            String projid=sc.next();
            //查询该项目的所有证明
            LinkedList<ProjectCerification> list1 = null;
            list1 = DAOFactory.getProjectCertificationDAO().getGivenProjectCertification(projid);
            if(list1.size() == 0){
                System.out.println("该项目没有需要填写的认定表");
            }else {
                System.out.println("认定表编号：");
                for(int j = 0; j<list1.size(); j++){
                    System.out.println(list1.get(j).getCertid());
                }
                System.out.println("请选择要认定折合经费的认定表编号：");
                String certid = sc.next();

                //查看该项目是否已认定
                ProjectCerification projectcertification = DAOFactory.getProjectCertificationDAO().getProjectCertification(certid);
                int sign = projectcertification.getMentorsign();
                float expenditure = projectcertification.getExpenditure();
                if(expenditure <= 0){
                    System.out.println("该认定表未填写折合经费，不允许认定！");
                    return;
                }
                if(sign == 1){
                    System.out.println("该认定表已经认定，不允许重复认定！");
                    return;
                }
                System.out.println(projectcertification.toString());

                while (true){
                    System.out.println("请问是否认定项目折合经费？1.认定 2.不认定");
                    int s = sc.nextInt();
                    switch (s){
                        case 1:
                            //填写认定
                            projectcertification.setMentorsign(1);
                            DAOFactory.getProjectCertificationDAO().updateProjectCertification(projectcertification);
                            //补充：如果两个签字都确定，则在研究生毕业要求表的相关项加一
                            if(projectcertification.getManagersign()==1){
                                DAOFactory.getGraduationRequirementsDAO().AddProjectCertificationTimes(projectcertification.getSid());
                            }
                            System.out.println("承担工作折合经费认定成功！");
                            return;
                        case 2:
                            System.out.println("承担工作折合经费认定取消！");
                            return;
                    }
                }
            }
        }
    }

    //zxy
    // 项目负责人签字
    private void fillManagerSign(){
        //先查询自己名下所有项目
        LinkedList<Project>list = null;
        list = DAOFactory.getProjectDAO().getManagerProject(m.getMenid());

        if(list.size() == 0){
            System.out.println("您没有指导的项目");
        }else {
            System.out.println("您指导的项目编号：");
            for(int i = 0; i<list.size(); i++){
                System.out.println(list.get(i).getProid());
            }
            System.out.println("请选择要认定折合经费的项目编号：");
            Scanner sc=new Scanner(System.in);
            String projid=sc.next();
            //查询该项目的所有证明
            LinkedList<ProjectCerification> list1 = null;
            list1 = DAOFactory.getProjectCertificationDAO().getGivenProjectCertification(projid);
            if(list1.size() == 0){
                System.out.println("该项目没有需要填写的认定表");
            }else {
                System.out.println("认定表编号：");
                for(int j = 0; j<list1.size(); j++){
                    System.out.println(list1.get(j).getCertid());
                }
                System.out.println("请选择要认定折合经费的认定表编号：");
                String certid = sc.next();

                //查看该项目是否已认定
                ProjectCerification projectcertification = DAOFactory.getProjectCertificationDAO().getProjectCertification(certid);
                int sign = projectcertification.getManagersign();
                float expenditure = projectcertification.getExpenditure();
                if(expenditure <= 0){
                    System.out.println("该认定表未填写折合经费，不允许认定！");
                    return;
                }
                if(sign == 1){
                    System.out.println("该认定表已经认定，不允许重复认定！");
                    return;
                }
                System.out.println(projectcertification.toString());

                while (true){
                    System.out.println("请问是否认定承担工作折合经费？1.认定 2.不认定");
                    int s = sc.nextInt();
                    switch (s){
                        case 1:
                            //填写认定
                            projectcertification.setManagersign(1);
                            DAOFactory.getProjectCertificationDAO().updateProjectCertification(projectcertification);
                            //补充：如果两个签字都确定，则在研究生毕业要求表的相关项加一
                            if(projectcertification.getMentorsign()==1){
                                DAOFactory.getGraduationRequirementsDAO().AddProjectCertificationTimes(projectcertification.getSid());
                            }
                            System.out.println("承担工作折合经费认定成功！");
                            return;
                        case 2:
                            System.out.println("承担工作折合经费认定取消！");
                            return;
                    }
                }
            }
        }
    }



}

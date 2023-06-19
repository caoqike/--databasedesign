package User;

import DAOS.DAOFactory;
import Entity.*;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class master extends User implements Menu{

    private Master m ;

    public master(UserType type, String name, String passwd) {
        super(type,name,passwd);
        m = DAOFactory.getMasterDAO().getMaster(name);
    }

    //
//    public master(UserType type, String loadname, String passwd) {
//        super(type, loadname, passwd);
//    }


    private void  showCourseList(LinkedList<Course> courses, LinkedList<Choose> myChooses){

        Integer i=1;//行号
        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()){
            Course course=itr.next();

            String stateString="未选，可选";

            Iterator<Choose> itr1 = myChooses.iterator();

            while (itr1.hasNext()){
                Choose choose= itr1.next();
                if(choose.getCouseid().equals(course.getCouseid())){
                    stateString="已选，不可重复选";

                    break;
                }
            }


            System.out.println(i.toString()+"\t"+course.getCouseid()+"\t"+course.getName()+"\t"+course.getHours()+"\t"+course.getApplications()+"\t"+stateString);
            i++;
        }
    }


    private void tutorMenu(){
        while(true)
        {
            System.out.println("---------助教课程子模块----------");
            System.out.println("1.选课");
            System.out.println("2.退课");
            System.out.println("3.返回上级菜单");
            int choose;
            Scanner sc=new Scanner(System.in);
            choose=sc.nextInt();
            switch (choose){
                case 1:
                    chooseCourse();
                    break;
                case 2:
                    deleteCourse();
                    break;
                case 3:
                    return;
            }
        }

    }

    public void deleteCourse(){
        System.out.println("----------------------退课-----------------------");

        LinkedList<Choose> allChooses = DAOFactory.getChooseDAO().getAllChooses();
        Iterator<Choose> itr = allChooses.iterator();
        LinkedList<Choose> myChooses=new LinkedList<>();
        while (itr.hasNext()) {

            Choose choose=itr.next();
            if (choose.getMid().trim().equals(m.getSid().trim())){
                myChooses.add(choose);
            }
        }
        if(myChooses.size()==0){
            System.out.println("本人无选课记录，无法退课！");
            return;
        }
        //展现本人的已选课程列表 1 或 2 个
        System.out.println("本人已选课程如下：");
        Iterator<Choose> itr11 = myChooses.iterator();
        System.out.println("行号\t课程号\t课程名\t");
        int i=1;
        while (itr11.hasNext()) {

            Choose tmp=itr11.next();
            Course course=DAOFactory.getCourseDAO().getCourse(tmp.getCouseid());
            System.out.println(i+"\t"+course.getCouseid().trim()+"\t"+course.getName());
            i+=1;
        }

        System.out.println("请输入要退选的行号：");
        Scanner sc=new Scanner(System.in);
        int row=sc.nextInt();
        Choose choose=myChooses.get(row-1);

        DAOFactory.getChooseDAO().deleteChoose(choose);
        System.out.println("删除选课记录成功！");

    }

    public void chooseCourse(){
        //判断是否可以选课 1或3才可以
        if(DAOFactory.getChooseStateDAO().getChooseState()==2||DAOFactory.getChooseStateDAO().getChooseState()==4){
            System.out.println("当前阶段是教师选助教阶段，学生无法选课程！");
            return;
        }
        //是第二次选课 判断这个学生能不能第二次选课
        if(DAOFactory.getChooseStateDAO().getChooseState()==3){
            if(DAOFactory.getResultDAO().cntMasterResult(m.getSid())!=0){
                System.out.println("您在第一次选课中没有落选，无需进行第二轮选课！");
                return;
            }
        }

        System.out.println("----------------------选课-----------------------");
        String subid= DAOFactory.getMentorDAO().getMentor(m.getMenid()).getSubid();

        //查看自己学科的特定状态的课程列表
        LinkedList<Course> courses= DAOFactory.getCourseDAO().getStateCourses(subid,1);

        LinkedList<Choose> myChooses=new LinkedList<>();


        LinkedList<Choose> allChooses = DAOFactory.getChooseDAO().getAllChooses();

        // System.out.println(allChooses.size());

        //查看所有选课记录当中有几个是当前硕士选的
        //默认设成2
        Integer volAvailabe=2;
        Integer volAlready=0;
        Iterator<Choose> itr = allChooses.iterator();
        while (itr.hasNext()) {
            Choose choose=itr.next();
            System.out.println("---");

            if (choose.getMid().trim().equals(m.getSid().trim())){

                myChooses.add(choose);
                volAvailabe--;
                volAlready++;
            }
        }

        System.out.println("助教课程列表如下：");
        showCourseList(courses,myChooses);

        //展现本人的已选课程列表 1 或 2 个
        System.out.println("本人已选课程如下：");
        Iterator<Choose> itr11 = myChooses.iterator();
        System.out.println("课程号\t课程名\t");
        while (itr11.hasNext()) {

            Choose tmp=itr11.next();
            Course course=DAOFactory.getCourseDAO().getCourse(tmp.getCouseid());
            System.out.println(course.getCouseid()+course.getName());
        }

        while (volAvailabe>0){
            System.out.println("当前硕士已选择"+ volAlready.toString() +"个志愿。还可选择"+volAvailabe.toString()+"个志愿");
            volAlready++;
            volAvailabe--;
            System.out.println("请输入要选的课程行号");
            int row;

            Scanner sc=new Scanner(System.in);
            row=sc.nextInt();
            Choose choose=new Choose(courses.get(row-1).getCouseid(),m.getSid());
            myChooses.add(choose);
            DAOFactory.getChooseDAO().addChoose(choose);

            //展现本人的已选课程列表 1 或 2 个
            System.out.println("本人已选课程情况更新如下：");
            Iterator<Choose> itr1 = myChooses.iterator();
            System.out.println("课程号\t课程名\t");
            while (itr1.hasNext()) {

                Choose tmp=itr1.next();
                Course course=DAOFactory.getCourseDAO().getCourse(tmp.getCouseid().trim());
                System.out.println(course.getCouseid()+course.getName());
            }

            int res=0;
            while (volAvailabe > 0){
                System.out.println("是否继续添加选课记录？ 1.是 2.否");
                res=sc.nextInt();
                if(res==1){
                    break;
                }
                else if (res==2){
                    return;
                }
                else
                {
                    System.out.println("输入有误。请输入1或者2");
                }

            }
            System.out.println("助教课程列表如下：");
            showCourseList(courses,myChooses);
        }
    }

    public  void submitMenu(){
        System.out.println("--------------提交助教工作表--------------");

                submitTutorTable();


    }

    public void submitTutorTable(){
        //选择自己所担任的一个助教课程。并填写其自评一栏的信息，其他自动生成


        LinkedList<Result> list=DAOFactory.getResultDAO().getmResultList(m.getSid().trim());


        System.out.println("学号\t所任助教的课程号");
        for (int  i=0;i<list.size();i++){
            System.out.println(list.get(i).getMid()+list.get(i).getCouseid());
        }

        System.out.println("请输入要填写课程号");
        Scanner sc=new Scanner(System.in);
        String couid=sc.nextLine();

        System.out.println("请输入一段助教工作自述并签名：");

        String selfdes=sc.nextLine();
        System.out.println("请输入年-月-日");
        String date=sc.nextLine();
        selfdes=selfdes+"\n"+date;


        Course course=DAOFactory.getCourseDAO().getCourse(couid);
        Subject subject=DAOFactory.getSubjectDAO().getSubject(course.getSubid());
        Teacher teacher=DAOFactory.getTeacherDAO().getTeacher(course.getTid());
        TutorTableAllInfo tableAllInfo=new TutorTableAllInfo(m.getSid(),
                m.getName(),course.getName(),course.getType(),course.getAudience(),teacher.getName(),course.getTime(),selfdes,
                0,course.getApplications(),
                subject.name
        );

        try {
            saveAllInfoTable(tableAllInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TutorTable tutorTable=new TutorTable(m.getSid(),teacher.getId(),couid, (byte) 0,selfdes);
        DAOFactory.getTutorTableDAO().addTutorTable(tutorTable);

        System.out.println("助教信息表已保存到本地");
    }

    public void saveAllInfoTable(TutorTableAllInfo tutorTableAllInfo) throws IOException {
        File file=new File(tutorTableAllInfo.getMid()+"_"+ tutorTableAllInfo.getCouname() +".txt");
        try {
            FileOutputStream outputStream=new FileOutputStream(file);
            outputStream.write(("研究生姓名:"+ tutorTableAllInfo.getMname() +"\n").getBytes());
            outputStream.write(("研究生学号:"+ tutorTableAllInfo.getMid() +"\n").getBytes());
            outputStream.write(("课程名称:"+ tutorTableAllInfo.getCouname() +"\n").getBytes());

            outputStream.write(("授课人数:"+tutorTableAllInfo.getSize()+"\n").getBytes());

            outputStream.write(("研究生所占学科:"+ tutorTableAllInfo.getSubname() +"\n").getBytes());

            String type=(tutorTableAllInfo.getType() ==0)?"选修":"必修";

            outputStream.write(("课程性质:"+type+"\n").getBytes());

            String audience=(tutorTableAllInfo.getAudience() ==0)?"本科生":"研究生";
            outputStream.write(("授课对象:"+audience+"\n").getBytes());

            outputStream.write(("授课教师:"+tutorTableAllInfo.getTname()+"\n").getBytes());

            outputStream.write(("授课时间:"+tutorTableAllInfo.getTime()+"\n").getBytes());


            outputStream.write(("助教工作自述:"+tutorTableAllInfo.getSelfdesc()+"\n").getBytes());

            outputStream.write(("授课教师评价:"+tutorTableAllInfo.getResult()+"\n").getBytes());


            outputStream.close();
            System.out.println("<助教工作评定表>已保存到本地！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    //判断输入的日期格式是否符合要求
    private Date scanDate(){
        Scanner sc = new Scanner(System.in);
        String date_string = sc.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
        //Date date = null;
        try{
            java.util.Date temp = format.parse(date_string);
            long datems = temp.getTime();
            return new Date(datems);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //提交学术交流活动申请
    private void AcademicFirstStep() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入活动名称:");
        String name = sc.next();
        System.out.println("请输入活动日期（年-月-日）");
        Date d;
        while (true) {
            d = scanDate();
            if (d != null) {
                break;
            } else {
                System.out.println("输入格式错误，请重新输入");
            }
        }
        //主码的自动生成
        java.util.Date date = new java.util.Date();
        SimpleDateFormat dateFormate = new SimpleDateFormat("MM-dd:hh:mm:ss");
        String aid = m.getSid().trim() + dateFormate.format(date);
        AcademicActivity a = new AcademicActivity();
        a.setActivity_name(name);
        a.setDate(d);
        a.setMaster_id(m.getSid());
        a.setActivity_id(aid);
        //调用接口向数据库中插入记录
        DAOFactory.getAcademicActivityDAO().addAcademicActivity(a);
        System.out.println("活动记录提交成功！");
        //Date d = java.sql.Date.valueOf(sc.next(""));
    }

    //学术交流活动认证第二步：提交证明材料
    private void AcademicSecondStep(){
        //搜索返回该学号下所有的学术活动记录
        List<AcademicActivity> al = DAOFactory.getAcademicActivityDAO().getAcademicActivity(m.getSid());
        Iterator<AcademicActivity> iterator = al.iterator();
        System.out.println("编号\t学术交流活动名称\t学术交流活动时间");
        //该数组记录查询到的所有学术交流活动主码
        String [] log = new String[al.size()];
        int count = 0;
        while(iterator.hasNext()){
            AcademicActivity temp = iterator.next();
            if(temp.isTutor_view() && !temp.isMaster_view()){
                System.out.println(count + 1 + "\t"+temp.getActivity_name()+"\t"+temp.getDate());
                log[count] = temp.getActivity_id().trim();
                count++;
            }
        }
        //选择要提交的记录并上传材料
        if(count > 0){
            System.out.println("请选择要提交材料的记录：");
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                if(choice > 0 && choice <= count){
                    //利用主码获取记录
                    AcademicActivity a = DAOFactory.getAcademicActivityDAO().getAcademicActivitybyId(log[choice-1]);
                    System.out.println("请输入会议报告名称：");
                    String reportname = sc.next();
                    a.setReport_name(reportname);
                    System.out.println("-----参会证明提交-----");
                    System.out.println("请输入图片路径(图片应为“jpg”或“png”格式)：");
                    String picturepath = sc.next();
                    String postfix = picturepath.substring(picturepath.lastIndexOf('.')+1);
                    if(!(postfix.equals("jpg") || postfix.equals("png")) ){
                        System.out.println("图片路径格式错误！");
                    }
                    //System.out.println(postfix);
                    a.setCertificate(picturepath);
                    a.setImage_type(postfix);
                    DAOFactory.getAcademicActivityDAO().updateAcademicActivity(a);
                    flag = false;
                }
                else{
                    System.out.println("选项错误，请重新输入：");
                }
            }
        }
        else{
            System.out.println("暂无可以操作的记录！");
        }

    }

    private void ShowAcademicProcess(){
        List<AcademicActivity> al = DAOFactory.getAcademicActivityDAO().getAcademicActivity(m.getSid());
        Iterator<AcademicActivity> iterator = al.iterator();
        System.out.println("学术活动编号\t学术活动名称\t学术活动时间\t学术活动状态");
        while(iterator.hasNext()){
            AcademicActivity temp = iterator.next();
            if(!temp.isTutor_view()){
                System.out.println(temp.getActivity_id()+ "\t"+temp.getActivity_name()+"\t"+temp.getDate()+"\t导师认证未通过");
            }
            else if(!temp.isMaster_view()){
                System.out.println(temp.getActivity_id()+ "\t"+temp.getActivity_name()+"\t"+temp.getDate()+"\t学院认证未通过");
            }
            else{
                System.out.println(temp.getActivity_id()+ "\t"+temp.getActivity_name()+"\t"+temp.getDate()+"\t认证通过");
            }
            //System.out.println(temp.toString());
        }

    }

    /**
     *学术活动认证模块
     **/
    private void Academicmodule(){
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------学术活动认证子系统---------------");
            System.out.println("1.初次申请学术活动");
            System.out.println("2.提交学术活动证明");
            System.out.println("3.查看办理进程");
            System.out.println("4.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                choose = sc.next();
                switch (choose) {
                    case "1":
                        AcademicFirstStep();
                        flag = false;
                        break;

                    case "2":
                        AcademicSecondStep();
                        flag = false;
                        break;

                    case "3":
                        ShowAcademicProcess();
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

    private void AddAward(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        String mid = sc.next();
        System.out.println("请输入奖励名称:");
        String name = sc.next();
        System.out.println("请输入奖励等级（1:国家级、2:省部级、3:市级、4:其他):");
        int level = Integer.parseInt(sc.next());
        System.out.println("请输入获奖等级（1:特等奖、2:一等奖、3:二等奖、4:三等奖）:");
        int grade = Integer.parseInt(sc.next());
        System.out.println("请输入排名:");
        int ranking = Integer.parseInt(sc.next());
        System.out.println("请输入获奖时间:");
        String time = sc.next();
        System.out.println("请输入佐证材料图片所在路径:");
        String materials = sc.next();
        Master master = new Master();
        master.setSid(mid);
        award award = new award();
        award.setMaster(master);
        award.setName(name);
        award.setReward_grade(level);
        award.setAward_grade(grade);
        award.setRanking(ranking);
        award.setTime(time);
        award.setMaterials(materials);
        DAOFactory.getawardDAO().submitaward(award);
        System.out.println("奖励成果提交成功！");
    }


    private void AddPaper(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        String mid = sc.next();
        System.out.println("请输入论文名称:");
        String name = sc.next();
        System.out.println("请输入论文发表刊物名称:");
        String periodical = sc.next();
        System.out.println("请输入论文状态（1:录用未发表、2:已发表）:");
        int state = Integer.parseInt(sc.next());
        System.out.println("请输入索引类型:");
        String index = sc.next();
        System.out.println("请输入发表时间:");
        String time = sc.next();
        System.out.println("请输入论文归属库情况（1:学院高质量论文库或2:学院核心论文库）:");
        int Attribution = Integer.parseInt(sc.next());
        System.out.println("请输入佐证材料图片所在路径:");
        String materials = sc.next();
        Master master = new Master();
        master.setSid(mid);
        paper paper = new paper();
        paper.setMaster(master);
        paper.setName(name);
        paper.setPeriodical(periodical);
        paper.setState(state);
        paper.setIndex_type(index);
        paper.setTime(time);
        paper.setAttribution(Attribution);
        paper.setMaterials(materials);
        DAOFactory.getpaperDAO().submitpaper(paper);
        System.out.println("论文成果提交成功！");
    }

    private void AddPatent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        String mid = sc.next();
        System.out.println("请输入专利名称:");
        String name = sc.next();
        System.out.println("请输入专利类型（1:发明专利、2:实用新型专利）:");
        int type = Integer.parseInt(sc.next());
        System.out.println("请输入专利号:");
        String number = sc.next();
        System.out.println("请输入专利发布时间:");
        String time = sc.next();
        System.out.println("请输入专利状态:");
        String state = sc.next();
        System.out.println("请输入贡献度排名（整数）:");
        int ranking = Integer.parseInt(sc.next());
        System.out.println("请输入佐证材料图片所在路径:");
        String materials = sc.next();
        Master master = new Master();
        master.setSid(mid);
        patent patent = new patent();
        patent.setMaster(master);
        patent.setName(name);
        patent.setType(type);
        patent.setNumber(number);
        patent.setTime(time);
        patent.setState(state);
        patent.setRanking(ranking);
        patent.setMaterials(materials);

        DAOFactory.getpatentDAO().submitpatent(patent);
        System.out.println("专利成果提交成功！");
    }

    private void AddStandard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        String mid = sc.next();
        System.out.println("请输入标准名称:");
        String name = sc.next();
        System.out.println("请输入标准级别（1:国家标准、2:省部级地方标准、3:行业标准、4:团队标准）:");
        int level = Integer.parseInt(sc.next());
        System.out.println("请输入标准发布时间:");
        String time = sc.next();
        System.out.println("请输入佐证材料图片所在路径:");
        String materials = sc.next();
        Master master = new Master();
        master.setSid(mid);
        standard standard = new standard();
        standard.setMaster(master);
        standard.setName(name);
        standard.setStandard_level(level);
        standard.setTime(time);
        standard.setMaterials(materials);
        DAOFactory.getstandardDAO().submitstandard(standard);
        System.out.println("标准成果提交成功！");
    }

    private void AddReport(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        String mid = sc.next();
        System.out.println("请输入报告名称:");
        String name = sc.next();
        System.out.println("请输入报告类型（1:规划类、2:设计类、3:服务类、4:其他）:");
        int type = Integer.parseInt(sc.next());
        System.out.println("请输入报告服务单位:");
        String unit = sc.next();
        System.out.println("请输入报告时间:");
        String time = sc.next();
        System.out.println("请输入贡献度排名（整数）:");
        int ranking = Integer.parseInt(sc.next());
        System.out.println("请输入佐证材料图片所在路径:");
        String materials = sc.next();
        Master master = new Master();
        master.setSid(mid);
        report report = new report();
        report.setMaster(master);
        report.setName(name);
        report.setType(type);
        report.setUnit(unit);
        report.setTime(time);
        report.setRanking(ranking);
        report.setMaterials(materials);
        DAOFactory.getreportDAO().submitreport(report);
        System.out.println("标准成果提交成功！");
    }

    private void Addplateform(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        String mid = sc.next();
        System.out.println("请输入名称:");
        String name = sc.next();
        System.out.println("请输入平台服务单位:");
        String unit = sc.next();
        System.out.println("请输入平台上线时间:");
        String time = sc.next();
        System.out.println("请输入贡献度（整数）:");
        int ranking = Integer.parseInt(sc.next());
        System.out.println("请输入佐证材料图片所在路径:");
        String materials = sc.next();
        Master master = new Master();
        master.setSid(mid);
        hs_platform platform = new hs_platform();
        platform.setMaster(master);
        platform.setName(name);
        platform.setUnit(unit);
        platform.setTime(time);
        platform.setRanking(ranking);
        platform.setMaterials(materials);
        DAOFactory.getplatformDAO().submiths_platform(platform);
        System.out.println("软硬件平台成果提交成功！");
    }


    private void Addtextbook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        String mid = sc.next();
        System.out.println("请输入教材名称:");
        String name = sc.next();
        System.out.println("请输入教材出版社:");
        String press = sc.next();
        System.out.println("请输入教材出版时间:");
        String time = sc.next();
        System.out.println("请输入贡献度（整数）:");
        int ranking = Integer.parseInt(sc.next());
        System.out.println("请输入佐证材料图片所在路径:");
        String materials = sc.next();
        Master master = new Master();
        master.setSid(mid);
        textbook textbook = new textbook();
        textbook.setMaster(master);
        textbook.setName(name);
        textbook.setPress(press);
        textbook.setTime(time);
        textbook.setRanking(ranking);
        textbook.setMaterials(materials);
        DAOFactory.gettextbookDAO().submittextbook(textbook);
        System.out.println("教材成果提交成功！");
    }


    //学生成果提交子系统
    private void Achievemodule(){
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------学生成果提交子系统---------------");
            System.out.println("1.提交论文成果");
            System.out.println("2.提交奖励成果");
            System.out.println("3.提交标准成果");
            System.out.println("4.提交报告成果");
            System.out.println("5.提交专利成果");
            System.out.println("6.提交软硬件平台成果");
            System.out.println("7.提交教材成果");
            System.out.println("8.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                choose = sc.next();
                switch (choose) {
                    case "1":
                        AddPaper();
                        flag = false;
                        break;

                    case "2":
                        AddAward();
                        flag = false;
                        break;

                    case "3":
                        AddStandard();
                        flag = false;
                        break;
                    case "4":
                        AddReport();
                        flag = false;
                        break;
                    case "5":
                        AddPatent();
                        flag = false;
                        break;
                    case "6":
                        Addplateform();
                        flag = false;
                        break;
                    case "7":
                        Addtextbook();
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
            System.out.println("1.助教课程子模块");
            System.out.println("2.学术活动认证模块");
            System.out.println("3.成果提交模块");
            System.out.println("4.提交助教工作评定表");
            System.out.println("5.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                choose = sc.next();
                switch (choose) {
                    case "1":
                        //可以获得学科下的state=1的课程
                        //通过导师号
                        tutorMenu();
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
                        submitMenu();
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


}

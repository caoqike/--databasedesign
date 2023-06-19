package User;

import DAOS.DAOFactory;
import Entity.*;
import sun.misc.DoubleConsts;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class teacher extends User implements Menu{
    private Teacher t;
    public teacher(UserType type, String loadname, String passwd) {
        super(type, loadname, passwd);
        t = DAOFactory.getTeacherDAO().getTeacher(loadname);
    }
    //表格输出
    private static StringBuffer contentSb = new StringBuffer();
    //调整每列的宽度
    private static Integer[] widths = new Integer[] {30,30,30};

    public  void print_table(String s1, String s2, String s3) {
        String[] table = {s1,s2, s3};
        for (int i = 0; i < 3; ++i) {
            int size = widths[i];
            contentSb.append("|");
            int len = table[i].length();
            int left_space =  (size-len)%2==0 ?(size-len)/2 :(size-len)/2+1 ;
            int right_space =    (size-len)/2    ;
            //左边空格
            for (int j = 0; j <   left_space   ; ++j) {
                contentSb.append(" ");
            }
            contentSb.append(table[i]);
            //右边空格
            for (int j = 0; j <   right_space     ; ++j) {
                contentSb.append(" ");
            }
        }
        contentSb.append("|\n");
        for (int i = 0; i < 3; ++i) {
            contentSb.append("+");
            for (int j = 0; j < widths[i]; ++j) {
                contentSb.append("-");
            }
        }
        contentSb.append("+\n");
    }


    //向上取整
    public static double ceil(double a) {
        return floorOrCeil(a, -0.0, 1.0, 1.0);
    }
    private static double floorOrCeil(double a,
                                      double negativeBoundary,
                                      double positiveBoundary,
                                      double sign) {
        int exponent = Math.getExponent(a);//获取a的指数

        if (exponent < 0) { //如果参数的指数的绝对值小于0，那么一定-1<a<1
            return ((a == 0.0) ? a :
                    ((a < 0.0) ? negativeBoundary : positiveBoundary));
            //如果参数为0.0就直接返回
            //为负数就返回 negativeBoundary（负边界）
            //为正数就返回 positiveBoundary（正边界）
        } else if (exponent >= 52) {
            return a;//指数大于52 超出了double的范围 为无穷大直接返回
        }
        //此时指数在[0，51]
        assert exponent >= 0 && exponent <= 51;

        long doppel = Double.doubleToRawLongBits(a);
        long mask = DoubleConsts.SIGNIF_BIT_MASK >> exponent;

        if ((mask & doppel) == 0L)
            return a;
        else {
            double result = Double.longBitsToDouble(doppel & (~mask));
            if (sign * a > 0.0)
                result = result + sign;
            return result;
        }
    }

    public  void print_table_line(String s1) {
        // System.out.println(s1);
        s1=s1.replaceAll("\r\n"," ");
        s1=s1.replaceAll("\n","");
        // System.out.println(s1);
        int size = 92;
        contentSb.append("|");
        int len = s1.length();
        int left_space =  0;
        int right_space = size-len  ;
        StringBuilder sb = new StringBuilder();
        String temp="[(\\u4e00-\\u9fa5)(\\u3002|\\uff1f|\\uff01|\\uff0c|\\u3001|\\uff1b|\\uff1a|\\u201c|\\u201d|\\u2018|\\u2019|\\uff08|\\uff09|\\u300a|\\u300b|\\u3010|\\u3011|\\u007e)]+";
        Pattern r = Pattern.compile(temp);
        Matcher m = r.matcher(s1);
        StringBuilder ss = new StringBuilder();
        int f=0;
        while (m.find()) {
            f=1;
            sb.append(m.group());
        }
        if(f==1){
            //System.out.println(sb.toString());
//            System.out.println(len);
            len=len-sb.toString().length()+(int) ceil(sb.toString().length()*1.5);
//            System.out.println(len);
            right_space =size-len;
        }
        //左边空格
        for (int j = 0; j <   left_space   ; ++j) {
            contentSb.append(" ");
        }
        contentSb.append(s1);
        //右边空格
        for (int j = 0; j <   right_space     ; ++j) {
            contentSb.append(" ");
        }
        if(f==1){
            contentSb.append("\n");
        }
        else {
            contentSb.append("|\n");
        }
        for (int i = 0; i < 93; ++i) {
            contentSb.append("-");
        }
        contentSb.append("+\n");
    }
    public void menu(){
        while(true){
            System.out.println("---------------------授课教师功能菜单----------------------");
            System.out.println("1.选择课程助教子模块");
            System.out.println("2.评价课程助教工作");
            System.out.println("3.退出系统");
            System.out.println("请选择:");

            //选择操作
            int n;
            Scanner scanner=new Scanner(System.in);
            n=scanner.nextInt();
            switch (n){
                case 1:
                    teachermenu();
                    break;
                case 3:
                    return;
                case 2:
                    evaluate();
                    break;
            }
        }
    }

    public void teachermenu(){
        if(DAOFactory.getChooseStateDAO().getChooseState()==1||DAOFactory.getChooseStateDAO().getChooseState()==3){
            System.out.println(
                    "当前阶段是学生选课阶段，教师无法选择助教！"
            );
            return;

        }
        while (true){
            System.out.println("-----------选择课程助教子模块-----------");
            System.out.println("1.选择助教");
            System.out.println("2.返回上级菜单");

            int n;
            Scanner scanner=new Scanner(System.in);
            n=scanner.nextInt();
            switch (n){
                case 1:
                    chooseassitant();
                    break;
                case 2:
                    return;
            }
        }
    }

    public void evaluate(){
        for (int i = 0; i < 3; ++i) {
            contentSb.append("+");
            for (int j = 0; j < widths[i]; ++j) {
                contentSb.append("-");
            }
        }
        contentSb.append("+\n");

        //获取教师工号
        String tid= DAOFactory.getTeacherDAO().getTeacher(t.getId()).getId();
        LinkedList<Course> courseList=null;

        //教师授课情况如下
        courseList=DAOFactory.getCourseDAO().getteachCourses(tid);

//        LinkedList<Result> resultList=null;
//
//        for(Course c:courseList) {
//            resultList.add(DAOFactory.getResultDAO().getResult(c.getCouseid()));
//        }
        while(true){
            System.out.println("课程号"+"\t课程名称\t课程助教状态");

            //输出老师授课助教情况，便于老师进行选择进行助教工作评价
            for(Course c:courseList){
                if(DAOFactory.getResultDAO().getResult(c.getCouseid())!=null){
                    System.out.println(c.getCouseid().trim()+"\t\t"+c.getName().trim()+"\t\t"+ DAOFactory.getResultDAO().getResult(c.getCouseid()).getMid());
                }
                else if(DAOFactory.getResultDAO().getResult(c.getCouseid())==null  && c.getState()==1){
                    System.out.println(c.getCouseid().trim()+"\t\t"+c.getName().trim()+"\t"+"没有助教");
                }
                else{
                    System.out.println(c.getCouseid().trim()+"\t\t"+c.getName().trim()+"\t"+"不需要助教");
                }
            }

            //请选择需要进行助教工作评价的课程号
//            System.out.println("请输入要查看课程助教工作表的课程号：");
            Scanner scanner=new Scanner(System.in);
//
            String couid="";
            //对输入课程号的判断
            int couflag=0;
            while(true){
                //请选择需要进行助教工作评价的课程号
                System.out.println("请输入要查看课程助教工作表的课程号：");
                couid=scanner.next();
                for(Course c:courseList){
                    //不需要助教
                    if(couid.trim().equals(c.getCouseid().trim()) && c.getState()==0){
                        couflag=-1;
                        break;
                    }
                    //无助教
                    else if(couid.trim().equals(c.getCouseid().trim()) && c.getState()==1){
                        couflag=1;
                        break;
                    }
                    //有助教
                    else{
                        couflag=2;
                        break;
                    }

                }
                //如果为1，则表明没有助教，该课程
                if(couflag==2){
                    break;
                }
                else if(couflag==-1){
                    System.out.println("该课程不需要助教，没有助教工作认定表！");
                    break;
                }
                else if(couflag==1){
                    System.out.println("该课程没有助教，没有助教工作认定表！");
                    break;
                }
                else {
                    System.out.println("课程号输入错误，请输入正确范围的课程号！");
                }
            }

            //课程号正确，则显示助教工作认定表
            //查看工作认定表
            TutorTable tutorTable=DAOFactory.getTutorTableDAO().getTutoTable(couid);
            TutorTableAllInfo tableAllInfo=null;
            //看助教工作表是否已经提交
            if(tutorTable!=null){
                //根据result获取系统中的表
                Master master=DAOFactory.getMasterDAO().getMaster(tutorTable.getMid());
                Course course=DAOFactory.getCourseDAO().getCourse(couid);
                Subject subject=DAOFactory.getSubjectDAO().getSubject(course.getSubid());
                Teacher teacher=DAOFactory.getTeacherDAO().getTeacher(course.getTid());
                tableAllInfo=new TutorTableAllInfo(master.getSid(),
                        master.getName(),course.getName(),course.getType(),course.getAudience(),teacher.getName(),course.getTime(), tutorTable.getSelfdesc(),
                        tutorTable.getResult(),course.getApplications(),
                        subject.name
                );

                System.out.println("---------");
                System.out.println("---------");
                //选修 必修课
                String type=(tableAllInfo.getType() ==0)?"optional course":"required Course";
                //本科生 研究生
                String audience=(tableAllInfo.getAudience() ==0)?"undergraduate":"postgraduate";
                print_table("Mastername:cqkmas1", "Masterid:mas1", "Coursename:sjjg");
                print_table("Size:"+ tableAllInfo.getSize(),
                        "Subjectname:"+ tableAllInfo.getSubname().trim(), "Type:"+type);
                print_table("Teaching object:"+audience,
                        "Teachername:"+tableAllInfo.getTname().trim(), "Teaching time:"+tableAllInfo.getTime());
                print_table_line("Selfdescribe:"+tableAllInfo.getSelfdesc());
                print_table_line("Teacher evaluation:"+tableAllInfo.getResult());
                System.out.println(contentSb.toString());

                System.out.println("对该助教工作做出评价，1.通过 0.不通过");
                int res=scanner.nextInt();
                tutorTable.setResult((byte) res);

                DAOFactory.getTutorTableDAO().updateTutorTable(tutorTable);

                //补充，更新毕业要求统计表的相关内容
                if(res == 1)
                    DAOFactory.getGraduationRequirementsDAO().AddTeachingAssistantTimes(master.getSid());

                System.out.println("评价助教工作成功！");
                //助教工作表更新如下：
                tableAllInfo.setResult(tutorTable.getResult());
                contentSb=new StringBuffer();
                for (int i = 0; i < 3; ++i) {
                    contentSb.append("+");
                    for (int j = 0; j < widths[i]; ++j) {
                        contentSb.append("-");
                    }
                }
                contentSb.append("+\n");
                System.out.println("---------");
                System.out.println("---------");
                print_table("Mastername:cqkmas1", "Masterid:mas1", "Coursename:sjjg");
                print_table("Size:"+ tableAllInfo.getSize(),
                        "Subjectname:"+ tableAllInfo.getSubname().trim(), "Type:"+type);
                print_table("Teaching object:"+audience,
                        "Teachername:"+tableAllInfo.getTname().trim(), "Teaching time:"+tableAllInfo.getTime());
                print_table_line("Selfdescribe:"+tableAllInfo.getSelfdesc());
                print_table_line("Teacher evaluation:"+tableAllInfo.getResult());
                System.out.println(contentSb.toString());
                System.out.println("助教工作表更新成功！");

            }

            //没有提交
//            else{
//                System.out.println("该课程对应助教工作表还未提交，请耐心等待！");
//            }

            System.out.println("是否还要继续进行助教工作评价？1.是 2.否");
            int n=scanner.nextInt();
            if(n==2){
                return;
            }
            else if(n==1){
                n=0;//继续
            }
            else{
                System.out.println("输入有误。请输入1或者2");
            }
        }
    }

    public void Printlist(LinkedList<Course> courseList){

        System.out.println("教师名称\t课程号"+"\t课程名称\t助教");

        Iterator<Course> iterator=courseList.iterator();
        while(iterator.hasNext()){
            Course course=iterator.next();
            if(course.getState()==1){
                System.out.println(
                        DAOFactory.getTeacherDAO().getTeacher(t.getId()).getName().trim()+"\t"+course.getCouseid().trim()+"\t\t"+course.getName().trim()+"\t"+"待定"
                );
            }
            else if (course.getState()==2){
                System.out.println(
                        DAOFactory.getTeacherDAO().getTeacher(t.getId()).getName().trim()+"\t"+course.getCouseid().trim()+"\t\t"+course.getName().trim()+"\t"+ DAOFactory.getResultDAO().getassistant(course.getCouseid()).getMid().trim()
                );
            }
            else{
                System.out.println(
                        DAOFactory.getTeacherDAO().getTeacher(t.getId()).getName().trim()+"\t"+course.getCouseid().trim()+"\t\t"+course.getName().trim()+"\t\t"+"不需要"
                );
            }
        }
    }

    public void chooseassitant(){
        System.out.println("-------------------选择助教-------------------");
        System.out.println("-----");
        System.out.println("-----");


        //获取教师工号
        String tid= DAOFactory.getTeacherDAO().getTeacher(t.getId()).getId();
        LinkedList<Course> courseList=null;

        int flag=0;
        while (true){
            if(flag!=-1){
                courseList=DAOFactory.getCourseDAO().getteachCourses(tid);
                System.out.println("教授课程情况如下：");
                Printlist( courseList);

                //判断，如果教授课程均不需要助教，直接自动退出
                int need=0;
                for(Course c:courseList){
                    if(c.getState()!=0){
                        need=1;
                    }
                }
                if(need==0){
                    System.out.println("教授课程均不需要助教，无需进行选择助教！");
                    return;
                }

            }
            Scanner scanner=new Scanner(System.in);


            String couid;
            //对输入的课程号进行鲁棒性
            while(true){
                System.out.println("请选择要确定助教的课程号：");

                couid=scanner.next();

                //对输入课程号的判断
                Iterator<Course> cit=courseList.iterator();
                int couflag=0;
                while(cit.hasNext()){
                    Course c= cit.next();
                    if(couid.trim().equals(c.getCouseid().trim()) && c.getState()==0){
                        couflag=-1;
                        break;
                    }
                    else if(couid.trim().equals(c.getCouseid().trim())){
                        couflag=1;
                        break;
                    }

                }
                if(couflag==1){
                    break;
                }
                else if(couflag==-1){
                    System.out.println("该课程不需要助教！");
                }
                else {
                    System.out.println("课程号输入错误，请输入正确范围的课程号！");
                }
            }


            Iterator<Course> iterator=courseList.iterator();

            while(iterator.hasNext()){

                Course course=iterator.next();
                if(course.getCouseid().trim().equals(couid.trim())){
                    flag=1;
                    System.out.println("想要申请'"+course.getName()+"'课程的学生记录如下：");
                    System.out.println("课程名称\t学生学号\t学生姓名");

                    //筛选该课程的所有学生学生选择记录
                    LinkedList<Choose> chooses=DAOFactory.getChooseDAO().getassistantlist(couid.trim());

                    //输出选课记录的学生，供教师选择
                    Iterator<Choose> ite=chooses.iterator();

                    while(ite.hasNext()){
                        Choose choose= ite.next();
                        System.out.println(course.getName().trim()+"\t"+ choose.getMid().trim()+"\t"+DAOFactory.getMasterDAO().getMaster(choose.getMid()).getName().trim());
                    }


                    String masid;
                    int n;
                    if(chooses.size()>0){
                        while (true){
                            //选择助教
                            System.out.println("请选择课程助教(输入学生学号)：");
                            masid=scanner.next();


                            //对输入学号的判断
                            Iterator<Choose> ite1=chooses.iterator();
                            int cflag=0;
                            while(ite1.hasNext()){
                                Choose choose= ite1.next();
                                if(masid.trim().equals(choose.getMid().trim())){
                                    cflag=1;
                                    break;
                                }
                            }
                            if(cflag==1){

                                //删除result记录

                                //不为空，说明该课已有助教，在进行重选助教过程
                                if(DAOFactory.getResultDAO().getassistant(course.getCouseid().trim())!=null){
                                    Result r=new Result(course.getCouseid().trim(), DAOFactory.getResultDAO().getassistant(course.getCouseid()).getMid().trim());
                                    DAOFactory.getResultDAO().deleteResult(r);
                                }

                                //确定助教后，更改课程状态，并将choose表中学生记录添加到result表中
                                Result result=new Result(course.getCouseid().trim(),masid.trim());
                                DAOFactory.getResultDAO().addResult(result);

                                //课程状态修改为2，表示该课程已有助教
                                DAOFactory.getCourseDAO().changeCourseState(course.getCouseid(),2);


                                //读取最新记录信息
                                courseList=DAOFactory.getCourseDAO().getteachCourses(tid);


                                break;
                            }
                            else {
                                System.out.println("学号输入错误，请输入正确范围的学号！");
                            }
                        }
//



                        System.out.println("教授课程助教情况更新如下：");
                        Printlist( courseList);


                    }
                    else{
                        System.out.println("选择申请该课程的学生记录为0");
                    }

                    //统计还有多少课程还没有确定助教，从而决定是否弹出“是否”
                    int count=0;//表示需要助教，但还没有助教的课程数量
                    for(Course c:courseList) {
                        if(c.getState()==1){
                            count++;
                        }
                    }

                    n=3;
                    while(count > 0){
                        System.out.println("是否继续确定课程助教？ 1.是 2.否");
                        n=scanner.nextInt();
                        if(n==2){
                            return;
                        }
                        else if(n==1){
                            break;
                        }
                        else{
                            System.out.println("输入有误。请输入1或者2");
                        }
                    }
                    if (count==0){
                        return;
                    }
                    break;
                }

            }

            if(flag==0){
                System.out.println("输入课程号错误！");
                flag=-1;
            }
        }
    }
}

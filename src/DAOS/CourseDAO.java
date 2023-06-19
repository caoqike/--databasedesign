package DAOS;

import Entity.Course;
import Entity.Teacher;

import java.util.LinkedList;

/**
 * @author Administrator
 * @version 1.0
 * @data 2022/12/9 21:01
 */
public interface CourseDAO {

        //增加
        public void addCourse(Course course);
        //删除
        public void deleteCourse(Course course);
        //修改
        public void updateCourse(Course course);
        //查找
        public Course getCourse(String courseid);

        //改变状态
        public void changeCourseState(String courseid,int status);

        //返回全部课程信息
        public LinkedList<Course> getAllCourses(String subid);


        //返回特定状态的课程信息
        public LinkedList<Course> getStateCourses(String subid,int state);

        //返回老师教授需要助教的课程信息
        public LinkedList<Course> getteachCourses(String tid);
}

package com.geekbrains.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DAO_Controller implements DAO {

    private List<Student> students;
    private List<Course> courses;

    private SessionFactory factory;
    private Session session;

    public DAO_Controller(){
         factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
         session = null;
         startSession();
    }
    private void startSession(){
        session = factory.getCurrentSession();
        session.beginTransaction();
        students = session.createQuery("from Student").getResultList();
        courses = session.createQuery("from Course").getResultList();

    }
    public void finishSession(){
        factory.close();
        session.close();
    }

    private Student findStudentByLastName(String lastName){
        for (Student st:students) {
            if (st.getLastName().equals(lastName)) return st;
        }
        return null;
    }

    private Course findCourseByName(String courseName){
        for (Course c:courses) {
            if (c.getCourse_name().equals(courseName)) return c;
        }
        return null;
    }

    public List<Course> getCourselistByLastName(String student_lastName) {
        Student st = session.get(Student.class,findStudentByLastName(student_lastName).getId());
        return st.getCourseList();
    }

    public List<Student> getStudentListByCourseName(String course) {
        Course c = session.get(Course.class,findCourseByName(course).getId());
        return c.getStudentList();
    }

    public void setCourse(String courseName){
        Course course = new Course();
        course.setCourse_name(courseName);
        session.save(course);
        this.courses = session.createQuery("from Course").getResultList();
        session.getTransaction().commit();
    }

    public void setStudent(String lastName,String firstName){
        Student student = new Student();
        student.setLastName(lastName);
        student.setFirstName(firstName);
        session.save(student);
        this.students = session.createQuery("from Student").getResultList();
        session.getTransaction().commit();
    }

    public void deleteCourse(String student_lastName, String course) {
        Student st = session.get(Student.class,findStudentByLastName(student_lastName).getId());
        for (Course c:st.getCourseList()) {
            if (c.getCourse_name().equals(course)){
                st.getCourseList().remove(c);
                System.out.println("course "+ c + " deleted from student " + st.getLastName());
                break;
                }
            }
        session.getTransaction().commit();
    }

    public void deleteStudent(String courseName, String student) {
        Course c = session.get(Course.class,findCourseByName(courseName).getId());
        for (Student st:c.getStudentList()) {
            if (st.getLastName().equals(student)){
                c.getStudentList().remove(st);
                System.out.println("stydent "+ st + " deleted from course " + c.getCourse_name());
                break;
            }
        }
        session.getTransaction().commit();
    }
    // добавить курс студенту - не рабочий метод
    public void setCourseToStudent(String studentLastName, String course) {
        Course currentCourse = null;
        for (Course c:courses) {
            if (c.getCourse_name().equals(course)) {
                currentCourse = session.get(Course.class,findCourseByName(course).getId());
            }
        }
        if (currentCourse == null) {
            System.out.println("no such course");
            return;
        }
        for (Student st:students) {
            if (st.getLastName().equals(studentLastName)){
                st.setCourseList(courses);
                session.save(st);
                session.getTransaction().commit();
                return;
            }
        }
        System.out.println("no such student");
    }
    // записать студента на курс (тоже самое, что и предыдущий метод, сделал для проверки)
    public void setStudentToCourse(String course, String student) {
        Student currentStudent = null;
        for (Student s:students) {
            if (s.getLastName().equals(student)) {
                currentStudent = session.get(Student.class,findStudentByLastName(student).getId());
            }
        }
        if (currentStudent == null) {
            System.out.println("no such student");
            return;
        }
        for (Course c:courses) {
            if (c.getCourse_name().equals(course)){
                c.setStudentList(students);
                session.save(c);
                session.getTransaction().commit();
                return;
            }
        }
        System.out.println("no such student");
    }


}

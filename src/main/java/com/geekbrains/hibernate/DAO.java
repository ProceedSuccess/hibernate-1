package com.geekbrains.hibernate;

import java.util.List;

public interface DAO {

    List<Course> getCourselistByLastName(String student);

    List<Student> getStudentListByCourseName(String course);

    void deleteCourse(String student_lastName,String course);

    void deleteStudent(String courseName, String student);

    void setCourseToStudent(String student, String course);

    void setStudentToCourse(String course, String student);
}

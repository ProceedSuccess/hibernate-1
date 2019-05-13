package com.geekbrains.hibernate;

import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc;
        DAO_Controller controller = new DAO_Controller();
        boolean end = false;
        while (true){
            if (end == true) break;
            sc = new Scanner(System.in);
            System.out.println("выберите команду: \n 1 - показать курсы, которые проходит студент \n" +
                    " 2 - показать студентов, которые записаны на курс \n" +
                    " 3 - удалить курс из списка курсов студента \n" +
                    " 4 - удалить студента из списка участников курса \n" +
                    " 5 - записать студента на курс \n" +
                    " 6 - выход");
            int command = sc.nextInt();
            try{
            switch (command){
                case 1: {
                    System.out.println("введите фамилию студента");
                    sc = new Scanner(System.in);
                    String lastName = sc.nextLine();
                    List<Course> courseListList = controller.getCourselistByLastName(lastName);
                    System.out.println(courseListList);
                    continue;
                }
                case 2: {
                    System.out.println("введите название курса");
                    sc = new Scanner(System.in);
                    String course = sc.nextLine();
                    List<Student> studentList = controller.getStudentListByCourseName(course);
                    System.out.println(studentList);
                    continue;
                }
                case 3: {
                    System.out.println("введите фамилию студента");
                    sc = new Scanner(System.in);
                    String lastName = sc.nextLine();
                    System.out.println("введите название курса");
                    sc = new Scanner(System.in);
                    String course = sc.nextLine();
                    controller.deleteCourse(lastName,course);
                    continue;
                }
                case 4: {
                    System.out.println("введите название курса");
                    sc = new Scanner(System.in);
                    String course = sc.nextLine();
                    System.out.println("введите фамилию студента");
                    sc = new Scanner(System.in);
                    String lastName = sc.nextLine();
                    controller.deleteStudent(course,lastName);
                    continue;
                }
                case 5: {
                    System.out.println("введите название курса");
                    sc = new Scanner(System.in);
                    String course = sc.nextLine();
                    System.out.println("введите фамилию студента");
                    sc = new Scanner(System.in);
                    String lastName = sc.nextLine();
                    controller.setStudentToCourse(course,lastName);
                    continue;
                }
                case 6: {
                    controller.finishSession();
                    System.out.println("bye!");
                    end = true;
                    break;
                }
            }
        }catch (NullPointerException e){
                System.out.println("Проверьте введенные данные"); }

        }
    }
}

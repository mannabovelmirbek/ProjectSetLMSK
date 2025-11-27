import models.Group;
import models.Lesson;
import models.Student;
import server.serverImpl.GroupServerImpl;
import server.serverImpl.LessonServerImpl;
import server.serverImpl.StudentServerImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import enums.Gender;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        GroupServerImpl groupServer = new GroupServerImpl();
        LessonServerImpl lessonServer = new LessonServerImpl();
        StudentServerImpl studentServer = new StudentServerImpl();

        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        String greeting;
        if (hour >= 5 && hour < 11) {
            greeting = "Кутман тан!";
        } else if (hour >= 11 && hour < 18) {
            greeting = "Кутман кун!";
        } else {
            greeting = "Кутман кеч!";
        }
        System.out.println(greeting + " --> " + now.format(DateTimeFormatter.ofPattern("HH:mm")));

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Кируу учун 1 баскычын басыныз: ");
            System.out.println("Чыгуу учун 2 баскычын басыныз: ");
            System.out.print("  Танданыз: ");

            int firstChoice = sc.nextInt();
            sc.nextLine();

            if (firstChoice == 2){
                System.out.println("Программа жабылды!");
                return;
            }

            if (firstChoice == 1) {
                System.out.print("Email почтанызды жазыныз: ");
                String email = sc.nextLine();
                System.out.print("Сыр созду жазыныз: ");
                String password = sc.nextLine();

                if (email.equals("admin@gmail.com") && password.equals("admin123")) {
                    System.out.println("Koш келдиниз!");
                    loggedIn = true;
                } else {
                    System.out.println("почта же сыр соз туура эмес!");
                }
            }
        }

        boolean exit = false;
        while (!exit){
            System.out.println("""
                    
                    1. Add new group
                    2. Get group by name
                    3. Update group name
                    4. Get all groups
                    5. Add new student to group
                    6. Update student
                    7. Find student by first name
                    8. Get all students by group name
                    9. Get all student's lessons
                    10. Delete student
                    11. Add new lesson to group
                    12. Get lesson by name
                    13. Get all lessons by group name
                    14. Delete lesson
                    15. Delete group
                    16. Exit
                    """);

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1 -> {
                    // Add new group
                    System.out.print("Группанын атын жазыныз: ");
                    String groupName = sc.nextLine();
                    System.out.print("Группанын суроттолушун жаз: ");
                    String description = sc.nextLine();
                    Group group = new Group();
                    group.setGroupName(groupName);
                    group.setDescription(description);
                    group.setStudents(new java.util.ArrayList<>());
                    group.setLessons(new java.util.ArrayList<>());
                    groupServer.addNewGroup(group);
                }
                case 2 -> {
                    // Get group by name
                    System.out.print("Группанын атын жазыныз: ");
                    String groupName = sc.nextLine();
                    Group foundGroup = groupServer.getGroupByName(groupName);
                    if (foundGroup != null){
                        System.out.println("Табылган группа: " + foundGroup);
                    } else {
                        System.out.println("Мындай аттуу группа табылган жок!");
                    }
                }
                case 3 -> {
                    // Update group name
                    System.out.print("Эски группанын атын жазыныз: ");
                    String oldName = sc.nextLine();
                    System.out.print("Жаны группанын атын жазыныз: ");
                    String newName = sc.nextLine();
                    Group update = groupServer.updateGroupName(oldName, newName);
                    if (update != null){
                        System.out.println("Жаныланган группа: " + update);
                    } else {
                        System.out.println("Мындай аттуу группа табылган жок!");
                    }
                }
                case 4 -> {
                    // Get all groups
                    List<Group> groups = groupServer.getAllGroups();
                    System.out.println("Баардык группалар:");
                    groups.forEach(System.out::println);
                }
                case 5 -> {
                    // Add new student to group
                    System.out.print("Кайсы группага студент кошосуз: ");
                    String groupName = sc.nextLine();
                    Student newStudent = new Student();

                    System.out.print("Студенттин аты: ");
                    newStudent.setFirstName(sc.nextLine());
                    System.out.print("Студенттин фамилиясы: ");
                    newStudent.setLastName(sc.nextLine());
                    System.out.print("Студенттин Email почтасы: ");
                    newStudent.setGmail(sc.nextLine());
                    System.out.print("Студенттин сыр созу: ");
                    newStudent.setPassword(sc.nextLine());

                    Gender gender = null;
                    while (gender == null) {
                        System.out.print("Студенттин жынысын жазыныз (MALE/FEMALE): ");
                        String g = sc.nextLine().trim().toUpperCase();
                        try {
                            gender = Gender.valueOf(g);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Жарамсыз туура эмес формат. Текшерип кайра жазыңыз: MALE же FEMALE.");
                        }
                    }
                    newStudent.setGender(gender);

                    System.out.println(studentServer.addNewStudentToGroup(groupName, newStudent));
                }
                case 6 -> {
                    // Update student
                    System.out.print("Студенттин атын жазыныз: ");
                    String firstName = sc.nextLine();
                    Student updatedStudent = new Student();
                    System.out.print("Жаңы атын жазыныз: ");
                    updatedStudent.setFirstName(sc.nextLine());
                    System.out.print("Жаңы фамилиясын жазыныз: ");
                    updatedStudent.setLastName(sc.nextLine());
                    Student result = studentServer.updateStudent(firstName, updatedStudent);
                    if (result != null){
                        System.out.println("Жаныланган студент: " + result);
                    } else {
                        System.out.println("Студент табылган жок!");
                    }
                }
                case 7 -> {
                    // Find student by first name
                    System.out.print("Студенттин атын жазыныз: ");
                    String firstName = sc.nextLine();
                    Student found = studentServer.findStudentByFirstName(firstName);
                    if (found != null){
                        System.out.println("Табылган студент: " + found);
                    } else {
                        System.out.println("Студент табылган жок!");
                    }
                }
                case 8 -> {
                    // Get all students by group name
                    System.out.print("Группанын атын жазыныз: ");
                    String groupName = sc.nextLine();
                    List<Student> students = studentServer.getAllStudentsByGroupName(groupName);
                    if (students != null){
                        students.forEach(System.out::println);
                    } else {
                        System.out.println("Группа табылган жок!");
                    }
                }
                case 9 -> {
                    // Get all student's lessons
                    System.out.print("Студенттин Email жазыныз: ");
                    String email = sc.nextLine();
                    List<Lesson> lessons = studentServer.getAllStudentLessons(email);
                    if (lessons != null){
                        lessons.forEach(System.out::println);
                    } else {
                        System.out.println("Студент табылган жок же сабактар жок!");
                    }
                }
                case 10 -> {
                    // Delete student
                    System.out.print("Студенттин атын жазыныз: ");
                    String firstName = sc.nextLine();
                    System.out.println(studentServer.deleteStudent(firstName));
                }
                case 11 -> {
                    // Add new lesson to group
                    System.out.print("Группанын атын жазыныз: ");
                    String groupName = sc.nextLine();
                    System.out.print("Сабактын атын жазыныз: ");
                    String lessonName = sc.nextLine();
                    System.out.print("Сабактын суроттолушун жазыныз: ");
                    String taskDescription = sc.nextLine();
                    Lesson lesson = new Lesson();
                    lesson.setLessonName(lessonName);
                    System.out.println(lessonServer.addNewLessonToGroup(groupName, taskDescription, lesson));
                }
                case 12 -> {
                    // Get lesson by name
                    System.out.print("Сабактын атын жазыныз: ");
                    String lessonName = sc.nextLine();
                    Lesson lesson = lessonServer.getLessonByName(lessonName);
                    if (lesson != null){
                        System.out.println("Табылган сабак: " + lesson);
                    } else {
                        System.out.println("Сабак табылган жок!");
                    }
                }
                case 13 -> {
                    // Get all lessons by group name
                    System.out.print("Группанын атын жазыныз: ");
                    String groupName = sc.nextLine();
                    List<Lesson> lessons = lessonServer.getAllLessonsByGroupName(groupName);
                    if (lessons != null){
                        lessons.forEach(System.out::println);
                    } else {
                        System.out.println("Группа табылган жок!");
                    }
                }
                case 14 -> {
                    // Delete lesson
                    System.out.print("Сабактын атын жазыныз: ");
                    String lessonName = sc.nextLine();
                    System.out.println(lessonServer.deleteLesson(lessonName));
                }
                case 15 -> {
                    // Delete group
                    System.out.print("Группанын атын жазыныз: ");
                    String groupName = sc.nextLine();
                    System.out.println(lessonServer.deleteGroup(groupName));
                }
                case 16 -> {
                    // Exit
                    System.out.println("Программа жабылды!");
                    exit = true;
                }
                default -> System.out.println("Туура эмес тандоо!");
            }
        }
    }
}

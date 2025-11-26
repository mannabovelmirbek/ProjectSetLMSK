package server.serverImpl;

import DataBase.Db;
import models.Group;
import models.Lesson;
import models.Student;
import server.StudentServer;

import java.util.Iterator;
import java.util.List;

public class StudentServerImpl implements StudentServer {

    @Override
    public String addNewStudentToGroup(String groupName, Student student) {
        try {
            if (student == null) return "Студент null болбошу керек!";
            if (!student.getGmail().contains("@")) return "Email туура эмес! '@' белгиси жок.";
            for (Student s : Db.students) {
                if (s.getGmail().equalsIgnoreCase(student.getGmail())) return "Мындай Email менен студент мурун катталган!";
            }
            if (student.getPassword().length() < 7) return "Пароль 7 символдон кыска болбошу керек!";
            Db.students.add(student);
            for (Group group : Db.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    if (group.getStudents() == null) group.setStudents(new java.util.ArrayList<>());
                    group.getStudents().add(student);
                    return "Студент ийгиликтуу группага кошулду!";
                }
            }
            return "Мындай аттуу группа " + groupName + " табылган жок!";
        } catch (Exception e) {
            return "Студент кошууда ката чыкты: " + e.getMessage();
        }
    }

    @Override
    public Student updateStudent(String firstName, Student newStudentData) {
        try {
            for (Student student : Db.students) {
                if (student.getFirstName().equalsIgnoreCase(firstName)) {
                    student.setFirstName(newStudentData.getFirstName());
                    student.setLastName(newStudentData.getLastName());
                    System.out.println("Студент ийгиликтуу жаңыланды!");
                    return student;
                }
            }
        } catch (Exception e) {
            System.out.println("Студентти жаңыртууда ката чыкты: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Student findStudentByFirstName(String firstName) {
        try {
            for (Student student : Db.students) {
                if (student.getFirstName().equalsIgnoreCase(firstName)) return student;
            }
        } catch (Exception e) {
            System.out.println("Студентти издөөдө ката чыкты: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAllStudentsByGroupName(String groupName) {
        try {
            for (Group group : Db.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) return group.getStudents();
            }
        } catch (Exception e) {
            System.out.println("Студенттерди алууда ката чыкты: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllStudentLessons(String studentEmail) {
        try {
            for (Student student : Db.students) {
                if (student.getGmail().equalsIgnoreCase(studentEmail)) return student.getLessons();
            }
        } catch (Exception e) {
            System.out.println("Студенттин сабактарын алуудө ката чыкты: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteStudent(String firstName) {
        try {
            Iterator<Student> iterator = Db.students.iterator();
            while (iterator.hasNext()) {
                Student student = iterator.next();
                if (student.getFirstName().equalsIgnoreCase(firstName)) {
                    iterator.remove();
                    return "Студент ийгиликтуу очурулду!";
                }
            }
            return "Студент табылган жок!";
        } catch (Exception e) {
            return "Студент очурулгонда ката чыкты: " + e.getMessage();
        }
    }
}

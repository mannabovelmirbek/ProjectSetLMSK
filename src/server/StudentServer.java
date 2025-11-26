package server;

import models.Lesson;
import models.Student;

import java.util.List;

public interface StudentServer {
    String addNewStudentToGroup(String groupName, Student student);
    Student updateStudent(String firstName,Student newStudentData);
    Student findStudentByFirstName(String firstName);
    List<Student> getAllStudentsByGroupName(String groupName);
    List<Lesson> getAllStudentLessons(String studentEmail);
    String deleteStudent(String firstName);
}

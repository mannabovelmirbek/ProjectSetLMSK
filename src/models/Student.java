package models;

import DataBase.GenerateId;
import enums.Gender;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private String gmail;
    private String password;
    private Gender gender;
    private List<Lesson> lessons;

    // Default constructor
    public Student() {
        this.id = GenerateId.generateStudentId();
        this.lessons = new ArrayList<>();
    }

    public Student(String firstName){
        this.id = GenerateId.generateStudentId();
        this.firstName = firstName;
        this.lessons = new ArrayList<>();
    }

    public Student(String firstName, String lastName, String gmail, String password, Gender gender) {
        this.id = GenerateId.generateStudentId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gmail = gmail;
        this.password = password;
        this.gender = gender;
        this.lessons = new ArrayList<>();
    }

    // Getters & setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGmail() { return gmail; }
    public void setGmail(String gmail) { this.gmail = gmail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public List<Lesson> getLessons() { return lessons; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gmail='" + gmail + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                '}';
    }
}

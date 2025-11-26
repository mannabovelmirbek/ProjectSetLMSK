package models;

import DataBase.GenerateId;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private long id;
    private String groupName;
    private String description;
    private List<Student> students;
    private List<Lesson> lessons;

    // Default constructor
    public Group() {
        this.id = GenerateId.generateGroupId();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public Group(String groupName){
        this.id = GenerateId.generateGroupId();
        this.groupName = groupName;
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public Group(String groupName, String description, List<Student> students, List<Lesson> lessons) {
        this.id = GenerateId.generateGroupId();
        this.groupName = groupName;
        this.description = description;
        this.students = students;
        this.lessons = lessons;
    }

    // Getters & setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", students=" + students +
                ", lessons=" + lessons +
                '}';
    }
}

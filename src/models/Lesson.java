package models;

import DataBase.GenerateId;

public class Lesson {
    private long id;
    private String lessonName;
    private String taskDescription;

    // Default constructor
    public Lesson() {
        this.id = GenerateId.generateLessonId();
    }

    public Lesson(String lessonName){
        this.id = GenerateId.generateLessonId();
        this.lessonName = lessonName;
    }

    public Lesson(String lessonName, String taskDescription) {
        this.id = GenerateId.generateLessonId();
        this.lessonName = lessonName;
        this.taskDescription = taskDescription;
    }

    // Getters & setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getLessonName() { return lessonName; }
    public void setLessonName(String lessonName) { this.lessonName = lessonName; }

    public String getTaskDescription() { return taskDescription; }
    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}

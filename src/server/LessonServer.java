package server;

import models.Lesson;

import java.util.List;

public interface LessonServer {
    String addNewLessonToGroup(String groupName,String taskDescription,Lesson lesson);
    Lesson getLessonByName(String lessonName);
    List<Lesson>getAllLessonsByGroupName(String groupName);
    String deleteLesson(String lessonName);
    String deleteGroup(String groupName);
}

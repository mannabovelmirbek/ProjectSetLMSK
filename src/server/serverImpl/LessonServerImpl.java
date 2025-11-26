package server.serverImpl;

import DataBase.Db;
import models.Group;
import models.Lesson;
import server.LessonServer;

import java.util.Iterator;
import java.util.List;

public class LessonServerImpl implements LessonServer {

    @Override
    public String addNewLessonToGroup(String groupName, String taskDescription, Lesson lesson) {
        try {
            if (lesson == null) return "Lesson null болбошу керек!";
            for (Group group : Db.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    lesson.setTaskDescription(taskDescription);
                    if (group.getLessons() == null) group.setLessons(new java.util.ArrayList<>());
                    group.getLessons().add(lesson);
                    return "Сабак ийгиликтуу кошулду!";
                }
            }
            return "Группа табылган жок!";
        } catch (Exception e) {
            return "Сабак кошууда ката чыкты: " + e.getMessage();
        }
    }

    @Override
    public Lesson getLessonByName(String lessonName) {
        try {
            for (Lesson lesson : Db.lessons) {
                if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                    System.out.println("Сабак ийгиликтуу табылды!");
                    return lesson;
                }
            }
        } catch (Exception e) {
            System.out.println("Сабакты издөөдө ката чыкты: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllLessonsByGroupName(String groupName) {
        try {
            for (Group group : Db.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    return group.getLessons();
                }
            }
        } catch (Exception e) {
            System.out.println("Сабактарды алуудө ката чыкты: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteLesson(String lessonName) {
        try {
            Iterator<Lesson> iterator = Db.lessons.iterator();
            while (iterator.hasNext()) {
                Lesson lesson = iterator.next();
                if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                    iterator.remove();
                    return "Сабак ийгиликтуу очурулду!";
                }
            }
            return "Сабак табылган жок!";
        } catch (Exception e) {
            return "Сабак очурулганда ката чыкты: " + e.getMessage();
        }
    }

    @Override
    public String deleteGroup(String groupName) {
        try {
            Iterator<Group> iterator = Db.groups.iterator();
            while (iterator.hasNext()) {
                Group group = iterator.next();
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    iterator.remove();
                    return "Группа ийгиликтуу очурулду!";
                }
            }
            return "Группа табылган жок!";
        } catch (Exception e) {
            return "Группаны очурулганда ката чыкты: " + e.getMessage();
        }
    }
}

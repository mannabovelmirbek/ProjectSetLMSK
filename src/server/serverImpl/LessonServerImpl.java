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
            if (lesson.getLessonName() == null || lesson.getLessonName().isBlank()) {
                return "Сабактын аты бош болбошу керек!";
            }

            if (taskDescription == null || taskDescription.isBlank()) {
                return "Сабактын тапшырмасы бош болбошу керек!";
            }


            lesson.setTaskDescription(taskDescription);

            for (Group group : Db.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {

                    if (group.getLessons() == null) {
                        group.setLessons(new java.util.ArrayList<>());
                    }


                    group.getLessons().add(lesson);

                    Db.lessons.add(lesson);

                    return "Сабак ийгиликтүү кошулду!";
                }
            }

            return "Мындай аттуу группа табылган жок!";
        }
        catch (NullPointerException e) {
            return "Ката: Сабак же группа маанилери null болуп жатат.";
        }
        catch (Exception e) {
            return "Белгисиз ката чыкты: " + e.getMessage();
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
            Lesson toDelete = null;

            // Сабакты табабыз
            for (Lesson lesson : Db.lessons) {
                if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                    toDelete = lesson;
                    break;
                }
            }

            if (toDelete == null) {
                return "Сабак табылган жок!";
            }

            // 1) Db.lessonsтен өчүрөбүз
            Db.lessons.remove(toDelete);

            // 2) Бардык группалардан да өчүрөбүз
            for (Group group : Db.groups) {
                group.getLessons().remove(toDelete);
            }

            return "Сабак ийгиликтуу очурулду!";

        } catch (Exception e) {
            return "Сабакты өчүрүүдө ката чыкты: " + e.getMessage();
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

package server.serverImpl;

import DataBase.Db;
import models.Group;
import server.GroupServer;

import java.util.ArrayList;
import java.util.List;

public class GroupServerImpl implements GroupServer {

    @Override
    public void addNewGroup(Group group) {
        try {
            if (group == null) {
                System.out.println("Группа null болбошу керек!");
                return;
            }
            if (group.getGroupName() == null || group.getGroupName().isEmpty()) {
                System.out.println("Группанын аталышы бош!");
                return;
            }
            if (group.getStudents() == null) group.setStudents(new ArrayList<>());
            if (group.getLessons() == null) group.setLessons(new ArrayList<>());
            Db.groups.add(group);
            System.out.println("Группа ийгиликтуу тузулду!");
        } catch (Exception e) {
            System.out.println("Группаны кошууда ката чыкты: " + e.getMessage());
        }
    }

    @Override
    public Group getGroupByName(String groupName) {
        try {
            for (Group group : Db.groups) {
                if (group.getGroupName().equalsIgnoreCase(groupName)) {
                    System.out.println("Группа ийгиликтуу табылды!");
                    return group;
                }
            }
            System.out.println("Мындай аттуу группа табылган жок!");
        } catch (Exception e) {
            System.out.println("Группаны издөөдө ката чыкты: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Group updateGroupName(String oldNameGroup, String newNameGroup) {
        try {
            for (Group group : Db.groups) {
                if (group.getGroupName().equalsIgnoreCase(oldNameGroup)) {
                    group.setGroupName(newNameGroup);
                    System.out.println("Группа ийгиликтуу жаңыланды!");
                    return group;
                }
            }
            System.out.println("Мындай аттуу группа табылган жок!");
        } catch (Exception e) {
            System.out.println("Группанын атын жаңыртууда ката чыкты: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        try {
            return Db.groups;
        } catch (Exception e) {
            System.out.println("Группаларды алуудө ката чыкты: " + e.getMessage());
            return null;
        }
    }
}

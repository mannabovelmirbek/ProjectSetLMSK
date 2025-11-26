package server;

import models.Group;

import java.util.List;

public interface GroupServer {
    void addNewGroup(Group group);
    Group getGroupByName(String name);
    Group updateGroupName(String oldNameGroup, String newNameGroup);
    List<Group> getAllGroups();

}

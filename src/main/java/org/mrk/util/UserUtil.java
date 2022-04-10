package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.Task;
import org.mrk.interfaces.User;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

@UtilityClass
public class UserUtil {
    private static String name;
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User user) {
        UserUtil.currentUser = user;
    }
    public static String getName(){
        return name;
    }
    public static void setName(String name){
        UserUtil.name = name;
    }

    public static User checkNameRep(User user, List<String > usersList){
        if (usersList.contains(user.getFirstName())) {
            user =  new UserBuilder()
                    .setFirstName("new_" + user.getFirstName())
                    .setLastName(user.getLastName())
                    .setId(user.getId())
                    .setTasks(user.getTasks())
                    .build();
            return checkNameRep(user, usersList);
        }
        return user;
    }

    public static void createUser(String name, String lastName, TreeSet<Task> tasks){
        List<String> usersList = FileUtil.loadUsersList();
        if (usersList==null) usersList = new ArrayList<>();
        User user = checkNameRep(
                new UserBuilder()
                .setFirstName(name)
                .setLastName(lastName)
                .setId(UUID.randomUUID())
                .setTasks(tasks)
                .build(), usersList);
        FileUtil.saveNewUser(user.getFirstName());
        FileUtil.saveUserObj(user);
        setCurrentUser(user);
    }

}

package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.Task;
import org.mrk.interfaces.User;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@UtilityClass
public class UserUtil {
    static String name;
    static User user;

    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        UserUtil.user = user;
    }
    public static String getName(){
        return name;
    }
    public static void setName(String name){
        UserUtil.name = name;
    }

    public static User checkRepName(User user, List<String > usersList){
        if (usersList.contains(user.getFirstName())) {
            user =  new UserBuilder()
                    .setFirstName("new_" + user.getFirstName())
                    .setLastName(user.getLastName())
                    .setId(user.getId())
                    .setTasks(new TreeSet<>())
                    .build();
            return checkRepName(user, usersList);
        }
        return user;
    }

    public static void createUserGui(String name,String lastName, TreeSet<Task> tasks){
        List<String> usersList = FileUtil.loadUsersList();
        if (usersList==null) usersList = new ArrayList<>();
        User user = checkRepName(
                new UserBuilder()
                .setFirstName(name)
                .setLastName(lastName)
                .setId(1)
                .setTasks(tasks)
                .build(), usersList);
        FileUtil.saveNewUser(user.getFirstName());
        FileUtil.saveUserObj(user);
        setUser(user);
    }

}

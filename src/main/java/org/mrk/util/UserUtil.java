package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.interfaces.Task;
import org.mrk.interfaces.User;
import org.mrk.model.user.UserModel;

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
            user = UserModel.builder()
                            .firstName("new_" + user.getFirstName())
                            .lastName(user.getLastName())
                            .id(user.getId())
                            .tasks(user.getTasks())
                            .build();
            return checkNameRep(user, usersList);
        }
        return user;
    }

    public static void createUser(String name, String lastName, TreeSet<Task> tasks){
        List<String> usersList = ServiceUtil.loadUsersList();
        if (usersList==null) usersList = new ArrayList<>();
        User user = checkNameRep(
                UserModel.builder()
                        .firstName(name)
                        .lastName(lastName)
                        .id(UUID.randomUUID())
                        .tasks(tasks)
                        .build()
                , usersList);

        ServiceUtil.saveUserName(user.getFirstName());
        ServiceUtil.saveUser(user);
        setCurrentUser(user);
    }

}

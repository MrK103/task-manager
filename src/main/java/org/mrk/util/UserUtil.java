package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.builder.task.TaskCreator;
import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;

import java.util.List;
import java.util.TreeSet;

import static org.mrk.util.Util.input;

@UtilityClass
public class UserUtil {

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
    public static User createUser(){
        return new UserBuilder()
                .setFirstName(input("Enter your name"))
                .setLastName(input("Enter last name"))
                .setId(1)
                .setTasks(new TaskCreator().addTasks())
                .build();
    }

}

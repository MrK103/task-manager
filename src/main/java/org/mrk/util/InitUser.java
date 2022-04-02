package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.exception.NullTaskException;
import org.mrk.interfaces.User;
import org.mrk.ui.UserMenu;

import java.util.List;

@UtilityClass
public class InitUser {

    public User initUsers() {
        List<String> usersList = FileUtil.loadUsersList();
        User user;
        if (usersList.isEmpty()) {
            System.out.println("User not found, creating a new user");
            user = UserUtil.createUser();
            FileUtil.saveNewUser(user.getFirstName());
            FileUtil.saveUserObj(user);
            return user;
        } else {
            int id = UserMenu.userMenu(usersList);
            if (id == -1) { // add new user
                user = UserUtil.checkRepName(UserUtil.createUser(), usersList);
                FileUtil.saveNewUser(user.getFirstName());
                FileUtil.saveUserObj(user);
            } else {

                user = FileUtil.loadUserObj(usersList.get(id));
                if (user==null){
                    return initUsers();
                }
            }
        }

        System.out.println("User initialization...");
        System.out.println("Welcome, " + user.getFirstName() + "!");
        try {
            if (user.getTasks() == null) {
                throw new NullTaskException("ERROR, User task is null");
            }
        } catch (NullTaskException e) {
            user = TaskUtil.addUserTask(user);
            FileUtil.saveUserObj(user);
        }
        return user;
    }
}
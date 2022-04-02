package org.mrk.ui;

import org.mrk.interfaces.Task;
import org.mrk.interfaces.User;
import org.mrk.util.FileUtil;
import org.mrk.util.Util;

import java.util.List;

public abstract class DeleteTaskMenu {

    static void deleteTask(User user) {
        List<Task> tasks = user.getTasks().stream().toList();
        if (tasks.isEmpty()) return;
        tasks.forEach(name -> System.out.println((tasks.indexOf(name) + 1) + ") " + name.getName()));
        int answer = Util.validInt(Util.input("Enter the number of the task you want to delete (any other to exit)"));
        if (answer >= 1 && answer <= tasks.size()) {
            user.getTasks().remove(tasks.get(answer - 1));
            FileUtil.saveUserObj(user);
            deleteTask(user);
        }
    }
}
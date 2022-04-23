package org.mrk.util;

import org.mrk.interfaces.Task;
import org.mrk.interfaces.User;
import org.mrk.model.task.RepeatTask;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadUtil implements Runnable{

    public ThreadUtil(boolean showOverdueTask){
        this.showOverdueTask = showOverdueTask;
    }

    @SuppressWarnings("removal")
    public ThreadUtil(int taskIdForDelete){
        listOfTask.removeIf(thread -> {
            boolean found = thread.getName().equals(String.valueOf(taskIdForDelete));
            if (found) thread.stop();
            return found;
        });

    }

    public static final List<Thread> listOfTask = new ArrayList<>();
    private boolean showOverdueTask = false;

    private void init(){
        List<String> userNameList = ServiceUtil.loadUsersList();
        List<User> userObjList = new ArrayList<>();
        if (userNameList == null) userNameList = new ArrayList<>();
        userNameList.forEach(name -> userObjList.add(ServiceUtil.loadUser(name)));

        for (User user : userObjList) {
            //ju
            if (user == null) return;
            if (user.getTasks() == null) return;

            for (Task task : user.getTasks()) {
                if (TaskUtil.deadLineMs(task.getDate()) == 0) {
                    showOverdueTaskDialog(user, task, showOverdueTask);
                } else {
                    Thread thread = new Thread(runnable(task, user), String.valueOf(task.getIdTask()));
                    if (listOfTask.stream().noneMatch(thread1 ->
                            thread1.getName().equals(String.valueOf(task.getIdTask())))) {
                        listOfTask.add(thread);
                    }
                }
            }
        }
        listOfTask.removeIf(thread -> thread.getState().equals(Thread.State.TERMINATED));
        listOfTask.forEach(thread -> {
            if (thread.getState().equals(Thread.State.NEW)) thread.start();
        });
    }

    public Runnable runnable(Task task, User user){
        return () -> {
            try {
                Thread.sleep(TaskUtil.deadLineMs(task.getDate()));
                if (task instanceof RepeatTask repeatTask){
                    while (repeatTask.getRepeat().get()>0){
                        repeatTask.realization();
                        ServiceUtil.saveUser(user);
                        if (user.getFirstName().equals(UserUtil.getCurrentUser().getFirstName())){
                            UserUtil.setCurrentUser(user);
                        }
                        Thread.sleep(TaskUtil.deadLineMs(task.getDate()));
                    }
                } else task.realization();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    private void showOverdueTaskDialog(User user, Task task, boolean state){
        if (state) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "Задача " + task.getName() + " просрочена" +
                            "\nУдалить данную задачу?",
                    user.getFirstName() + " " + user.getLastName(),
                    JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) deleteOverdueTask(user, task);
        }
    }

    public void deleteOverdueTask(User user, Task task){
        User newUser = ServiceUtil.loadUser(user.getFirstName());
        assert newUser != null;
        newUser.getTasks().remove(task);
        ServiceUtil.saveUser(newUser);
        UserUtil.setCurrentUser(newUser);
    }

    @Override
    public void run() {
        init();
    }
}

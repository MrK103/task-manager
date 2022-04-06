package org.mrk.util;

import org.mrk.interfaces.Task;
import org.mrk.interfaces.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadUtil implements Runnable{

    public ThreadUtil(){
    }
    public ThreadUtil(boolean showOverdueTask){
    this.showOverdueTask = showOverdueTask;
    }
    public ThreadUtil(int taskIdForDelete){
        //this.taskIdForDelete = taskIdForDelete;
        listOfTask.removeIf(thread -> thread.getName().equals(String.valueOf(taskIdForDelete)));
    }

    private static final List<Thread> listOfTask = new ArrayList<>();
    private boolean showOverdueTask = false;
    //private int taskIdForDelete;

    private void init(){
        List<String> userNameList = FileUtil.loadUsersList();
        List<User> userObjList = new ArrayList<>();
        assert userNameList != null;
        userNameList.forEach(name -> userObjList.add(FileUtil.loadUserObj(name)));

        for (User user : userObjList) {
            for (Task task : user.getTasks()) {
                if (TaskUtil.deadLineMs(task.getDate()) == 0) {
                    showOverdueTaskDialog(user, task, showOverdueTask);
                } else {
                    Thread thread = new Thread(runnable(task), String.valueOf(task.getIdTask()));
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

        System.out.println(listOfTask.size());
    }

    public Runnable runnable(Task task){
        return () -> {
            try {
                Thread.sleep(TaskUtil.deadLineMs(task.getDate()));
                task.realization();
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
        User newUser = FileUtil.loadUserObj(user.getFirstName());
        assert newUser != null;
        newUser.getTasks().remove(task);
        FileUtil.saveUserObj(newUser);
        UserUtil.setUser(newUser);
    }


    @Override
    public void run() {
        init();
    }
}

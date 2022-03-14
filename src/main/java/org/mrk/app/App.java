package org.mrk.app;

import org.mrk.interfaces.Task;
import org.mrk.model.AbstractTask;
import org.mrk.util.CreateTask;

import java.util.ArrayList;


public class App{

    public static void main(String[] args) {
        ArrayList<AbstractTask> tasks;
        new CreateTask(tasks = new ArrayList<>());

        for (Task task : tasks){
            System.out.println(task.toString());
        }

       /* while (!tasks.isEmpty()){
            Date currentTime = new Date();
            Date realizationTime =  tasks.get(0).getDate();
            System.out.println("Задача " + tasks.get(0).getName() + " выполнится через " +
                    ((realizationTime.getTime() - currentTime.getTime()) / 1000) + " секунд;");

            while ((realizationTime.getTime() - currentTime.getTime()) >= 0 ){
                currentTime = new Date();
            }

            tasks.get(0).realization();
            tasks.remove(0);
        }*/

    }
}
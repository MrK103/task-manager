package org.mrk.interfaces;

import org.mrk.model.AbstractTask;
import org.mrk.model.enums.Priority;

import java.util.ArrayList;
import java.util.Date;

public interface Util {

    String input(String s);

    double validDouble(String d);

    int validInt(String d);

    boolean checkAnswer(String s);

    void sortTask(ArrayList<AbstractTask> abstractTasks);

    AbstractTask addTask();

    AbstractTask categoryTask();

    Date createData();

    Priority priority(String s);

}

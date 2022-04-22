package org.mrk.interfaces;

import org.mrk.model.task.AbstractTask;

public interface Task extends Comparable<Task> {

    void realization();

    @Override
    String toString();

    @Override
    int compareTo(Task p);

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    String getName();

    int getIdTask();

    org.mrk.model.task.enums.Category getCategory();

    org.mrk.model.task.enums.Priority getPriority();

    java.util.Date getDate();


}

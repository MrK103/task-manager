package org.mrk.interfaces;

import java.io.Serializable;

public interface Task extends Comparable<Task>, Serializable {

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

    org.mrk.enums.Category getCategory();

    org.mrk.enums.Priority getPriority();

    java.util.Date getDate();


}

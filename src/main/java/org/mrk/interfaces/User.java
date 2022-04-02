package org.mrk.interfaces;

import java.io.Serializable;
import java.util.TreeSet;

public interface User extends Serializable {
    String getLastName();

    String getFirstName();

    int getId();

    String toString();

    TreeSet<Task> getTasks();
}

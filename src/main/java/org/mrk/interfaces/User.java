package org.mrk.interfaces;

import java.util.TreeSet;

public interface User<T> {
    String getLastName();

    String getFirstName();

    T getId();

    String toString();

    TreeSet<Task> getTasks();
}

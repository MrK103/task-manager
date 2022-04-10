package org.mrk.interfaces;

import java.io.Serializable;
import java.util.TreeSet;
import java.util.UUID;

public interface User extends Serializable {
    String getLastName();

    String getFirstName();

    UUID getId();

    String toString();

    TreeSet<Task> getTasks();

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();
}

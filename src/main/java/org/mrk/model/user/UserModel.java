package org.mrk.model.user;

//import lombok.Builder;

import lombok.Builder;
import lombok.Getter;
import org.mrk.interfaces.Task;

import java.util.Objects;
import java.util.TreeSet;
import java.util.UUID;

@Builder
@Getter
public final class UserModel implements org.mrk.interfaces.User {

    private final UUID id;
    private final String lastName;
    private final String firstName;
    private final TreeSet<Task> tasks;

    public UserModel(UUID id, String newLastName, String newFirstName, TreeSet<Task> tasks) {
        this.id = id;
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserModel user)) {
            return false;
        }
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return firstName + " " + lastName + " " + id;
    }
}
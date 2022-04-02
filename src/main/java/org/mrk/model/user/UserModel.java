package org.mrk.model.user;

//import lombok.Builder;
import lombok.Getter;
import org.mrk.interfaces.Task;
import java.util.TreeSet;

@Getter
public final class UserModel implements org.mrk.interfaces.User{

    private final int id;
    private final String lastName;
    private final String firstName;
    private final TreeSet<Task> tasks;

//@Builder
    public UserModel(int id, final String newLastName, final String newFirstName, TreeSet<Task> tasks) {
        this.id = id;
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.tasks = tasks;
    }

    @Override
    public String toString(){
        return firstName + " " + lastName + " " + id;
    }
}
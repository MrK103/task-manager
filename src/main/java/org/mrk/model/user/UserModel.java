package org.mrk.model.user;

//import lombok.Builder;
//import lombok.Getter;

import org.mrk.interfaces.Task;

import java.util.TreeSet;

//@Getter
public class UserModel<T> implements org.mrk.interfaces.User<T> {

    private final T id;
    private final String lastName;
    private final String firstName;
    private final TreeSet<Task> tasks;

//@Builder
    public UserModel(T id, final String newLastName, final String newFirstName, TreeSet<Task> tasks) {
        this.id = id;
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.tasks = tasks;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }
    
    @Override
    public T getId(){
        return id;
    }

    @Override
    public String toString(){
        return firstName + " " + lastName + " " + id;
    }

    @Override
    public TreeSet<Task> getTasks() {
        return tasks;
    }
}
package org.mrk.exception;

import org.mrk.app.UserInterface;
import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;

import java.util.TreeSet;

public class NullTaskException extends RuntimeException{

    public NullTaskException(String message, User<Integer> user){
        System.out.println(message);
        User<Integer> newUser = new UserBuilder<Integer>()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setId(user.getId())
                .setTasks(new TreeSet<>())
                .build();

        new UserInterface(newUser).initUsers();
    }

//    public NullTaskException(String message){
//        System.out.println(message);
//        new UserInterface().initUsers();
//    }

}

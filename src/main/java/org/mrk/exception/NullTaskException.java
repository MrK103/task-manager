package org.mrk.exception;

import org.mrk.app.UserInterface;
import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;

import java.util.TreeSet;

public class NullTaskException extends RuntimeException{

    public NullTaskException(String message, User<Integer> user){
        System.out.println(message);
    }
}

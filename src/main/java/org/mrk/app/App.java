package org.mrk.app;

import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;
import org.mrk.util.AbstractUtil;

import java.util.TreeSet;


public class App extends AbstractUtil {

    public static void main(String[] args) {

        // Создать 2 реализации, параметризованные разными типами (String и Integer). (homework5)
        User<String> personString = new UserBuilder<String>()
                .setFirstName("Mark")
                .setLastName("Sholomitskiy")
                .setId("PRIORITY_ID")
                .setTasks(new TreeSet<>())
                .build();

        User<Integer> personInt = new UserBuilder<Integer>()
                 .setFirstName("Mark")
                 .setLastName("Sholomitskiy")
                 .setId(2)
                 .setTasks(new TreeSet<>())
                 .build();

        System.out.println(personString.toString() + " (id - String)\n"
                + personInt.toString() + " (id - Integer)\n");


        //create users vs task

        UserInterface ui = new UserInterface();
        //ui = new UserInterface(personInt);
        ui.initUsers();
    }
}
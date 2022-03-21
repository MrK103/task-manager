package org.mrk.app;

import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;
import org.mrk.util.AbstractUtil;


public class App extends AbstractUtil {

    public static void main(String[] args) {

        // Создать 2 реализации, параметризованные разными типами (String и Integer).
        User<String> personString = new UserBuilder<String>()
                .setFirstName("Mark")
                .setLastName("Sholomitskiy")
                .setId("PRIORITY_ID")
                .build();

        User<Integer> personInt = new UserBuilder<Integer>()
                 .setFirstName("Mark")
                 .setLastName("Sholomitskiy")
                 .setId(2)
                 .build();

        System.out.println(personString.toString() + " (id - String)\n"
                + personInt.toString() + " (id - Integer)\n");


        //create users vs task

        UserInterface ui = new UserInterface();
        ui.initUsers();
        ui.menu();


    }
}
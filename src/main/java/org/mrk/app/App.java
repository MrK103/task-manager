package org.mrk.app;

import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;

import java.util.TreeSet;


public class App {

    public static void main(String[] args) {

        User<Integer> personInt = new UserBuilder<Integer>()
                 .setFirstName("Mark")
                 .setLastName("Sholomitskiy")
                 .setId(2)
                 //.setTasks(new TreeSet<>())
                 .build();

        //create users vs task
        UserInterface ui = new UserInterface();

        /*
          homework 8
          В personInt не проиницилизирована коллекция задач, выбросит ошибку NullTasKException,
          Try/catch применены в AbstractUtil
         */

        ui = new UserInterface(personInt);
        ui.initUsers();
    }
}
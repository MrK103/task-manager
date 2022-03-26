package org.mrk.app;

import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;

import java.util.TreeSet;


public class App {

    /**
     * Добавлены методы deadLineTime (высчитывает время до выполнения задачи в человеческом виде и
     * метод deadLineMs (время до выполнения в мс) в классе AbstractUtil
     *
     */

    public static void main(String[] args) {

// чтобы не создавать каждый раз нового пользователя

//        User<Integer> personInt = new UserBuilder<Integer>()
//                .setFirstName("Mark")
//                .setLastName("Sholomitskiy")
//                .setId(2)
//                //.setTasks(new TreeSet<>())
//                .build();
//        UserInterface ui = new UserInterface(personInt);
//        ui.initUsers();


        //create users vs task
        UserInterface ui = new UserInterface();
        ui = new UserInterface();
        ui.initUsers();
    }
}
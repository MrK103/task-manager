package org.mrk.builder.task;

import org.mrk.model.task.enums.Priority;
import org.mrk.util.AbstractUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractTaskBuilder extends AbstractUtil {

    public String setName() {
        return input("Input a task name");
    }

    public Priority setPriority() {
        Priority priority;
        String s = input("Set priority 1 - High, 2 - Default, 3 - Low.");
        switch (s) {
            case "1" -> priority = Priority.HiGH;
            case "2" -> priority = Priority.DEFAULT;
            case "3" -> priority = Priority.LOW;
            default -> {
                System.out.println("Input only 1/2/3");
                return setPriority();
            }
        }
        return priority;
    }

    public Date setDate() {
        Date date;
        String data = input("Enter date of completion (dd.MM.yyyy)");
        String time = input("Enter runtime (HH:mm)");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        try {
            date = format.parse(data + " " + time);
        } catch (ParseException e) {
            System.out.println("You entered the wrong date, please try again");
            return setDate();
        }
        return date;
    }
}

package org.mrk.builder.task;

import org.mrk.model.task.enums.Priority;
import org.mrk.util.AbstractUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractTaskBuilder extends AbstractUtil {

    public String setName() {
        return input("Укажите имя задачи (Без пробелов)");
    }

    public Priority setPriority(String s) {
        Priority priority;
        switch (s) {
            case "1" -> priority = Priority.HiGH;
            case "2" -> priority = Priority.DEFAULT;
            case "3" -> priority = Priority.LOW;
            default -> {return setPriority(input("Вводите только 1/2/3"));}
        }
        return priority;
    }

    public Date setDate() {
        Date date;
        String data = input("Введите дату выполнения (dd.MM.yyyy)");
        String time = input("Введите время выполнения (HH:mm)");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        try {
            date = format.parse(data + " " + time);
        } catch (ParseException e) {
            System.out.println("Вы ввели не корректную дату, попробуйте еще раз");
            return setDate();
        }
        return date;
    }
}

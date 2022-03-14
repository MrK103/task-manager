package org.mrk.util;

import org.mrk.model.OneTimeTask;
import org.mrk.model.RepeatingTask;
import org.mrk.model.AbstractTask;
import org.mrk.model.enums.Category;
import org.mrk.model.enums.Priority;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public final class CreateTask extends AbstractUtil {

    public CreateTask(ArrayList<AbstractTask> tasks){
        do {
            tasks.add(addTask());
        } while (checkAnswer(input("\nЖелаете добавить еще одну задачу? (Y/N, 1/2)")));
        sortTask(tasks);
    }

    @Override
    public AbstractTask addTask(){
        System.out.println("Добавление новой задачи");
        AbstractTask task = categoryTask();
        task.setName(input("Укажите имя задачи"));
        task.setDate(createData());
        task.setPriority(priority(input("Укажите приоритет 1 - High, 2 - Default, 3 - Low.")));
        return task;
    }

    @Override
    public AbstractTask categoryTask() {
        if (checkAnswer(input("Вы хотите добавить одноразовую задачу или повторяющуюся (Y/N, 1/2)"))) {
            return new OneTimeTask(Category.ONCE);
        } else {
            return new RepeatingTask(validInt(input("Введите кол-во раз повтора")),
                    validInt(input("Введите через какое время повторить (секунды)")), Category.REPEATS);
        }
    }

    @Override
    public Date createData(){
        String data;
        String time;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date;

        data = input("Введите дату выполнения (dd.MM.yyyy)");
        time = input("Введите время выполнения (HH:mm)");

        try {
            date = format.parse(data + " " + time);
        } catch (ParseException e) {
            System.out.println("Вы ввели не корректную дату, попробуйте еще раз");
            return createData();
        }
        return date;
    }

    @Override
    public Priority priority(String s){
        Priority priority;
        switch (s) {
            case "1" -> priority = Priority.HiGH;
            case "2" -> priority = Priority.DEFAULT;
            case "3" -> priority = Priority.LOW;
            default -> {return priority(input("Вводите только 1/2/3"));}
        }
        return priority;
    }
}
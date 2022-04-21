package org.mrk.model.task;

import lombok.Builder;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;

import javax.swing.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public final class RepeatTask extends AbstractTask {

    private final AtomicInteger repeat = new AtomicInteger();
    private final int timesToRepeat;

    public AtomicInteger getRepeat() {
        return repeat;
    }

    @Override
    public void realization() {
        if (repeat.get() > 1) {
            super.date.setTime(date.getTime() + timesToRepeat);
            new Thread(() -> JOptionPane.showMessageDialog(
                    null,
                    "Задача " + super.getName() + " выполнена!"
                            + "\n Осталось выполнить задачу еще "
                            + repeat
                            + " раз",
                    super.getName(),
                    JOptionPane.ERROR_MESSAGE)
            ).start();
            repeat.getAndDecrement();
        }

    }

    public RepeatTask(String name, Category category, Priority priority
            , Date date, int repeat, int timesToRepeat) {
        super(name, category, priority, date);
        this.timesToRepeat = timesToRepeat;
        this.repeat.set(repeat);
    }

    public String toString() {
        return super.toString() +
                "\nRepeats time: " + repeat +
                " Repeats after: " + (timesToRepeat / 1000) + " seconds\n";
    }
}
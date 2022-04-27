package org.mrk.model.task;

import lombok.Builder;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;

import javax.swing.*;
import java.util.Date;

public final class OnceTask extends AbstractTask {

    public OnceTask(String name, Category category, Priority priority, Date date) {
        super(name, category, priority, date);
    }
    public void realization(){
        super.realization();
        new Thread(() -> JOptionPane.showMessageDialog(
                null,
                "Задача " + super.getName() + " выполнена!",
                super.getName(),
                JOptionPane.ERROR_MESSAGE)).start();
    }

    public String toString() {
        return super.toString();
    }

}


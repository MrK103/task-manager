package org.mrk.model;

import lombok.Getter;
import lombok.Setter;
import org.mrk.model.enums.Category;
import org.mrk.model.enums.Priority;
import org.mrk.interfaces.Task;

import java.util.Date;

/**
 * 2. Минимальные характеристики задачи: название,
 * категория, приоритет, дата выполнения (строки).
 */

@Setter
@Getter
public abstract class AbstractTask implements Task {

    private String name;
    protected Category category;
    private Priority priority;
    private Date date;

    @Override
    public void realization() {
        System.out.println("Задача " + name + " выполнена успешно!");
    }

    @Override
    public String toString(){
        return "\nTask name: " + name +
                "\nDate: " + date +
                "\nCategory: " + category.toString() +
                "\nPriority: " + priority;
    }
}

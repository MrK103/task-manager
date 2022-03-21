package org.mrk.model.task;

import lombok.Getter;
import org.mrk.model.task.enums.Category;
import org.mrk.model.task.enums.Priority;
import org.mrk.interfaces.Task;

import java.util.Date;
import java.util.Objects;

/**
 * 2. Минимальные характеристики задачи: название,
 * категория, приоритет, дата выполнения (строки).
 */


@Getter
public abstract class AbstractTask implements Task{

    private final String name;
    protected Category category;
    private final Priority priority;
    private final Date date;

    public AbstractTask(String name, Category category, Priority priority, Date date) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.date = date;
    }

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

    //сортирует по возрастанию дату выполнения задачи
    @Override
    public int compareTo(Task p){
        return getDate().compareTo(p.getDate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AbstractTask task)) return false;
        return date.equals(task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}

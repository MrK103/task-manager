package org.mrk.model.task;

import lombok.Getter;
import org.mrk.model.task.enums.Category;
import org.mrk.model.task.enums.Priority;
import org.mrk.interfaces.Task;
import org.mrk.util.TaskUtil;

import java.util.Date;
import java.util.Objects;

@Getter
public abstract class AbstractTask implements Task {

    private final String name;
    private final Priority priority;
    private final Date date;
    private final int idTask;
    private static int id = 0;
    protected Category category;

    public AbstractTask(String name, Category category, Priority priority, Date date) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.date = date;
        idTask = ++id;
    }

    @Override
    public void realization() {
        System.out.println("Task " + name + " completed successfully!");
    }

    @Override
    public int compareTo(Task p){
        return idTask - p.getIdTask();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AbstractTask task)) return false;
        return idTask == task.idTask;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask);
    }

    @Override
    public String toString(){
        return "\nTask name: " + name +
                "\nID - " + idTask +
                "\n" + TaskUtil.deadLineTime(date) +
                "\nCategory: " + category.toString() +
                "\nPriority: " + priority;
    }
}

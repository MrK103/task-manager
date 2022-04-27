package org.mrk.model.task;

import lombok.Getter;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;
import org.mrk.interfaces.Task;
import org.mrk.util.SoundUtil;
import org.mrk.util.TaskUtil;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Getter
public abstract class AbstractTask implements Task {

    private final String name;
    private final Priority priority;
    protected final Date date;
    private final int idTask;
    private final Category category;

    public AbstractTask(String name, Category category, Priority priority, Date date) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.date = date;
        idTask = new Random().nextInt();
    }

    @Override
    public void realization() {
        SoundUtil.playSound();
    }

    @Override
    public int compareTo(Task p){
        return idTask - p.getIdTask();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AbstractTask task)) {
            return false;
        }
        return idTask == task.idTask;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask);
    }

    @Override

    public String toString(){
        return getName()
                + " - "
                + "priority - "
                + getPriority()
                + "\n" + TaskUtil.deadLineTime(getDate());
    }
}

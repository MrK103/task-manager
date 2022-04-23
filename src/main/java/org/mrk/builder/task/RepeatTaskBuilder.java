package org.mrk.builder.task;

import org.mrk.interfaces.Task;
import org.mrk.model.task.RepeatTask;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;

import java.util.Date;

public class RepeatTaskBuilder {

    private int repeatsTime;
    private int repeatsAfter;
    private  String name;
    private Priority priority;
    private Date date;
    private Category category;



    public RepeatTaskBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public RepeatTaskBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }
    public RepeatTaskBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }
    public RepeatTaskBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public RepeatTaskBuilder setRepeatsTime(int i) {
        this.repeatsTime = i;
        return this;
    }
    public RepeatTaskBuilder setRepeatsAfter(int i) {
        this.repeatsAfter = i;
        return this;
    }

    public Task builder(){
        return new RepeatTask(
                 name
                ,category
                ,priority
                ,date
                ,repeatsTime
                ,repeatsAfter);
    }
}

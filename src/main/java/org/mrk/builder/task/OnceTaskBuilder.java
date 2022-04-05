package org.mrk.builder.task;

import org.mrk.interfaces.Task;
import org.mrk.model.task.OnceTask;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;

import java.util.Date;

public class OnceTaskBuilder{

    private  String name;
    private  Priority priority;
    private  Date date;
    private Category category;



    public OnceTaskBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public OnceTaskBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }
    public OnceTaskBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }
    public OnceTaskBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public Task builder(){
        return new OnceTask(name
                ,category
                ,priority
                ,date);
    }
}

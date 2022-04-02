package org.mrk.model.task;

import org.mrk.model.task.enums.Category;
import org.mrk.model.task.enums.Priority;

import java.util.Date;

public final class OnceTask extends AbstractTask{

    public OnceTask(String name, Category category, Priority priority, Date date) {
        super(name,category,priority, date);
    }

    public String toString(){
        return super.toString() + "\n";
    }

}


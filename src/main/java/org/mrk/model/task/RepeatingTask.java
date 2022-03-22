package org.mrk.model.task;

import org.mrk.model.task.enums.Category;
import org.mrk.model.task.enums.Priority;

import java.util.Date;

public final class RepeatingTask extends AbstractTask {
    private final int repeatsTime;
    private final int repeatsAfter;


    public RepeatingTask(String name, Category category, Priority priority
            , Date date, int repeatsTime, int repeatsAfter ) {
        super(name,category,priority, date);
        this.repeatsAfter = repeatsAfter;
        this.repeatsTime = repeatsTime;
    }

    public String toString(){
        return super.toString() +
                "\nRepeats time: " + repeatsTime +
                "\nRepeats after: " + repeatsAfter + " seconds\n";
    }

}

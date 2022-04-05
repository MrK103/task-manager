package org.mrk.model.task;

import org.mrk.enums.Category;
import org.mrk.enums.Priority;

import java.util.Date;

public final class RepeatTask extends AbstractTask {
    private final int repeatsTime;
    private final int repeatsAfter;

    public RepeatTask(String name, Category category, Priority priority
            , Date date, int repeatsTime, int repeatsAfter ) {
        super(name,category,priority, date);
        this.repeatsAfter = repeatsAfter;
        this.repeatsTime = repeatsTime;
    }

    public String toString(){
        return super.toString() +
                "\nRepeats time: " + repeatsTime +
                " Repeats after: " + repeatsAfter + " seconds\n";
    }

}

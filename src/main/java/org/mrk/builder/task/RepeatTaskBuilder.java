package org.mrk.builder.task;

import org.mrk.model.task.RepeatTask;
import org.mrk.model.task.enums.Category;

public class RepeatTaskBuilder extends AbstractTaskBuilder {

    public int setRepeatsTime(){
        return validInt(input("Enter how many times repeat task"));
    }
    public int setRepeatsAfter(){
        return validInt(input("Enter how long to repeat after (seconds)"));
    }

    public Category setCategory() {
        return Category.REPEATS;
    }

    public RepeatTask builder(){
        return new RepeatTask(setName()
                ,setCategory()
                ,setPriority()
                ,setDate()
                ,setRepeatsTime()
                ,setRepeatsAfter());
    }
}

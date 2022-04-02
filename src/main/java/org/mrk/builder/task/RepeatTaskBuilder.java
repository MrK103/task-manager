package org.mrk.builder.task;

import org.mrk.model.task.RepeatTask;
import org.mrk.model.task.enums.Category;

import static org.mrk.util.Util.input;
import static org.mrk.util.Util.validInt;

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

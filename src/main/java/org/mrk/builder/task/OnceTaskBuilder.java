package org.mrk.builder.task;

import org.mrk.model.task.OnceTask;
import org.mrk.model.task.enums.Category;

public class OnceTaskBuilder extends AbstractTaskBuilder {

    public Category setCategory() {
        return Category.ONCE;
    }

    public OnceTask builder(){
        return new OnceTask(setName()
                ,setCategory()
                ,setPriority()
                ,setDate());
    }

}

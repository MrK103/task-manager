package org.mrk.builder.task;

import org.mrk.model.task.OneTimeTask;
import org.mrk.model.task.enums.Category;

public class OneTimeTaskBuilder extends AbstractTaskBuilder {

    public Category setCategory() {
        return Category.ONCE;
    }

    public OneTimeTask builder(){
        return new OneTimeTask(setName()
                ,setCategory()
                ,setPriority(input("Укажите приоритет 1 - High, 2 - Default, 3 - Low."))
                ,setDate());
    }

}

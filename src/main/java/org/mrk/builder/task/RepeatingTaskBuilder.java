package org.mrk.builder.task;

import org.mrk.model.task.RepeatingTask;
import org.mrk.model.task.enums.Category;

public class RepeatingTaskBuilder extends AbstractTaskBuilder {

    public int setRepeatsTime(){
        return validInt(input("Введите кол-во раз повторять задачу "));
    }
    public int setRepeatsAfter(){
        return validInt(input("Введите через какое время выполнить повторить (секунды)"));
    }

    public Category setCategory() {
        return Category.REPEATS;
    }

    public RepeatingTask builder(){
        return new RepeatingTask(setName()
                ,setCategory()
                ,setPriority(input("Укажите приоритет 1 - High, 2 - Default, 3 - Low."))
                ,setDate()
                ,setRepeatsTime()
                ,setRepeatsAfter());
    }
}

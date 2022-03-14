package org.mrk.model;

import org.mrk.model.enums.Category;

public class RepeatingTask extends AbstractTask {
    private final int repeatsTime;
    private final int repeatsAfter;

    public RepeatingTask(int repeatsTime, int repeatsAfter, Category category){
        this.repeatsAfter = repeatsAfter;
        this.repeatsTime = repeatsTime;
        this.category = category;
    }

    public String toString(){
        return super.toString() +
                "\nRepeats time: " + repeatsTime +
                "\nRepeats after: " + repeatsAfter + " seconds";
    }

}

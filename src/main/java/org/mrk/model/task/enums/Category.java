package org.mrk.model.task.enums;

public enum Category {
    REPEATS("Повторяющаяся задача"),
    ONCE("Одноразовая задача");

    private final String name;

    Category(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

package org.mrk.enums;

public enum Category {
    REPEATS("Повторяющаяся задача"),
    ONCE("Одноразовая задача"),
    ALL("Все задачи");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

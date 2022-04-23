package org.mrk.enums;

public enum SortMenu {
    DATE("Сортировка по дате"),
    NAME("Сортировка по имени"),
    PRIORITY("Сортировка по приоритетам");

    private final String name;

    SortMenu(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
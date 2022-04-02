package org.mrk.ui;

import org.mrk.model.task.enums.Category;
import org.mrk.model.task.enums.Priority;

import static org.mrk.util.Util.input;

public class FilterMenu {
    private final MainMenu mainMenu;

    public FilterMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    void filterMenu() {
        System.out.println();
        switch (input("""
                |----------------------------------|
                | 1. Filter by category            |
                | 2. Filter by priority            |
                | 3. Any number to return          |
                |----------------------------------|
                """)) {
            case "1" -> categoryMenu();
            case "2" -> priorityMenu();
            default -> mainMenu.menu();
        }
    }

    void categoryMenu() {
        switch (input("""
                |----------------------------------|
                | 1. Show one-time tasks           |
                | 2. Show repeatable tasks         |
                | 3. Any number to return          |
                |----------------------------------|
                """)) {
            case "1" -> {
                mainMenu.getUser().getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getCategory().equals(Category.ONCE))
                        .forEach(System.out::println);
                categoryMenu();
            }
            case "2" -> {
                mainMenu.getUser().getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getCategory().equals(Category.REPEATS))
                        .forEach(System.out::println);
                categoryMenu();
            }
            default -> filterMenu();
        }
    }

    void priorityMenu() {
        switch (input("""
                |----------------------------------|
                | 1. High priority tasks           |
                | 2. Low priority tasks            |
                | 3. Default priority tasks        |
                | 4. Any number to return          |
                |----------------------------------|
                """)) {
            case "1" -> {
                mainMenu.getUser().getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getPriority().equals(Priority.HiGH))
                        .forEach(System.out::println);
                priorityMenu();
            }
            case "2" -> {
                mainMenu.getUser().getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getPriority().equals(Priority.LOW))
                        .forEach(System.out::println);
                priorityMenu();
            }
            case "3" -> {
                mainMenu.getUser().getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getPriority().equals(Priority.DEFAULT))
                        .forEach(System.out::println);
                priorityMenu();
            }
            default -> filterMenu();
        }
    }
}
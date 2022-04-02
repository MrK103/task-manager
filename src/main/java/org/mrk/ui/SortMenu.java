package org.mrk.ui;

import org.mrk.interfaces.Task;

import java.util.Comparator;

import static org.mrk.util.Util.input;

public class SortMenu {
    private final MainMenu mainMenu;

    public SortMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @SuppressWarnings("deprecation")
    public void sortMenu() {
        String answer;
        answer = input("""
                |----------------------------------|
                | 1. Sort by date                  |
                | 2. Sort by name                  |
                | 3. Sort by priority              |
                | 4. Any number to return          |
                |----------------------------------|
                """);
        switch (answer) {
            case "1" -> {
                mainMenu.getUser().getTasks().stream()
                        .sorted(Comparator.comparing(Task::getDate))
                        .forEach(task -> System.out.println("| " + task.getName() + " - "
                                + task.getDate().toLocaleString()));
                sortMenu();
            }
            case "2" -> {
                mainMenu.getUser().getTasks()
                        .stream()
                        .sorted(Comparator.comparing(Task::getName))
                        .forEach(task -> System.out.println("| " + task.getName()));
                sortMenu();
            }
            case "3" -> {
                mainMenu.getUser().getTasks()
                        .stream()
                        .sorted(Comparator.comparing(Task::getPriority))
                        .forEach(task -> System.out.printf("| %s - %s priority\n", task.getName(), task.getPriority()));

                sortMenu();
            }
            default -> mainMenu.menu();
        }
    }
}
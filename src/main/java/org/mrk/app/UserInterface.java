package org.mrk.app;

import org.mrk.interfaces.User;
import org.mrk.exception.NullTaskException;
import org.mrk.util.TaskCreator;
import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.Task;
import org.mrk.model.task.enums.Category;
import org.mrk.model.task.enums.Priority;
import org.mrk.util.AbstractUtil;

import java.util.Comparator;
import java.util.List;

public class UserInterface extends AbstractUtil {
    private User<Integer> user;

    public UserInterface(){
    }

    @SuppressWarnings("unused")
    public UserInterface(User<Integer> user){
        this.user = user;
    }
    public void initUsers(){
        System.out.println("User initialization...");
        if (user != null){
            System.out.println("Welcome, " + user.getFirstName() + "!");
            try {
                if (user.getTasks() == null) {
                    throw new NullTaskException("ERROR, User task is null", user);
                }
            }catch (NullTaskException e) {
                user = AbstractUtil.refactorUser(user);
            }
            menu();
        } else {
            System.out.println("User not found, creating a new user");
            user = AbstractUtil.createUser();
            menu();
        }
    }

    public void menu(){
        String answer = input("\n| "
                + user.toString()
                + """

                |----------------------------------|
                | 1.Show tasks                     |
                | 2.Show tasks in full             |
                | 3.Add new tasks                  |
                | 4.Delete tasks                   |
                | 5.Sort tasks                     |
                | 6.Filter tasks                   |
                | 7.Exit (Any number)              |
                |----------------------------------|
                """);

        switch (answer){
            case "1" -> {
                System.out.println("|----------------------------------|");
                user.getTasks().forEach(task -> System.out.println(task.getIdTask() + ") " + task.getName()));
                System.out.print("|----------------------------------|");
                menu();
            }
            case "2" -> {
                System.out.println("|----------------------------------|");
                user.getTasks().forEach(System.out::println);
                System.out.print("|----------------------------------|");
                menu();
            }
            case "3" -> {
                new TaskCreator(user.getTasks()).addTasks();
                menu();
            }
            case "4" -> deleteMenu();
            case "5" -> sortMenu();
            case "6" -> filterMenu();
            default -> System.out.println("Program completed");
        }
    }
    @SuppressWarnings("deprecation")
    public void sortMenu(){
        String answer;
        answer = input("""
                |----------------------------------|
                | 1. Sort by date                  |
                | 2. Sort by name                  |
                | 3. Sort by priority              |
                | 4. Any number to return          |
                |----------------------------------|
                """);
        switch (answer){
            case "1" -> {
                user.getTasks().stream()
                        .sorted(Comparator.comparing(Task::getDate))
                        .forEach(task -> System.out.println("| " + task.getName() + " - "
                                + task.getDate().toLocaleString()));
                sortMenu();}
            case "2" -> {
                user.getTasks()
                        .stream()
                        .sorted(Comparator.comparing(Task::getName))
                        .forEach(task -> System.out.println("| " + task.getName()));
                sortMenu();
            }
            case "3" -> {
                user.getTasks()
                        .stream()
                        .sorted(Comparator.comparing(Task::getPriority))
                        .forEach(task -> System.out.printf("| %s - %s priority\n", task.getName(), task.getPriority()));

                sortMenu();
            }
            default -> menu();
        }
    }

    void filterMenu(){
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
            default -> menu();
        }
    }
    void categoryMenu(){
        switch (input("""
                |----------------------------------|
                | 1. Show one-time tasks           |
                | 2. Show repeatable tasks         |
                | 3. Any number to return          |
                |----------------------------------|
                """)){
            case "1" -> {
                user.getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getCategory().equals(Category.ONCE))
                        .forEach(System.out::println);
                categoryMenu();
            }
            case "2" -> {
                user.getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getCategory().equals(Category.REPEATS))
                        .forEach(System.out::println);
                categoryMenu();
            }
            default -> filterMenu();
        }
    }

    void priorityMenu(){
        switch (input("""
                |----------------------------------|
                | 1. High priority tasks           |
                | 2. Low priority tasks            |
                | 3. Default priority tasks        |
                | 4. Any number to return          |
                |----------------------------------|
                """)){
            case "1" -> {
                user.getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getPriority().equals(Priority.HiGH))
                        .forEach(System.out::println);
                priorityMenu();
            }
            case "2" -> {
                user.getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getPriority().equals(Priority.LOW))
                        .forEach(System.out::println);
                priorityMenu();
            }
            case "3" -> {
                user.getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getPriority().equals(Priority.DEFAULT))
                        .forEach(System.out::println);
                priorityMenu();
            }
            default -> filterMenu();
        }
    }

    void deleteMenu(){
        List<Task> tasks = user.getTasks().stream().toList();
        tasks.forEach(name -> System.out.println((tasks.indexOf(name) + 1) + ") " + name.getName()));
        int answer = validInt(input("Enter the number of the task you want to delete (any other to exit)"));
        if (answer < 1 || answer > tasks.size()) {
            menu();
        } else {
            user.getTasks().remove(tasks.get(answer - 1));
            deleteMenu();
        }
    }
}
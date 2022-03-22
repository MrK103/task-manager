package org.mrk.app;

import org.mrk.interfaces.User;
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
        System.out.println("Загрузка пользователя...");
        if (user != null){
            System.out.println("Пользователь найден");
            menu();
        } else {
            System.out.println("Пользователь не найден, создание новго пользователя");
            user = new UserBuilder<Integer>()
                    .setFirstName(input("Введите ваше имя"))
                    .setLastName(input("Введите фамилию"))
                    .setId(1)
                    .setTasks(new TaskCreator().addTasks())
                    .build();
        }
    }

    public void menu(){
        String answer = input("\n| " + user.toString()
                + """
                
                |----------------------------------|
                | 1.Показать задачи                |
                | 2.Показать задачи полностью      |
                | 3.Добавление новых задач         |
                | 4.Удаление задач                 |
                | 5.Отсортировать задачи           |
                | 6.Отфильтровать задачи           |
                | 7.Выйти (Любую цифру)            |
                |----------------------------------|
                """);

        switch (answer){
            case "1" -> {
                System.out.println("|----------------------------------|");
                user.getTasks().forEach(task -> System.out.println(task.getIdTask() + ") " + task.getName()));
                System.out.println("|----------------------------------|");
                menu();
            }
            case "2" -> {
                System.out.println("|----------------------------------|");
                user.getTasks().forEach(System.out::println);
                System.out.println("|----------------------------------|");
                menu();
            }
            case "3" -> {
                new TaskCreator(user.getTasks()).addTasks();
                menu();
            }
            case "4" -> deleteMenu();
            case "5" -> sortMenu();
            case "6" -> filterMenu();
            default -> System.out.println("Программа завершена");
        }
    }
    @SuppressWarnings("deprecation")
    public void sortMenu(){
        String answer;
        answer = input("""
                |----------------------------------|
                | 1. Сортировка по дате            |
                | 2. Сортировка по названию        |
                | 3. Сортировка по приоритетам     |
                | 4. Любую цифру для возврата      |
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
                        .forEach(task -> System.out.println(task.getName()));
                sortMenu();
            }
            case "3" -> {
                user.getTasks()
                        .stream()
                        .sorted(Comparator.comparing(Task::getPriority))
                        .forEach(task -> System.out.println(task.getName() + " - " + task.getPriority() + " priority"));
                sortMenu();
            }
            default -> menu();
        }
    }

    void filterMenu(){
        System.out.println();
        switch (input("""
                |----------------------------------|
                | 1. Отфильтровать по категория    |
                | 2. Отфильтровать по приоритетам  |
                | 3. Вернуться назад               |
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
                | 1. Показать одноразовые задачи   |
                | 2. Показать многоразовые задачи  |
                | 3. Вернуться назад               |
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
                | 1. Задачи с высоким приоритетом  |
                | 2. Ззадачи с низким приоритетом  |
                | 3. Задачи с обычым приоритетом   |
                | 4. Вернуться                     |
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
        int answer = validInt(input("Введите номер задачи, которую хотите удалить, (любую другую для выхода)"));
        if (answer < 1 || answer > tasks.size()) {
            menu();
        } else {
            user.getTasks().remove(tasks.get(answer - 1));
            deleteMenu();
        }
    }
}
package org.mrk.ui;

import org.mrk.builder.task.TaskCreator;
import org.mrk.interfaces.User;
import org.mrk.util.*;

import static org.mrk.util.Util.checkAnswer;
import static org.mrk.util.Util.input;

public class MainMenu {
    private User user;
    private final SortMenu sortMenu = new SortMenu(this);
    private final FilterMenu filterMenu = new FilterMenu(this);
    //private final DeleteMenu deleteMenu = new DeleteMenu(this);
    //private final InitUser initUser = new InitUser();


    public User getUser() {
        return user;
    }

    public MainMenu(){
        user = InitUser.initUsers();
        menu();
    }

//    public MainMenu(User user){
//        this.user = user;
//    }

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
                | 7.Change user                    |
                | 8.Delete user                    |
                | 9.Exit (Any number)              |
                |----------------------------------|
                """);

        switch (answer){
            case "1" -> {
                System.out.println("|----------------------------------|");
                user.getTasks().forEach(task -> System.out.println(task.getIdTask() + ") "
                        + task.getName() + ". "
                        + TaskUtil.deadLineTime(task.getDate())));
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
                FileUtil.saveUserObj(user);
                menu();
            }
            case "4" -> {
                DeleteTaskMenu.deleteTask(user);
                menu();
            }
            case "5" -> sortMenu.sortMenu();
            case "6" -> filterMenu.filterMenu();
            case "7" -> {
                user = InitUser.initUsers();
                menu();
            }
            case "8" -> {
                if (checkAnswer(input("Are you sure? (1/2) (Y/N)"))){
                    FileUtil.deleteUser(user.getFirstName());
                    user = InitUser.initUsers();
                }
                menu();
            }
            default -> {
                System.out.println("Program completed");
                FileUtil.saveUserObj(user);
            }
        }
    }

    }
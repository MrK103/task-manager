package org.mrk.util;

import org.mrk.builder.task.OneTimeTaskBuilder;
import org.mrk.builder.task.RepeatingTaskBuilder;
import org.mrk.interfaces.Task;

import java.util.TreeSet;



public class TaskCreator extends AbstractUtil {
    private TreeSet<Task> tasks;

    public TaskCreator(){
        tasks = new TreeSet<>();
    }

    public TaskCreator(TreeSet<Task> tasks){
        this.tasks = tasks;
    }

    public TreeSet<Task> addTasks(){
        if (tasks == null){
            tasks = new TreeSet<>();
        }
        do {
            Task task = createTask();
            if (!checkRepeats(task) || !tasks.add(task)) {
                System.out.println("Упс, задача " + task.getName() + " уже существует, попробуйте еще раз");
            }
        }
        while (checkAnswer(input("Добавить еще одну задачу? (1/2), (Y/N)")));
        return tasks;
    }

    public Task createTask(){
        if (checkAnswer(input("""
                Выберите категорию задачи? (1/2), (Y/N)
                 1) Одноразовая
                 2) Повторяющаяся
                 """))) {
            return new OneTimeTaskBuilder().builder();
        } else return new RepeatingTaskBuilder().builder();
    }

    public boolean checkRepeats(Task task){
        if (tasks.isEmpty()) {
            return true;
        }
        else return tasks
                .stream()
                .noneMatch(AbstractTask -> AbstractTask.getName().equals(task.getName()));
    }
}

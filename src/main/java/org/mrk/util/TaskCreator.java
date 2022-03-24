package org.mrk.util;

import org.mrk.builder.task.OnceTaskBuilder;
import org.mrk.builder.task.RepeatTaskBuilder;
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
                System.out.println("Oops, the task " + task.getName() + " already exists, please try again");
            }
        }
        while (checkAnswer(input("Add one more task? (1/2), (Y/N)")));
        return tasks;
    }

    public Task createTask(){
        if (checkAnswer(input("""
                Select a task category? (1/2), (Y/N)
                 1) One time task
                 2) Repeatable task
                 """))) {
            return new OnceTaskBuilder().builder();
        } else return new RepeatTaskBuilder().builder();
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

package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.builder.task.OnceTaskBuilder;
import org.mrk.builder.task.RepeatTaskBuilder;
import org.mrk.interfaces.Task;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

@UtilityClass
public class TaskUtil {
    public static String deadLineTime(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        long toDeadLineMS  = deadLineMs(date);
        if (toDeadLineMS == 0){
            return "Deadline: " + format.format(date) + ". Times left: " + "Task overdue!";
        } else {
            Calendar calculate = Calendar.getInstance();
            calculate.setTimeInMillis(toDeadLineMS);
            String time = ((calculate.get(Calendar.DAY_OF_YEAR) - 1 ) * 24)
                    + (calculate.get(Calendar.HOUR_OF_DAY) - 11+8)
                    + ":" + calculate.get(Calendar.MINUTE)
                    + ":" + calculate.get(Calendar.SECOND);
            return "Deadline: " + format.format(date) + ". Times left: " + time;
        }
    }

    public static long deadLineMs(Date date){
        Date nowDate = new Date();
        if (nowDate.getTime() > date.getTime()){
            return 0;
        }
        return date.getTime() - nowDate.getTime();
    }


    public static boolean checkRepeats(Task task, TreeSet<Task> tasks){
        if (tasks.isEmpty()) {
            return true;
        }
        else return tasks
                .stream()
                .noneMatch(AbstractTask -> AbstractTask.getName().equals(task.getName()));
    }

    public static Task addTaskOnce(String name, Category category, Priority priority, Date date){
        return new OnceTaskBuilder()
                .setName(name)
                .setCategory(category)
                .setPriority(priority)
                .setDate(date).
                builder();
    }
    public static Task addTaskRepeats(String name, Category category, Priority priority, Date date, int reps, int repsAfter){
        return new RepeatTaskBuilder()
                .setName(name)
                .setCategory(category)
                .setPriority(priority)
                .setDate(date)
                .setRepeatsTime(reps)
                .setRepeatsAfter(repsAfter)
                .builder();
    }
}

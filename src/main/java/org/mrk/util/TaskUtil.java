package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

@UtilityClass
public class TaskUtil {
    public static String deadLineTime(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        long toDeadLineMS  = deadLineMs(date);
        if (toDeadLineMS == -1){
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
            return -1;
        }
        return date.getTime() - nowDate.getTime();
    }



    public static User addUserTask(User user){
        return new UserBuilder()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setId(user.getId())
                .setTasks(new TreeSet<>())
                .build();
    }
}

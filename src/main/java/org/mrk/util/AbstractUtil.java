package org.mrk.util;

import org.mrk.builder.user.UserBuilder;
import org.mrk.interfaces.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Scanner;
import java.util.TreeSet;

public abstract class AbstractUtil{
     private static final Scanner scanner = new Scanner(System.in);

     public static String input(String s){
         System.out.print(s+": ");
         return scanner.nextLine();
     }

     @SuppressWarnings("unused")
    public double validDouble(String d) {
        try {
            return Double.parseDouble(d);
        } catch (NumberFormatException ex) {
            return validDouble(input("Enter only numbers"));
        }
    }

    public int validInt(String d) {
        try {
            return Integer.parseInt(d);
        } catch (NumberFormatException ex) {
            return validInt(input("Enter only whole numbers"));
        }
    }

    public boolean checkAnswer(String s) {
         if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("1")) {
             return true;
         } else if (s.equalsIgnoreCase("n") || s.equalsIgnoreCase("2")) {
             return false;
         } else return checkAnswer(input("You entered an incorrect answer, please answer Y/N (1/2)"));
     }

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


    public static User createUser(){
        return new UserBuilder<Integer>()
                .setFirstName(input("Enter your name"))
                .setLastName(input("Enter last name"))
                .setId(1)
                .setTasks(new TaskCreator().addTasks())
                .build();
    }

    public static User refactorUser(User<Integer> user){
        return new  UserBuilder<Integer>()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setId(user.getId())
                .setTasks(new TreeSet<>())
                .build();
    }
}


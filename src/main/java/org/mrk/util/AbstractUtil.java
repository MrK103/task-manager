package org.mrk.util;

import org.mrk.model.AbstractTask;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbstractUtil implements org.mrk.interfaces.Util {
     private final Scanner scanner = new Scanner(System.in);

     @Override
     public String input(String s){
         System.out.print(s+": ");
         return scanner.nextLine();
     }

    @Override
    public double validDouble(String d) {
        try {
            return Double.parseDouble(d);
        } catch (NumberFormatException ex) {
            return validDouble(input("Вводите только числа"));
        }
    }

    @Override
    public int validInt(String d) {
        try {
            return Integer.parseInt(d);
        } catch (NumberFormatException ex) {
            return validInt(input("Вводите только целые числа"));
        }
    }

    @Override
    public boolean checkAnswer(String s) {
         if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("1")) {
             return true;
         } else if (s.equalsIgnoreCase("n") || s.equalsIgnoreCase("2")) {
             return false;
         } else return checkAnswer(input("Вы ввели некорректный ответ, отвечайте Y/N (1/2)"));
     }

    @Override
    public void sortTask(ArrayList<AbstractTask> tasks){
         if (tasks.size() != 1 ){
             for (int i = 0; i < tasks.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (tasks.get(i).getDate().getTime() < tasks.get(j).getDate().getTime()){
                        AbstractTask temp = tasks.get(i);
                        tasks.remove(i);
                        tasks.add(i, tasks.get(j));
                        tasks.remove(j);
                        tasks.add(j, temp);
                    }
                }
            }
         }
    }
}

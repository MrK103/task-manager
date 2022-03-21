package org.mrk.util;

import java.util.Scanner;

public abstract class AbstractUtil{
     private final Scanner scanner = new Scanner(System.in);

     public String input(String s){
         System.out.print(s+": ");
         return scanner.nextLine();
     }

    public double validDouble(String d) {
        try {
            return Double.parseDouble(d);
        } catch (NumberFormatException ex) {
            return validDouble(input("Вводите только числа"));
        }
    }

    public int validInt(String d) {
        try {
            return Integer.parseInt(d);
        } catch (NumberFormatException ex) {
            return validInt(input("Вводите только целые числа"));
        }
    }

    public boolean checkAnswer(String s) {
         if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("1")) {
             return true;
         } else if (s.equalsIgnoreCase("n") || s.equalsIgnoreCase("2")) {
             return false;
         } else return checkAnswer(input("Вы ввели некорректный ответ, отвечайте Y/N (1/2)"));
     }
}

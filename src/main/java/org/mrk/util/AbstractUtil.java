package org.mrk.util;

import java.util.Scanner;

public abstract class AbstractUtil{
     private final Scanner scanner = new Scanner(System.in);

     public String input(String s){
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
}

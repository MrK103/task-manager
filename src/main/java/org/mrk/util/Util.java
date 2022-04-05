package org.mrk.util;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@UtilityClass
public class Util {
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

    public static int validInt(String d) {
        try {
            return Integer.parseInt(d);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public Date setDate(String textFromField) {
        Date date;
        String[] arrayDate;
        if ((arrayDate = textFromField.split(" ")).length!=2){
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            date = format.parse(arrayDate[0] + " " + arrayDate[1]);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

}

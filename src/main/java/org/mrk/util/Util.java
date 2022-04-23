package org.mrk.util;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@UtilityClass
public class Util {
//     private static final Scanner scanner = new Scanner(System.in);

//     public static String input(String s){
//         System.out.print(s+": ");
//         return scanner.nextLine();
//     }
//
//     @SuppressWarnings("unused")
//    public double validDouble(String d) {
//        try {
//            return Double.parseDouble(d);
//        } catch (NumberFormatException ex) {
//            return validDouble(input("Enter only numbers"));
//        }
//    }

    public static int validInt(String d) {
        try {
            return Integer.parseInt(d);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public Date setDate(LocalDate localDate, String hours, String minutes) {
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (localDate == null) return null;
        if (hours.isEmpty()) hours = "00";
        if (minutes.isEmpty()) minutes = "00";
        try {
            date = format.parse(localDate + " " + hours+":"+minutes);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

}

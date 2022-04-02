package org.mrk.ui;

import org.mrk.util.Util;

import java.util.List;

public class UserMenu {

    public static int userMenu(List<String> list){
        System.out.println("0) Add new user");
        for (int i = 0; i < list.size(); i++) {
             System.out.println((i+1) + ") " + list.get(i) );
        }
        int answer = Util.validInt(Util.input("Выберите пользователя"));
        if (answer < 0 || answer > list.size()) {
            return userMenu(list);
        }
        return answer-1;
    }
}

package org.mrk.app;

import org.mrk.ui.MainMenu;

public class App {
    static MainMenu mainMenu;

    public static void main(String[] args) {
        System.out.println("Load program...");
        mainMenu = new MainMenu();
    }
}
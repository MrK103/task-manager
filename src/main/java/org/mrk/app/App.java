package org.mrk.app;

import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;

import java.io.File;
import java.io.IOException;


public class App {
    public static void main(String[] args) {

        try {
            if (!new File(Link.tempUrl).exists()) {
                new File(Link.tempUrl).createNewFile();
                Link.currentLink = Link.FirstMenu;
            } else {
                Link.currentLink = Link.MainMenu;
            }
            MainWindow mainWindow = new MainWindow();
            mainWindow.run();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
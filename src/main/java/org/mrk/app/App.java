package org.mrk.app;

import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;
import org.mrk.util.ThreadUtil;

import java.io.File;
import java.io.IOException;


public class App {
    public static void main(String[] args) {

        try {
            if (!new File(Link.TEMP_URL).exists()) {
                new File(Link.TEMP_URL).createNewFile();
                Link.currentLink = Link.WELCOME_MENU;
            } else {
                Link.currentLink = Link.LOGIN_MENU;
            }
            createThreadWithTask();
            MainWindow mainWindow = new MainWindow();
            mainWindow.run();


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void createThreadWithTask(){
        try {
            Thread taskRealizations = new Thread(new ThreadUtil(true));
            taskRealizations.setDaemon(true);
            taskRealizations.start();
            taskRealizations.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package org.mrk.app;

import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;
import org.mrk.util.ThreadUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class App {
    public static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        try {
            if (!new File(Link.TEMP_URL).exists()) {
                new File(Link.TEMP_URL).createNewFile();
                Link.currentLink = Link.WELCOME_MENU;
            } else {
                Link.currentLink = Link.LOGIN_MENU;
            }
            executor.execute(new ThreadUtil(true));
            MainWindow mainWindow = new MainWindow();
            mainWindow.run();


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
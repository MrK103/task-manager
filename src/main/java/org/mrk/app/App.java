package org.mrk.app;

import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;
import org.mrk.util.ThreadUtil;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class App {

    public static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

            Link.CURRENT_MENU = Link.TITLE_FRAME;
            new File(Link.TEMP_URL).mkdirs();
            executor.execute(new ThreadUtil(true));
            MainWindow mainWindow = new MainWindow();
            mainWindow.run();
    }
}
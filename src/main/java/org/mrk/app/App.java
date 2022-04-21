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

            Link.currentLink = Link.TITLE_FRAME;
            executor.execute(new ThreadUtil(true));
            MainWindow mainWindow = new MainWindow();
            mainWindow.run();
    }
}
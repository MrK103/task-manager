package org.mrk.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mrk.interfaces.Controller;
import org.mrk.util.ThreadUtil;

import javax.swing.*;

public abstract class ControllerImpl implements Controller {
    @FXML private static ImageView btnMinimize;
    @FXML private static ImageView btnClose;
    @FXML private static Pane titlePane;
    private static double x, y;
    private static Stage stage;

    public void controller(ImageView btnMinimize, ImageView btnClose, Pane titlePane, Stage stage) {
        ControllerImpl.btnMinimize = btnMinimize;
        ControllerImpl.btnClose = btnClose;
        ControllerImpl.titlePane = titlePane;
        ControllerImpl.stage = stage;
        init();
    }
    private void init(){
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        btnClose.setOnMouseClicked(mouseEvent -> {
            if (!ThreadUtil.listOfTask.isEmpty()) {
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Вы хотите оставить запущенные задачи работать?",
                        "Oops",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    stage.setIconified(true);
                    return;
                }
            }
            stage.close();
            System.exit(0);
            });

        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });
    }
}


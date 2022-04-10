package org.mrk.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mrk.interfaces.Controller;

import javax.swing.*;

public abstract class GeneralController implements Controller {
    @FXML private static ImageView btnMinimize;
    @FXML private static ImageView btnClose;
    @FXML private static Pane titlePane;
    private static double x, y;
    private static Stage stage;

    public void initGeneralController(ImageView btnMinimize, ImageView btnClose, Pane titlePane, Stage stage) {
        GeneralController.btnMinimize = btnMinimize;
        GeneralController.btnClose = btnClose;
        GeneralController.titlePane = titlePane;
        GeneralController.stage = stage;
        init();
    }
    private void init(){
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        btnClose.setOnMouseClicked(mouseEvent -> {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "Вы хотите оставить запущенные задачи работать?",
                    "Oops",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            stage.close();});

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


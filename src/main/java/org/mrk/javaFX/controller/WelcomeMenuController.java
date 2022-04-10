package org.mrk.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;

public class WelcomeMenuController extends GeneralController{
    @FXML
    private Pane mainPane, btnNext, titlePane;
    @FXML
    private ImageView btnMinimize, btnClose;

    public WelcomeMenuController() {
    }

    public void init(Stage stage) {
        initGeneralController(btnMinimize, btnClose, titlePane, stage);
        mainPane.setOnMouseEntered(event -> btnNext.setDisable(false));
        mainPane.setOnMouseExited(event -> btnNext.setDisable(true));

        btnNext.setOnMouseClicked(event -> {
            Link.currentLink = Link.LOGIN_MENU;
            MainWindow mainWindow = new MainWindow();
            try {
                mainWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

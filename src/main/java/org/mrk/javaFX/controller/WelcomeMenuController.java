package org.mrk.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mrk.util.Link;

public class WelcomeMenuController extends ControllerImpl {
    @FXML
    private Pane mainPane, btnNext;

    public WelcomeMenuController() {
    }

    public void init(Stage stage) {
        mainPane.setOnMouseEntered(event -> btnNext.setDisable(false));
        mainPane.setOnMouseExited(event -> btnNext.setDisable(true));

        btnNext.setOnMouseClicked(event -> {
            try {
                loadNext(Link.LOGIN_MENU, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

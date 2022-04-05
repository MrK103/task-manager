package org.mrk.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mrk.interfaces.Controller;
import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;

public class FirstMenuController implements Controller {
    @FXML
    private Pane mainPane, btnNext, titlePane;
    @FXML
    private ImageView btnMinimize, btnClose;
//    @FXML
//    private Label lbl1, lbl2, lbl3, lbl4;

    private double x, y;

    public FirstMenuController() {
    }

    public void init(Stage stage) {
        //перемещение окна
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        mainPane.setOnMouseEntered(event -> btnNext.setDisable(false));
        mainPane.setOnMouseExited(event -> btnNext.setDisable(true));

        btnNext.setOnMouseClicked(event -> {
            Link.currentLink = Link.MainMenu;
            MainWindow mainWindow = new MainWindow();
            try {
                mainWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

package org.mrk.javaFX.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.mrk.interfaces.Controller;
import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;
import org.mrk.util.UserUtil;

import java.util.TreeSet;

public class CreateUserMenuController implements Controller {
    @FXML private Pane titlePane, btnOK;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private TextField nameField, surNameField;
    @FXML private RadioButton radioBTN;
    @FXML private Label labelLastName, labelName;

    private double x, y;

    public CreateUserMenuController() {
    }

    public void init(Stage stage) {
        //перемещение окна
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        btnOK.setOnMouseClicked(mouserEvent -> {
            //проверка поля labelName
            if (nameField.getText().isEmpty()) {
                labelName.setTextFill(Color.web("#FF0000"));
                return;
            }else labelName.setTextFill(Color.web("#000000"));
            //проверка поля labelLastName
            if (surNameField.getText().isEmpty()) {
                labelLastName.setTextFill(Color.web("#FF0000"));
                return;
            }else labelLastName.setTextFill(Color.web("#000000"));

            UserUtil.createUserGui(nameField.getText(), surNameField.getText(), new TreeSet<>());
            MainWindow mainWindow = new MainWindow();
            if (radioBTN.isSelected()) Link.currentLink = Link.CREATE_TASK;
            else Link.currentLink = Link.LOGIN_MENU;
            try {
                mainWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
       });
    }
}
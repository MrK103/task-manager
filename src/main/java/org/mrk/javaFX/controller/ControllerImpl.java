package org.mrk.javaFX.controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.mrk.interfaces.Controller;
import org.mrk.util.Link;
import org.mrk.util.ThreadUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ControllerImpl implements Controller {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView btnMinimize;
    @FXML
    private ImageView btnClose;

    @FXML
    private Pane titlePane;
    private static AnchorPane anchorPaneMain;
    private static double x, y;
    private static ImageView btnCloseMain, btnMinimizeMain;
    private static Stage stageMain;
    private static Pane titlePaneMain;
    private static Scene scene;

    public void init(Stage stage) {
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
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        anchorPaneMain = anchorPane;
        titlePaneMain = titlePane;
        stageMain = stage;
        scene = btnClose.getScene();
        btnCloseMain = btnClose;
        btnMinimizeMain = btnMinimize;

        try {
            File tempDir = new File(Link.TEMP_URL);
            if (!new File(tempDir, "temp").exists()) {
                new File(tempDir, "temp").createNewFile();
                Link.CURRENT_MENU = Link.WELCOME_MENU;
            } else {
                Link.CURRENT_MENU = Link.LOGIN_MENU;
            }
            loadNext(Link.CURRENT_MENU, false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setWidth(double paneWidth, double btnWidth) {
        btnMinimizeMain.setX(btnWidth);
        btnCloseMain.setX(btnWidth);
        stageMain.setMaxWidth(paneWidth);
        anchorPaneMain.setMaxWidth(paneWidth);
        titlePaneMain.setMaxWidth(paneWidth);
        stageMain.setMinWidth(paneWidth);
        anchorPaneMain.setMinWidth(paneWidth);
        titlePaneMain.setMinWidth(paneWidth);
    }

    @FXML
    void loadNext(String url, boolean deleteBeforeAnimation) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        ((Controller) loader.getController()).init(stageMain);
        root.translateYProperty().set(scene.getHeight());

        if (deleteBeforeAnimation) {
            if (anchorPaneMain.getChildren().size() == 2) anchorPaneMain.getChildren().remove(1);
            else {
                anchorPaneMain.getChildren().remove(1);
                anchorPaneMain.getChildren().remove(1);
            }
        }
        anchorPaneMain.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 22, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            if (anchorPaneMain.getChildren().size() > 3) anchorPaneMain.getChildren().remove(2);
        });
        timeline.play();
    }

}


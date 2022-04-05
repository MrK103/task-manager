package org.mrk.javaFX.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mrk.interfaces.Controller;
import org.mrk.util.Link;

import java.util.Objects;

public class MainWindow extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Link.currentLink));
        Scene scene = new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        if (stage.getStyle() != StageStyle.TRANSPARENT) stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        //stage.setTitle("Calculator");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png"))));
        ((Controller)loader.getController()).init(stage);
        //((MainWindowController)loader.getController()).init(stage);
        stage.show();
    }

    public void run() {
        launch();
    }
}

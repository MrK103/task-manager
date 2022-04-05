package org.mrk.javaFX.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mrk.interfaces.Controller;
import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;
import org.mrk.util.FileUtil;
import org.mrk.util.UserUtil;

import java.util.Objects;

public class MainWindowController implements Controller {
    @FXML private Pane titlePane, btnNew, btnDel, btnOK;
    @FXML private ListView<String> listMenu;
    @FXML private ImageView btnMinimize, btnClose;

    private MultipleSelectionModel<String> listSelections;
    private double x, y;

    public MainWindowController() {
    }

    public void loadUsersList(){
        ObservableList<String> userNames = FXCollections.observableList(
                Objects.requireNonNull(FileUtil.loadUsersList()));
        listMenu.setItems(userNames);
    }
    public void init(Stage stage) {
        loadUsersList();
        listSelections = listMenu.getSelectionModel();

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

        btnNew.setOnMouseClicked(event -> {
            Link.currentLink = Link.UserMenu;
            MainWindow loginMenu = new MainWindow();
            try {
                loginMenu.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnOK.setOnMouseClicked(mouserEvent -> {
            if (listSelections.getSelectedItem()!=null){
            UserUtil.setUser(FileUtil.loadUserObj(listSelections.selectedItemProperty().get()));
            Link.currentLink = Link.TaskList;
            MainWindow mainWindow = new MainWindow();
            try {
                mainWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        });

        btnDel.setOnMouseClicked(event -> {
            if (listSelections.selectedItemProperty().get() != null) {
             FileUtil.deleteUser(listSelections.selectedItemProperty().get());
             loadUsersList();
            }
        });
    }
}

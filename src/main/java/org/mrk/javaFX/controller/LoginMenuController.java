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
import org.mrk.util.ServiceUtil;
import org.mrk.util.UserUtil;

import java.util.Objects;

public class LoginMenuController extends ControllerImpl implements Controller {
    @FXML private Pane titlePane, btnNew, btnDel, btnOK;
    @FXML private ListView<String> listMenu;
    @FXML private ImageView btnMinimize, btnClose;

    private MultipleSelectionModel<String> listSelections;

    public LoginMenuController() {
    }

    public void loadUsersList(){
        ObservableList<String> userNames = FXCollections.observableList(
                Objects.requireNonNull(ServiceUtil.loadUsersList()));
        listMenu.setItems(userNames);
    }
    public void init(Stage stage) {
        controller(btnMinimize, btnClose, titlePane, stage);
        loadUsersList();
        listSelections = listMenu.getSelectionModel();
        //перемещение окна

        btnNew.setOnMouseClicked(event -> {
            Link.currentLink = Link.CREATE_USER;
            MainWindow loginMenu = new MainWindow();
            try {
                loginMenu.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnOK.setOnMouseClicked(mouserEvent -> {
            if (listSelections.getSelectedItem()!=null){
            UserUtil.setCurrentUser(ServiceUtil.loadUser(listSelections.selectedItemProperty().get()));
            Link.currentLink = Link.MAIN_MENU;
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
             ServiceUtil.deleteUser(listSelections.selectedItemProperty().get());
             int id = listSelections.getSelectedIndex();
             if (id != 0) --id;
             loadUsersList();
             listSelections.select(id);
            }
        });

        }
    }


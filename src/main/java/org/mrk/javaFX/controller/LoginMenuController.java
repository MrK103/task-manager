package org.mrk.javaFX.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mrk.interfaces.Controller;
import org.mrk.util.Link;
import org.mrk.util.ServiceUtil;
import org.mrk.util.UserUtil;

import java.io.IOException;
import java.util.Objects;

public class LoginMenuController extends ControllerImpl implements Controller {

    @FXML
    private Pane btnNew, btnDel, btnOK;
    @FXML
    private ListView<String> listMenu;

    private MultipleSelectionModel<String> listSelections;

    public LoginMenuController() {
    }

    public void loadUsersList() {
        ObservableList<String> userNames = FXCollections.observableList(
                Objects.requireNonNull(ServiceUtil.loadUsersList()));
        listMenu.setItems(userNames);
    }


    public void init(Stage stage) {
        loadUsersList();
        listSelections = listMenu.getSelectionModel();

        btnNew.setOnMouseClicked(event -> {
            try {
                loadNext(Link.CREATE_USER, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnOK.setOnMouseClicked(mouserEvent -> {
            if (listSelections.getSelectedItem() != null) {
                UserUtil.setCurrentUser(ServiceUtil.loadUser(listSelections.selectedItemProperty().get()));
                try {
                    setWidth(460, 130);
                    loadNext(Link.MAIN_MENU, true);
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


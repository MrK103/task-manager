package org.mrk.javaFX.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.mrk.interfaces.Task;
import org.mrk.enums.SortMenu;
import org.mrk.util.*;
import org.mrk.enums.Category;

import java.util.Arrays;
import java.util.Comparator;

public class MainMenuController extends ControllerImpl {
    @FXML
    private Label lblDel, lblAdd, lblExit, lblUpdate;
    @FXML
    private Pane btnBack, btnAdd, btnDel, btnUpdate;
    @FXML
    private ListView<Task> listMenu;
    @FXML
    private ChoiceBox<Category> filterMenu;
    @FXML
    private ChoiceBox<SortMenu> sortMenu;

    private MultipleSelectionModel<Task> listSelections; //модель listMenu
    private ObservableList<Task> taskList = FXCollections.observableList(
            UserUtil.getCurrentUser().getTasks().stream().toList()); //лист с задачми obj
    private final ObservableList<Category> filterList = FXCollections.observableArrayList(
            Arrays.asList(Category.ALL, Category.ONCE, Category.REPEATS));
    private final ObservableList<SortMenu> sortedList = FXCollections.observableArrayList(
            Arrays.asList(SortMenu.DATE, SortMenu.NAME, SortMenu.PRIORITY));

    public MainMenuController() {
    }

    public void init(Stage stage) {
        listMenu.setItems(taskList);
        listSelections = listMenu.getSelectionModel();
        //Фильтрация
        filterMenu.setItems(filterList);
        filterMenu.setValue(filterList.get(0));
        filterMenu.setOnAction(event -> setFilter(filterMenu.getValue()));

        //Сортировка
        sortMenu.setItems(sortedList);
        sortMenu.setValue(sortedList.get(0));
        sortMenu.setOnAction(event -> sortList(sortMenu.getValue()));

        btnAdd.setOnMouseClicked(event -> {
            try {
                setWidth(324, 0);
                loadNext(Link.CREATE_TASK, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnUpdate.setOnMouseClicked(event -> setFilter(filterMenu.getValue()));


        btnDel.setOnMouseClicked(event -> {
                    if (listSelections.getSelectedItem() != null) {
                        UserUtil.getCurrentUser().getTasks().remove(listSelections.getSelectedItem());
                        int id = listSelections.getSelectedIndex();
                        if (id != 0) --id;
                        ServiceUtil.saveUser(UserUtil.getCurrentUser());
                        //
                        Thread thread = new Thread(new ThreadUtil(listSelections.getSelectedItem().getIdTask()));
                        thread.setDaemon(true);
                        thread.start();
                        //
                        setFilter(filterMenu.getValue());
                        listSelections.select(id);
                    }
                }

        );

        btnBack.setOnMouseClicked(event -> {
            try {
                setWidth(324, 0);
                loadNext(Link.LOGIN_MENU, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        stage.setOnShowing(event -> System.err.println("Start"));
    }


    public void setFilter(Category category) {
        switch (category) {
            case ALL -> {
                taskList = FXCollections.observableList(
                        UserUtil
                                .getCurrentUser()
                                .getTasks()
                                .stream()
                                .toList());
                sortList(sortMenu.getValue());
            }
            case ONCE -> {
                taskList = FXCollections.observableList(
                        UserUtil
                                .getCurrentUser()
                                .getTasks()
                                .stream()
                                .filter(AbstractTask -> AbstractTask.getCategory().equals(Category.ONCE))
                                .toList());
                sortList(sortMenu.getValue());
            }
            case REPEATS -> {
                taskList = FXCollections.observableList(
                        UserUtil
                                .getCurrentUser()
                                .getTasks()
                                .stream()
                                .filter(AbstractTask -> AbstractTask.getCategory().equals(Category.REPEATS))
                                .toList());
                sortList(sortMenu.getValue());
            }
        }
    }

    public void sortList(SortMenu sortMenu) {
        switch (sortMenu) {
            case DATE -> listMenu.setItems(FXCollections.observableList(taskList.stream()
                    .sorted(Comparator.comparing(Task::getDate)).toList()));
            case NAME -> listMenu.setItems(FXCollections.observableList(taskList.stream()
                    .sorted(Comparator.comparing(Task::getName)).toList()));
            case PRIORITY -> listMenu.setItems(FXCollections.observableList(taskList.stream()
                    .sorted(Comparator.comparing(Task::getPriority)).toList()));
        }
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(btnAdd)) lblAdd.setTextFill(Color.web("#ff00bf"));
        else if (mouseEvent.getSource().equals(btnDel)) lblDel.setTextFill(Color.web("#ff00bf"));
        else if (mouseEvent.getSource().equals(btnUpdate)) lblUpdate.setTextFill(Color.web("#ff00bf"));
        else if (mouseEvent.getSource().equals(btnBack)) lblExit.setTextFill(Color.web("#ff00bf"));
    }

    public void onMouseExit(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(btnAdd)) lblAdd.setTextFill(Color.web("#ff75ef"));
        else if (mouseEvent.getSource().equals(btnDel)) lblDel.setTextFill(Color.web("#ff75ef"));
        else if (mouseEvent.getSource().equals(btnUpdate)) lblUpdate.setTextFill(Color.web("#ff75ef"));
        else if (mouseEvent.getSource().equals(btnBack)) lblExit.setTextFill(Color.web("#ff75ef"));
    }
}
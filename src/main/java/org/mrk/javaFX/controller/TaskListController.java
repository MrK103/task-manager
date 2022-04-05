package org.mrk.javaFX.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mrk.interfaces.Controller;
import org.mrk.interfaces.Task;
import org.mrk.enums.SortMenu;
import org.mrk.util.Link;
import org.mrk.javaFX.ui.MainWindow;
import org.mrk.enums.Category;
import org.mrk.util.FileUtil;
import org.mrk.util.UserUtil;

import java.util.Arrays;
import java.util.Comparator;

public class TaskListController implements Controller {
    @FXML
    private Pane titlePane, btnBack, btnAdd, btnDel;
    @FXML
    private ListView<Task> listMenu;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private ChoiceBox<Category> filterMenu;
    @FXML
    private ChoiceBox<SortMenu> sortMenu;
    @FXML
    private Label userName;

    private MultipleSelectionModel<Task> listSelections; //модель listMenu
    private ObservableList<Task> taskList; //лист с задачми obj
    private final ObservableList<Category> filterList = FXCollections.observableArrayList(
            Arrays.asList(Category.ALL, Category.ONCE, Category.REPEATS));
    private final ObservableList<SortMenu> sortedList = FXCollections.observableArrayList(
            Arrays.asList(SortMenu.DATE, SortMenu.NAME, SortMenu.PRIORITY));

    private double x, y;

    public TaskListController() {
    }

    public void setTaskList(ObservableList<Task> taskList1){
        listMenu.setItems(taskList1);
        listSelections = listMenu.getSelectionModel();
    }

    public void init(Stage stage) {
        userName.setText(UserUtil.getUser().getFirstName() + " " +  UserUtil.getUser().getLastName());
        taskList = FXCollections.observableList(UserUtil.getUser().getTasks().stream().toList());
        setTaskList(taskList);

        //Фильтрация
        filterMenu.setItems(filterList);
        filterMenu.setValue(filterList.get(0));
        filterMenu.setOnAction(event -> setFilter(filterMenu.getValue()));

        //Сортировка
        sortMenu.setItems(sortedList);
        sortMenu.setValue(sortedList.get(0));
        sortMenu.setOnAction(event -> sortList(sortMenu.getValue()));

        btnAdd.setOnMouseClicked(event -> {
            Link.currentLink = Link.TaskMenu;
            MainWindow taskMenuWindow = new MainWindow();
            try {
                taskMenuWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnDel.setOnMouseClicked(event -> {
            if (listSelections.getSelectedItem() != null) {
                UserUtil.getUser().getTasks().remove(listSelections.getSelectedItem());
                FileUtil.saveUserObj(UserUtil.getUser());
                setFilter(filterMenu.getValue());
            }
            }

        );

        //перемещение окна
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnBack.setOnMouseClicked(event -> {
            Link.currentLink = Link.MainMenu;
            MainWindow mainWindow = new MainWindow();
            try {
                mainWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    public void setFilter(Category category){
        switch (category){
            case ALL -> {
                taskList = FXCollections.observableList(
                        UserUtil
                        .getUser()
                        .getTasks()
                        .stream()
                        .toList());
                sortList(sortMenu.getValue());
            }
            case ONCE -> {
                taskList = FXCollections.observableList(
                        UserUtil
                        .getUser()
                        .getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getCategory().equals(Category.ONCE))
                        .toList());
                sortList(sortMenu.getValue());
            }
            case REPEATS -> {
                taskList = FXCollections.observableList(
                        UserUtil
                        .getUser()
                        .getTasks()
                        .stream()
                        .filter(AbstractTask -> AbstractTask.getCategory().equals(Category.REPEATS))
                        .toList());
                sortList(sortMenu.getValue());
            }
        }
    }

    public void sortList(SortMenu sortMenu){
        switch (sortMenu) {
            case DATE -> setTaskList(FXCollections.observableList(taskList.stream()
                    .sorted(Comparator.comparing(Task::getDate)).toList()));
            case NAME -> setTaskList(FXCollections.observableList(taskList.stream()
                    .sorted(Comparator.comparing(Task::getName)).toList()));
            case PRIORITY -> setTaskList(FXCollections.observableList(taskList.stream()
                    .sorted(Comparator.comparing(Task::getPriority)).toList()));
        }
    }
}
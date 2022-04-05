package org.mrk.javaFX.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.mrk.interfaces.Controller;
import org.mrk.javaFX.ui.MainWindow;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;
import org.mrk.util.*;

import java.util.Arrays;

public class TaskMenuController implements Controller {
    @FXML
    public DatePicker datePicker;
    @FXML
    private Pane titlePane, btnOK;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private TextField nameField, dateField, repeatsField, timesRepField;
    @FXML
    private ChoiceBox<Priority> boxPriority;
    @FXML
    private ChoiceBox<Category> boxCategory;
    @FXML
    private Label labelTimeRep, labelRep, labelDate, labelTaskName;

    private double x, y;
    private final ObservableList<Priority> listPriority =
            FXCollections.observableArrayList(Arrays.asList(Priority.HIGH, Priority.DEFAULT, Priority.LOW));
    private final ObservableList<Category> listCategory =
            FXCollections.observableArrayList(Arrays.asList(Category.ONCE, Category.REPEATS));

    public TaskMenuController() {
    }

    public void init(Stage stage) {



        boxPriority.setItems(listPriority);
        boxPriority.setValue(listPriority.get(0));

        boxCategory.setItems(listCategory);
        boxCategory.setValue(listCategory.get(0));
        setInvisibleFiled();

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

        boxCategory.setOnAction(event -> {
            if (boxCategory.getValue().equals(Category.ONCE)){
                setInvisibleFiled();
            } else {
                repeatsField.setVisible(true);
                timesRepField.setVisible(true);
                labelTimeRep.setVisible(true);
                labelRep.setVisible(true);
            }
        });
        btnOK.setOnMouseClicked(mouserEvent -> {
            //проверка поля имя
            if (nameField.getText().isEmpty()){
                labelTaskName.setTextFill(Color.web("#FF0000"));
                return;
            } else labelTaskName.setTextFill(Color.web("#000000"));

            //проверка поля дата
            if(Util.setDate(datePicker.getValue(), dateField.getText())==null) { //неправильная дата
                labelDate.setTextFill(Color.web("#FF0000"));
                return;
            } else labelDate.setTextFill(Color.web("#000000"));

            if (boxCategory.getValue().equals(Category.REPEATS)) {
                //проверка полей повтора
                if (Util.validInt(repeatsField.getText()) == -1 || Util.validInt(timesRepField.getText()) == -1){
                    labelRep.setTextFill(Color.web("#FF0000"));
                    labelTimeRep.setTextFill(Color.web("#FF0000"));
                    return;
                }
                UserUtil.getUser().getTasks().add(
                        TaskUtil.addTaskRepeats(
                                nameField.getText()
                                , Category.REPEATS
                                , boxPriority.getValue()
                                , Util.setDate(datePicker.getValue(), dateField.getText())
                                ,Integer.parseInt(repeatsField.getText())
                                ,Integer.parseInt(timesRepField.getText())
                        ));
            } else {
                UserUtil.getUser().getTasks().add(
                        TaskUtil.addTaskOnce(
                                nameField.getText()
                                , Category.ONCE
                                , boxPriority.getValue()
                                , Util.setDate(datePicker.getValue(), dateField.getText())));
            }
            FileUtil.saveUserObj(UserUtil.getUser());
            Link.currentLink = Link.TaskList;
            MainWindow mainWindow = new MainWindow();
            try {
                mainWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            });
    }

    private void setInvisibleFiled(){
        repeatsField.setVisible(false);
        timesRepField.setVisible(false);
        labelTimeRep.setVisible(false);
        labelRep.setVisible(false);
    }
}
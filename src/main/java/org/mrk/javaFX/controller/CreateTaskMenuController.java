package org.mrk.javaFX.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.mrk.javaFX.ui.MainWindow;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;
import org.mrk.util.*;

import java.util.Arrays;
import java.util.Date;

public class CreateTaskMenuController extends GeneralController{
    @FXML
    public DatePicker datePicker;
    @FXML
    private Pane titlePane, btnOK;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private TextField nameField, minutesField, hoursField, repeatsField, timesRepField;
    @FXML
    private ChoiceBox<Priority> boxPriority;
    @FXML
    private ChoiceBox<Category> boxCategory;
    @FXML
    private Label labelTimeRep, labelRep, labelDate, labelTaskName, labelTitle;

    private final ObservableList<Priority> listPriority =
            FXCollections.observableArrayList(Arrays.asList(Priority.HIGH, Priority.DEFAULT, Priority.LOW));
    private final ObservableList<Category> listCategory =
            FXCollections.observableArrayList(Arrays.asList(Category.ONCE, Category.REPEATS));

    public CreateTaskMenuController() {
    }

    public void init(Stage stage) {
        initGeneralController(btnMinimize, btnClose, titlePane, stage);

        labelTitle.setText(UserUtil.getCurrentUser().getFirstName() + " " + UserUtil.getCurrentUser().getLastName());

        boxPriority.setItems(listPriority);
        boxPriority.setValue(listPriority.get(0));

        boxCategory.setItems(listCategory);
        boxCategory.setValue(listCategory.get(0));
        boxCategory.setOnAction(event -> setVisibleForRepeatsField(!boxCategory.getValue().equals(Category.ONCE)));

        btnOK.setOnMouseClicked(mouserEvent -> {
            //проверка поля имя
            if (nameField.getText().isEmpty()){
                labelTaskName.setTextFill(Color.web("#FF0000"));
                return;
            } else labelTaskName.setTextFill(Color.web("#000000"));

            //проверка поля дата
            Date date;
            if((date = Util.setDate(datePicker.getValue(), hoursField.getText(), minutesField.getText()))==null) { //неправильная дата
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
                UserUtil.getCurrentUser().getTasks().add(
                        TaskUtil.addTaskRepeats(
                                nameField.getText()
                                , Category.REPEATS
                                , boxPriority.getValue()
                                , date
                                ,Integer.parseInt(repeatsField.getText())
                                ,Integer.parseInt(timesRepField.getText() ) * 1000
                        ));
            } else {
                UserUtil.getCurrentUser().getTasks().add(
                        TaskUtil.addTaskOnce(
                                nameField.getText()
                                , Category.ONCE
                                , boxPriority.getValue()
                                , date
                        ));
            }

            FileUtil.saveUserObj(UserUtil.getCurrentUser());

            Thread t1 = new Thread(new ThreadUtil(false));
            t1.setDaemon(true);
            t1.start();

            Link.currentLink = Link.MAIN_MENU;
            MainWindow mainWindow = new MainWindow();
            try {
                mainWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            });
    }

    private void setVisibleForRepeatsField(boolean visible){
        repeatsField.setVisible(visible);
        timesRepField.setVisible(visible);
        labelTimeRep.setVisible(visible);
        labelRep.setVisible(visible);
    }
}
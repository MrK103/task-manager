package org.mrk.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Link {
    public static String currentLink;
    public final static String LOGIN_MENU = "/fxml/LoginMenuInterface.fxml";
    public final static String MAIN_MENU = "/fxml/MainMenuInterface.fxml";
    public final static String CREATE_TASK = "/fxml/CreateTaskMenuInterface.fxml";
    public final static String CREATE_USER = "/fxml/CreateUserInterface.fxml";
    public final static String WELCOME_MENU = "/fxml/WelcomeMenuInterface.fxml";
    public final static String TEMP_URL = "src/main/resources/temp/temp";
    public final static String USER_NAME_PATH = "src/main/resources/users/users.txt";

}

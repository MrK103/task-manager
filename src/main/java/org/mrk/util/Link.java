package org.mrk.util;

import lombok.experimental.UtilityClass;

import java.net.URISyntaxException;

@UtilityClass
public class Link {
    public static String CURRENT_MENU;
    public final static String LOGIN_MENU = "/fxml/LoginMenuInterface.fxml";
    public final static String MAIN_MENU = "/fxml/MainMenuInterface.fxml";
    public final static String CREATE_TASK = "/fxml/CreateTaskMenuInterface.fxml";
    public final static String CREATE_USER = "/fxml/CreateUserInterface.fxml";
    public final static String TITLE_FRAME = "/fxml/TitleFrameInterface.fxml";
    public final static String WELCOME_MENU = "/fxml/WelcomeMenuInterface.fxml";
    public final static String USER_NAME_PATH = "users.txt";
    public final static String NOTIFICATION_SOUND_DEFAULT = "/sound/song.mp3";
    public String TEMP_URL = null;

    static {
        try {
            TEMP_URL = Link.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if (TEMP_URL.contains(".jar")) TEMP_URL = TEMP_URL.substring(0, TEMP_URL.length() - 4);
            else TEMP_URL = TEMP_URL + "task-manager";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    //File tempDir = new File(path, "task-manager");
}

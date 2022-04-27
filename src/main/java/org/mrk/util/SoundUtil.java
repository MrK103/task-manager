package org.mrk.util;

import javazoom.jlgui.basicplayer.*;
import lombok.experimental.UtilityClass;

import java.io.*;

@UtilityClass
public class SoundUtil {

    static {
        try {
            InputStream song = SoundUtil.class.getResourceAsStream(Link.NOTIFICATION_SOUND_DEFAULT);
            OutputStream tempSong = new FileOutputStream(new File(Link.TEMP_URL,"song.mp3"));
            if (song != null) tempSong.write(song.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final BasicPlayer basicPlayer = new BasicPlayer(); // библиотека для проигрывания мп3
    private static final File songDef = new File(Link.TEMP_URL,"song.mp3");

    public static void playSound() {
        try {
            basicPlayer.open(songDef);
            basicPlayer.play();
        } catch (BasicPlayerException exc) {
            exc.printStackTrace();
        }
    }
}
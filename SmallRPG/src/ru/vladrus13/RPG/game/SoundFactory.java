package ru.vladrus13.RPG.game;

import com.goxr3plus.streamplayer.stream.StreamPlayerException;
import ru.vladrus13.RPG.core.sound.Sound;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vladkuznetsov
 * Sound factory
 */
public class SoundFactory {
    /**
     * Sounds map
     */
    private final Map<String, Sound> sounds;
    /**
     * Played sounds
     */
    private final ArrayList<Sound> played;

    /**
     * Constructor for class
     */
    public SoundFactory() {
        sounds = new HashMap<>();
        played = new ArrayList<>();
        loadMp3("MainTheme");
    }

    /**
     * Load mp3 file
     *
     * @param name name of file
     */
    private void loadMp3(String name) {
        sounds.put(name, new Sound(new File("assets/sounds/" + name + ".mp3")));
    }

    /**
     * Play sound with this name
     *
     * @param name name
     */
    public void play(String name) {
        played.add(sounds.get(name));
        try {
            sounds.get(name).play();
        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pause sound with this name
     *
     * @param name name
     */
    public void pause(String name) {
        sounds.get(name).pause();
    }

    /**
     * Resume sound with this name
     *
     * @param name name
     */
    public void resume(String name) {
        sounds.get(name).resume();
    }

    /**
     * Stop sound with this name
     *
     * @param name name
     */
    public void stop(String name) {
        if (played.contains(sounds.get(name))) {
            played.remove(sounds.get(name));
            sounds.get(name).stop();
        }
    }
}

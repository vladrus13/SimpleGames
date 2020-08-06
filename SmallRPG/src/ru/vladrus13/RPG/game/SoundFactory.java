package ru.vladrus13.RPG.game;

import com.goxr3plus.streamplayer.stream.StreamPlayerException;
import ru.vladrus13.RPG.core.sound.Sound;

import java.io.File;
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
     * Constructor for class
     */
    public SoundFactory() {
        sounds = new HashMap<>();
        loadMp3("MainTheme");
    }

    /**
     * Load mp3 file
     *
     * @param name name of file
     */
    private void loadMp3(String name) {
        sounds.put(name, getMp3(name));
    }

    /**
     * Get mp3 sound
     *
     * @param name name of file
     * @return {@link Sound}
     */
    private Sound getMp3(String name) {
        return new Sound(new File("resources/assets/sounds/" + name + ".mp3"));
    }

    /**
     * Play sound with this name
     *
     * @param name name
     */
    public void play(String name) {
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
        if (sounds.get(name).isPlaying()) {
            sounds.get(name).stop();
        }
    }

    /**
     * @param name name of sound
     * @return {@link Sound} - cloned element
     */
    public Sound getCloned(String name) {
        return getMp3(name);
    }
}

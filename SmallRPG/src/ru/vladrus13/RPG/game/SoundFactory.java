package ru.vladrus13.RPG.game;

import javazoom.jl.decoder.JavaLayerException;
import ru.vladrus13.RPG.core.sound.Sound;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoundFactory {
    private final Map<String, Sound> sounds;
    private final ArrayList<Sound> played;

    public SoundFactory() {
        sounds = new HashMap<>();
        played = new ArrayList<>();
        loadMp3("MainTheme");
    }

    private void loadMp3(String name) {
        sounds.put(name, new Sound(new File("assets/sounds/" + name + ".mp3")));
    }

    public void play(String name) {
        played.add(sounds.get(name));
        sounds.get(name).play();
    }

    public void stop(String name) {
        if (played.contains(sounds.get(name))) {
            played.remove(sounds.get(name));
            sounds.get(name).stop();
        }
    }
}

package ru.vladrus13.RPG.core.sound;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Sound implements AutoCloseable {

    private boolean released = false;
    private boolean playing = false;
    private Thread thread;
    private Player player;

    public Sound(File f) {
        try {
            FileInputStream fileInputStream = new FileInputStream(f);
            player = new Player(fileInputStream);
            released = true;
        } catch (IOException | JavaLayerException exc) {
            exc.printStackTrace();
            released = false;
            close();
        }
    }


    public boolean isReleased() {
        return released;
    }

    public boolean isPlaying() {
        return player.isComplete();
    }

    public void play() {
        Runnable runnable = () -> {
            if (released) {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        };
        // TODO very bad. VERY VERY VERY
        thread = new Thread(runnable);
        thread.start();
    }

    public void stop() {
        if (playing) {
            player.close();
        }
        thread.interrupt();
    }

    @Override
    public void close() {
        player.close();
    }
}

package ru.vladrus13.RPG.core.sound;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;
import ru.vladrus13.RPG.core.utils.EmptyStream;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author vladkuznetsov
 * Sound-class for playing mp3 or wav
 */
public class Sound extends StreamPlayer implements StreamPlayerListener {

    /**
     * Constructor
     *
     * @param file file .mp3 or .wav
     */
    public Sound(File file) {
        try {
            open(file);
        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unsupported
     *
     * @param o   Object
     * @param map Map
     */
    @Override
    public void opened(Object o, Map<String, Object> map) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported
     *
     * @param i     int
     * @param l     long
     * @param bytes bytes
     * @param map   map
     */
    @Override
    public void progress(int i, long l, byte[] bytes, Map<String, Object> map) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported
     *
     * @param streamPlayerEvent streamPlayerEvent
     */
    @Override
    public void statusUpdated(StreamPlayerEvent streamPlayerEvent) {
        throw new UnsupportedOperationException();
    }

    /**
     * Play sound with gain
     * @param gain gain
     * @throws StreamPlayerException if playing have some problems
     */
    public void play(double gain) throws StreamPlayerException {
        this.play();
        this.setGain(gain);
    }
}

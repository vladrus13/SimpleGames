package ru.vladrus13.RPG.core.sound;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;

import java.io.File;
import java.util.Map;

public class Sound extends StreamPlayer implements StreamPlayerListener {

    public Sound(File file) {
        try {
            open(file);
        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void opened(Object o, Map<String, Object> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void progress(int i, long l, byte[] bytes, Map<String, Object> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void statusUpdated(StreamPlayerEvent streamPlayerEvent) {
        throw new UnsupportedOperationException();
    }
}

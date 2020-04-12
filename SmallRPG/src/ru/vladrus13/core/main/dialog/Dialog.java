package ru.vladrus13.core.main.dialog;

import ru.vladrus13.core.person.Person;
import ru.vladrus13.core.utils.Drawing;
import ru.vladrus13.core.utils.GameService;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.FontService;
import ru.vladrus13.core.utils.picture.PictureService;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Dialog implements Drawing {
    private final ArrayList<Monolog> monologs;
    int current;

    public Dialog(String[] text, Person[] author, GameService gameService) throws GameException {
        if (text.length != author.length) {
            throw new GameException("Text and author sizes aren't equals!");
        }
        ArrayList<Monolog> monologs1 = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            monologs1.add(new Monolog(text[i], author[i], gameService));
        }
        monologs = monologs1;
    }

    public boolean hasNext() {
        return current + 1 < monologs.size();
    }

    public void next() {
        if (hasNext()) current++;
    }

    @Override
    public void draw(Graphics graphics) {
        monologs.get(current).draw(graphics);
    }
}

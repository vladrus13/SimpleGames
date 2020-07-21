package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.Drawing;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.util.ArrayList;

public class Dialog implements Drawing {
    private final ArrayList<Monologue> monologues;
    int current;

    public Dialog(String[] text, Person[] author, DungeonService dungeonService) throws GameException {
        if (text.length != author.length) {
            throw new GameException("Text and author sizes aren't equals!");
        }
        ArrayList<Monologue> monologues1 = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            monologues1.add(new Monologue(text[i], author[i], dungeonService));
        }
        monologues = monologues1;
    }

    public boolean hasNext() {
        return current + 1 < monologues.size();
    }

    public void next() {
        if (hasNext()) current++;
    }

    @Override
    public void draw(Graphics graphics) {
        monologues.get(current).draw(graphics);
    }
}

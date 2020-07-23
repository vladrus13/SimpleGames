package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Dialog extends Drawing implements KeyTaker {
    private final ArrayList<Monologue> monologues;
    private final DungeonService dungeonService;
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
        this.dungeonService = dungeonService;
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

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            if (hasNext()) {
                next();
            } else {
                dungeonService.getDungeon().removeDrawing(this);
                try {
                    dungeonService.getDungeon().removeFocus(this);
                } catch (GameException gameException) {
                    gameException.printStackTrace();
                }
            }
        }
    }
}

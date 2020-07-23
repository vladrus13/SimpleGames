package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class Dialog extends Drawing implements KeyTaker {
    private final ArrayList<Monologue> monologues;
    private final DungeonService dungeonService;
    private final Consumer<DungeonService> afterEnd;
    int current = 0;

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
        this.afterEnd = null;
    }

    public Dialog(String[] text, Person[] author, DungeonService dungeonService, Consumer<DungeonService> afterEnd) throws GameException {
        if (text.length != author.length) {
            throw new GameException("Text and author sizes aren't equals!");
        }
        ArrayList<Monologue> monologues1 = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            monologues1.add(new Monologue(text[i], author[i], dungeonService));
        }
        monologues = monologues1;
        this.dungeonService = dungeonService;
        this.afterEnd = afterEnd;
    }

    public Dialog(String text, Person author, DungeonService dungeonService, Consumer<DungeonService> afterEnd) throws GameException {
        monologues = new ArrayList<>(Collections.singleton(new Monologue(text, author, dungeonService)));
        this.dungeonService = dungeonService;
        this.afterEnd = afterEnd;
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
                if (afterEnd != null) {
                    afterEnd.accept(dungeonService);
                }
            }
        }
    }
}

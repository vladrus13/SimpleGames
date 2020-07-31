package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * Dialog class for persons
 */
public class Dialog extends Drawing implements KeyTaker {
    /**
     * {@link Monologue} monologues
     */
    private final ArrayList<Monologue> monologues;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * Calls, if dialog end
     */
    private final Consumer<DungeonService> afterEnd;
    /**
     * Current monologue
     */
    int current = 0;

    /**
     * Constructor of class
     *
     * @param text           array of texts
     * @param author         array of authors
     * @param dungeonService {@link DungeonService}
     * @throws GameException if {@link Monologue} constructor is throw it, or text and author is different lengths
     */
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

    /**
     * Constructor of class
     *
     * @param text           array of texts
     * @param author         array of authors
     * @param dungeonService {@link DungeonService}
     * @param afterEnd       calls, if dialog end
     * @throws GameException if {@link Monologue} constructor is throw it, or text and author is different lengths
     */
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

    /**
     * Constructor of class
     *
     * @param text           text
     * @param author         author
     * @param dungeonService {@link DungeonService}
     * @param afterEnd       calls, if dialog end
     * @throws GameException if {@link Monologue} constructor is throw it
     */
    public Dialog(String text, Person author, DungeonService dungeonService, Consumer<DungeonService> afterEnd) throws GameException {
        monologues = new ArrayList<>(Collections.singleton(new Monologue(text, author, dungeonService)));
        this.dungeonService = dungeonService;
        this.afterEnd = afterEnd;
    }

    /**
     * Constructor of class
     *
     * @param text           text
     * @param author         author
     * @param dungeonService {@link DungeonService}
     * @throws GameException if {@link Monologue} constructor is throw it
     */
    public Dialog(String text, Person author, DungeonService dungeonService) throws GameException {
        monologues = new ArrayList<>(Collections.singleton(new Monologue(text, author, dungeonService)));
        this.dungeonService = dungeonService;
        this.afterEnd = null;
    }

    /**
     * Has next monologue
     *
     * @return true, if exist, else no
     */
    public boolean hasNext() {
        return current + 1 < monologues.size();
    }

    /**
     * Go to next monologue
     */
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

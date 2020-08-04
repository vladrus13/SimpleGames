package ru.vladrus13.RPG.core;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.graphics.MouseTaker;
import ru.vladrus13.RPG.core.graphics.Updating;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.debug.DebugEvent;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author vladkuznetsov
 * Dungeon class
 */
public class Dungeon extends Drawing implements Updating, KeyTaker, MouseTaker {

    /**
     * Drawings objects - floors, persons, animations, etc.
     */
    private final LinkedList<Drawing> drawings;
    /**
     * Dead objects - which we don't draw, just for remove from drawings
     */
    private final ConcurrentLinkedDeque<Drawing> dead;
    /**
     * Focus elements - which get a keyboard command. Keyboard command get only a peek element
     */
    private final Deque<KeyTaker> focus;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * {@link Game}
     */
    private final Game game;
    /**
     * {@link DebugEvent}
     */
    private final DebugEvent debugEvent;

    /**
     * Constructor for class
     *
     * @param game {@link Game}
     * @throws GameException if we get problems with creating dungeon service
     */
    public Dungeon(Game game) throws GameException {
        this.game = game;
        dungeonService = new DungeonService(this);
        drawings = new LinkedList<>();
        dungeonService.setCurrentFloor(0);
        drawings.addAll(Arrays.asList(dungeonService.getHero(), dungeonService.getDialog(), dungeonService.getShortMenu()));
        focus = new ArrayDeque<>();
        dead = new ConcurrentLinkedDeque<>();
        focus.add(dungeonService.getHero());
        debugEvent = new DebugEvent(dungeonService);

        // dungeonService.getSoundFactory().play("MainTheme");

        dungeonService.getEventFactory().get("onStart").run(dungeonService);
    }

    /**
     * Getter for dungeon service
     *
     * @return {@link DungeonService}
     */
    public DungeonService getDungeonService() {
        return dungeonService;
    }

    @Override
    public void draw(Graphics graphics) {
        for (Drawing drawing : drawings) {
            if (drawing != null && drawing.isDrawing()) {
                drawing.draw(graphics);
            }
        }
    }

    @Override
    public void update(DungeonService dungeonService, long time) {
        drawings.removeAll(dead);
        dead.clear();
        for (Drawing drawing : drawings) {
            if (drawing instanceof Updating && !drawing.isPause()) {
                ((Updating) drawing).update(dungeonService, time);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!focus.isEmpty()) {
            focus.getFirst().keyPressed(e);
        }
        debugEvent.keyPressed(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!focus.isEmpty() && focus.getFirst() instanceof MouseTaker) {
            ((MouseTaker) focus.getFirst()).mousePressed(e);
        }
    }

    /**
     * Remove short menu from drawings
     *
     * @throws GameException if we can't remove focus
     */
    public void notShortMenu() throws GameException {
        dungeonService.getShortMenu().setDrawing(false);
        removeFocus(dungeonService.getShortMenu());
    }

    /**
     * Set game to menu status
     */
    public void exitToMenu() {
        game.setStatusGame(Game.STATUS_GAME.MENU);
    }

    /**
     * Add element to drawing
     *
     * @param drawing {@link Drawing}
     */
    public void addDrawing(Drawing drawing) {
        drawings.add(drawing);
    }

    /**
     * Remove drawing
     *
     * @param drawing removed element - {@link Drawing}
     */
    public void removeDrawing(Drawing drawing) {
        dead.add(drawing);
        // drawings.remove(drawing);
    }

    /**
     * Replace drawing, if from doesn't exist, just add
     *
     * @param from from which we replace - {@link Drawing}
     * @param to   to what we replace - {@link Drawing}
     */
    public void replaceDrawing(Drawing from, Drawing to) {
        if (from == null) {
            drawings.add(to);
        } else {
            int pos = drawings.indexOf(from);
            if (pos == -1) {
                addDrawing(to);
            } else {
                drawings.set(pos, to);
            }
        }
    }

    /**
     * Add to peek on focus hierarchy
     *
     * @param keyTaker element we add
     */
    public void addFocus(KeyTaker keyTaker) {
        this.focus.addFirst(keyTaker);
    }

    /**
     * Remove form peek on focus hierarchy
     *
     * @param keyTaker element we remove
     * @throws GameException if element not on peek or doesn't exists on focus
     */
    public void removeFocus(KeyTaker keyTaker) throws GameException {
        if (focus.getFirst() != keyTaker) {
            throw new GameException(keyTaker.toString() + "is not first on focus!");
        } else {
            focus.pollFirst();
        }
    }
}

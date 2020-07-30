package ru.vladrus13.RPG.core;

import ru.vladrus13.RPG.core.utils.debug.DebugEvent;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;
import ru.vladrus13.RPG.core.utils.picture.MouseTaker;
import ru.vladrus13.RPG.core.utils.picture.Updating;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Dungeon {

    private final LinkedList<Drawing> drawings;
    private final ConcurrentLinkedDeque<Drawing> dead;
    private final Deque<KeyTaker> focus;
    private final DungeonService dungeonService;
    private final Game game;
    private final DebugEvent debugEvent;

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

    public DungeonService getDungeonService() {
        return dungeonService;
    }

    public void draw(Graphics graphics) {
        for (Drawing drawing : drawings) {
            if (drawing != null && drawing.isDrawing()) {
                drawing.draw(graphics);
            }
        }
    }

    public void update(DungeonService dungeonService, long time) {
        drawings.removeAll(dead);
        dead.clear();
        for (Drawing drawing : drawings) {
            if (drawing instanceof Updating && !drawing.isPause()) {
                ((Updating) drawing).update(dungeonService, time);
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!focus.isEmpty()) {
            focus.getFirst().keyPressed(e);
        }
        debugEvent.keyPressed(e);
    }

    public void mousePressed(MouseEvent e) {
        if (!focus.isEmpty() && focus.getFirst() instanceof MouseTaker) {
            ((MouseTaker) focus.getFirst()).mousePressed(e);
        }
    }

    public void notShortMenu() throws GameException {
        dungeonService.getShortMenu().setDrawing(false);
        removeFocus(dungeonService.getShortMenu());
    }

    public void exitToMenu() {
        game.setStatusGame(Game.STATUS_GAME.MENU);
    }

    public void addDrawing(Drawing drawing) {
        drawings.add(drawing);
    }

    public void removeDrawing(Drawing drawing) {
        dead.add(drawing);
        // drawings.remove(drawing);
    }

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

    public void addFocus(KeyTaker keyTaker) {
        this.focus.addFirst(keyTaker);
    }

    public void removeFocus(KeyTaker keyTaker) throws GameException {
        if (focus.getFirst() != keyTaker) {
            throw new GameException(keyTaker.toString() + "is not first on focus!");
        } else {
            focus.pollFirst();
        }
    }
}

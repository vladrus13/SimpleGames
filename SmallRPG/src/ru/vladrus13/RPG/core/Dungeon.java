package ru.vladrus13.RPG.core;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.awt.event.*;

import static ru.vladrus13.RPG.core.utils.ways.Direction.*;

public class Dungeon {

    private final DungeonService dungeonService;
    private final ShortMenu shortMenu;
    private final Game game;
    private boolean isOnShortMenu = false;

    public Dungeon(Game game) throws GameException {
        this.game = game;
        shortMenu = new ShortMenu(this);
        dungeonService = new DungeonService();
        dungeonService.setCurrentFloor(1);
        dungeonService.getEventService().onStart(dungeonService);
    }

    public DungeonService getDungeonService() {
        return dungeonService;
    }

    public void draw(Graphics graphics) {
        dungeonService.getCurrentFloor().draw(graphics);
        dungeonService.getHero().draw(graphics);
        if (dungeonService.getDialog() != null) {
            dungeonService.getDialog().draw(graphics);
        }
        if (isOnShortMenu) {
            shortMenu.draw(graphics);
        }
    }

    public void update() { dungeonService.getHero().update(); }

    public void onEnterKeyPressed() {
        if (dungeonService.getDialog() != null) {
            if (dungeonService.getDialog().hasNext()) {
                dungeonService.getDialog().next();
            } else {
                dungeonService.setDialog(null);
            }
        } else {
            try {
                dungeonService.getEventService().onPressEnter(dungeonService);
            } catch (GameException gameException) {
                gameException.printStackTrace();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (isOnShortMenu) {
            shortMenu.keyPressed(e);
        } else {
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    dungeonService.getEventService().move(UP, dungeonService);
                    break;
                case KeyEvent.VK_DOWN:
                    dungeonService.getEventService().move(DOWN, dungeonService);
                    break;
                case KeyEvent.VK_LEFT:
                    dungeonService.getEventService().move(LEFT, dungeonService);
                    break;
                case KeyEvent.VK_RIGHT:
                    dungeonService.getEventService().move(RIGHT, dungeonService);
                    break;
                case KeyEvent.VK_ENTER:
                    onEnterKeyPressed();
                    break;
                case KeyEvent.VK_ESCAPE:
                    isOnShortMenu = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void notShortMenu() {
        isOnShortMenu = false;
    }

    public void exitToMenu() {
        game.setStatusGame(Game.STATUS_GAME.MENU);
    }
}

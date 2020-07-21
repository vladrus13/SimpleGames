package ru.vladrus13.RPG.core;

import ru.vladrus13.RPG.core.main.dungeon.Floor;
import ru.vladrus13.RPG.core.person.unit.Hero;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.event.EventService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.FontService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.event.*;

import static ru.vladrus13.RPG.core.utils.ways.Direction.*;

public class Dungeon {

    private final Floor floor;
    private final Hero hero;
    private final DungeonService dungeonService;
    private final ShortMenu shortMenu;
    private final Game game;
    private boolean isOnShortMenu = false;

    public Dungeon(Game game) throws GameException {
        this.game = game;
        floor = new Floor(2);
        hero = new Hero(0, new Point(1, 1), UP);
        shortMenu = new ShortMenu(this);
        dungeonService = new DungeonService();
        dungeonService.setCurrentFloor(floor);
        dungeonService.setHero(hero);
        dungeonService.setFontService(new FontService());
        dungeonService.setEventService(new EventService());
        dungeonService.getEventService().onStart(dungeonService);
    }

    public DungeonService getDungeonService() {
        return dungeonService;
    }

    public void draw(Graphics graphics) {
        floor.draw(graphics);
        hero.draw(graphics);
        if (dungeonService.getDialog() != null) {
            dungeonService.getDialog().draw(graphics);
        }
        if (isOnShortMenu) {
            shortMenu.draw(graphics);
        }
    }

    public void update() { hero.update(); }

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
        game.setStatusGame(Game.STATUSGAME.MENU);
    }
}

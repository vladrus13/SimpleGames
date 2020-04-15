package ru.vladrus13.core;

import ru.vladrus13.core.main.dungeon.Floor;
import ru.vladrus13.core.person.unit.Hero;
import ru.vladrus13.core.utils.DungeonService;
import ru.vladrus13.core.utils.event.EventService;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.FontService;
import ru.vladrus13.core.utils.ways.Point;

import java.awt.*;
import java.awt.event.*;

import static ru.vladrus13.core.utils.ways.Direction.*;

public class Dungeon {

    private final Floor floor;
    private final Hero hero;
    private final DungeonService dungeonService;
    private final ShortMenu shortMenu;

    public Dungeon() throws GameException {
        floor = new Floor(2);
        hero = new Hero(0, new Point(1, 1), UP);
        shortMenu = new ShortMenu();
        dungeonService = new DungeonService();
        dungeonService.setCurrentFloor(floor);
        dungeonService.setHero(hero);
        dungeonService.setFontService(new FontService());
        dungeonService.setEventService(new EventService());
        dungeonService.getEventService().onStart(dungeonService);
    }

    public void draw(Graphics graphics) {
        floor.draw(graphics);
        hero.draw(graphics);
        if (dungeonService.getDialog() != null) {
            dungeonService.getDialog().draw(graphics);
        }
    }

    public void update() {
        hero.update();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
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
                break;
            default:
                break;
        }
    }
}

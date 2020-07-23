package ru.vladrus13.RPG.core.person.unit;

import ru.vladrus13.RPG.core.ShortMenu;
import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.main.dungeon.Floor;
import ru.vladrus13.RPG.core.main.dungeon.event.TypeActiveEvent;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.person.Skills;
import ru.vladrus13.RPG.core.person.Stats;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;
import ru.vladrus13.RPG.core.utils.picture.PictureService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.event.KeyEvent;
import java.nio.file.Path;

import static ru.vladrus13.RPG.core.utils.ways.Direction.*;

public class Hero extends Person implements KeyTaker {

    protected final Stats stats;
    protected final Skills skills;
    protected final Inventory inventory;
    protected final DungeonService dungeonService;

    public Hero(int id, Point place, Direction direction, DungeonService dungeonService) throws GameException {
        super(id, place, direction);
        realPlace = new Point(place.getX() * 32, place.getY() * 32);
        this.picture = new PictureService().loadUnit(Path.of("assets/pictures/units/hero"));
        this.stats = new Stats();
        this.skills = new Skills();
        this.inventory = new Inventory();
        this.dungeonService = dungeonService;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void onPressEnter(DungeonService dungeonService) throws GameException {
        Hero hero = dungeonService.getHero();
        Floor floor = dungeonService.getCurrentFloor();
        Point to = hero.getPlace();
        to = to.makePoint(hero.getDirection());
        if (floor.isPerson(to)) {
            floor.getPerson(to).onPressEnter(dungeonService);
        } else {
            if (floor.isEvent(to, element -> element.getTypeActiveEvent() == TypeActiveEvent.ENTER_ONE_TILE)) {
                floor.getEvent(to, element -> element.getTypeActiveEvent() == TypeActiveEvent.ENTER_ONE_TILE).run(dungeonService);
            } else {
                if (floor.isDungeonItem(to)) {
                    dungeonService.getHero().getInventory().addItem(dungeonService.getItemFactory().get(floor.getDungeonItem(to).getId()));
                    floor.eraseItem(to, floor.getDungeonItem(to));
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                startWent(UP, dungeonService);
                break;
            case KeyEvent.VK_DOWN:
                startWent(DOWN, dungeonService);
                break;
            case KeyEvent.VK_LEFT:
                startWent(LEFT, dungeonService);
                break;
            case KeyEvent.VK_RIGHT:
                startWent(RIGHT, dungeonService);
                breaenient
            case KeyEvent.VK_ENTER:
                try {
                    onPressEnter(dungeonService);
                } catch (GameException gameException) {
                    gameException.printStackTrace();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                ShortMenu shortMenu = dungeonService.getShortMenu();
                shortMenu.setDrawing(true);
                dungeonService.getDungeon().addFocus(shortMenu);
                break;
            default:
                break;
        }
    }
}

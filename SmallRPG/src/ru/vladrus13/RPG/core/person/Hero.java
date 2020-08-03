package ru.vladrus13.RPG.core.person;

import ru.vladrus13.RPG.core.ShortMenu;
import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.main.dungeon.event.TypeActiveEvent;
import ru.vladrus13.RPG.core.main.dungeon.floor.Arena;
import ru.vladrus13.RPG.core.main.dungeon.floor.Floor;
import ru.vladrus13.RPG.core.main.dungeon.floor.StepByStepArena;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.graphics.MouseTaker;
import ru.vladrus13.RPG.core.graphics.PictureService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.nio.file.Path;

import static ru.vladrus13.RPG.core.utils.ways.Direction.*;

/**
 * @author vladkuznetsov
 * Hero class for {@link ru.vladrus13.RPG.core.Dungeon}
 */
public class Hero extends Person implements KeyTaker, MouseTaker {

    /**
     * {@link Stats} - stats
     */
    protected final Stats stats;
    /**
     * {@link Skills} - skills
     */
    protected final Skills skills;
    /**
     * {@link Inventory} - inventory
     */
    protected final Inventory inventory;
    /**
     * {@link DungeonService}
     */
    protected final DungeonService dungeonService;

    /**
     * Constructor for class
     *
     * @param id             id
     * @param place          {@link Point} - place
     * @param direction      {@link Direction} - direction
     * @param name           name of hero
     * @param dungeonService {@link DungeonService}
     */
    public Hero(int id, Point place, Direction direction, DungeonService dungeonService, String name) {
        super(id, place, direction, name);
        realPlace = new Point(place.getX() * 32, place.getY() * 32);
        this.picture = new PictureService().loadUnit(Path.of("assets/pictures/units/hero"));
        this.stats = new Stats(100, 200, 0, 10, 0);
        this.skills = new Skills();
        this.inventory = new Inventory();
        this.dungeonService = dungeonService;
    }

    /**
     * Getter for inventory
     *
     * @return {@link Inventory} - inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Getter for stats
     *
     * @return {@link Stats} - stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Method on press enter in game
     *
     * @param dungeonService {@link DungeonService}
     * @throws GameException if we have problem with item
     */
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
                    dungeonService.getHero().getInventory().addItem(dungeonService.getItemFactory().getWithCount(floor.getDungeonItem(to).getId()));
                    floor.eraseItem(to, floor.getDungeonItem(to));
                }
            }
        }
    }

    @Override
    public void went(DungeonService dungeonService) {
        super.went(dungeonService);
        if (!isWent()) {
            dungeonService.getEventService().onTileStep(dungeonService);
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
                break;
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

    @Override
    public void mousePressed(MouseEvent e) {
        if (dungeonService.getCurrentFloor() instanceof Arena) {
            // TODO normal attack
            Point attacked = getPlace().makePoint(getDirection());
            if (dungeonService.getCurrentFloor().isPerson(attacked) && dungeonService.getCurrentFloor().getPerson(attacked) instanceof Enemy) {
                ((Enemy) dungeonService.getCurrentFloor().getPerson(attacked)).damage(stats.getAttack());
            }
            if (dungeonService.getCurrentFloor() instanceof StepByStepArena) {
                ((StepByStepArena) dungeonService.getCurrentFloor()).enemyTurn();
            }
        }
    }

    /**
     * Getting damage
     *
     * @param damage how much damage
     */
    public void damage(int damage) {
        this.getStats().setHp(Math.max(0, this.getStats().getHp() - Math.max(0, damage - this.getStats().getArmor())));
    }

}

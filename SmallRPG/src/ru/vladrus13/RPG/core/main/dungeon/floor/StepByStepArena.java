package ru.vladrus13.RPG.core.main.dungeon.floor;

import ru.vladrus13.RPG.core.graphics.ColorService;
import ru.vladrus13.RPG.core.main.dungeon.DungeonItem;
import ru.vladrus13.RPG.core.main.dungeon.Tile;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.person.Enemy;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.person.Stats;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author vladkuznetsov
 * Class of step-by-step arena
 */
public class StepByStepArena extends Arena {

    /**
     * Using point
     */
    private final HashSet<Point> used;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    public void draw(Graphics graphics) {
        final int widthHP = 700;
        final int heightHP = widthHP / 15;
        super.draw(graphics);
        Stats heroStats = dungeonService.getHero().getStats();
        graphics.setColor(ColorService.underHPColor);
        graphics.fillRoundRect((800 - widthHP) / 2, 700, widthHP, heightHP, heightHP, heightHP);
        graphics.setColor(ColorService.HPColor);
        graphics.fillRoundRect((800 - widthHP) / 2, 700, widthHP * heroStats.getHp() / heroStats.getMaxHp(), heightHP, heightHP, heightHP);
    }

    /**
     * Method, calling move for all {@link Enemy}
     */
    public void enemyTurn() {
        dungeonService.getHero().setPause(true);
        HashSet<Person> deleted = new HashSet<>();
        for (Person person : actors) {
            if (person instanceof Enemy) {
                used.remove(person.getPlace());
                if (((Enemy) person).isDead()) {
                    deleted.add(person);
                } else {
                    used.add(((Enemy) person).next(used, dungeonService));
                }
            }
        }
        actors.removeAll(deleted);
        dungeonService.getHero().setPause(false);
    }

    /**
     * @param name           name of floor
     * @param tiles          table of tiles
     * @param actors         actors
     * @param dungeonItems   items
     * @param events         events
     * @param dungeonService {@link DungeonService}
     */
    public StepByStepArena(String name, ArrayList<ArrayList<Tile>> tiles, ArrayList<Person> actors, ArrayList<DungeonItem> dungeonItems, ArrayList<Event> events, DungeonService dungeonService) {
        super(name, tiles, actors, dungeonItems, events);
        this.used = new HashSet<>();
        this.dungeonService = dungeonService;
        for (Person person : actors) {
            used.add(person.getPlace());
        }
    }
}

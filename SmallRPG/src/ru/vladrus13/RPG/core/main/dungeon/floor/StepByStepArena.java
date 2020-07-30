package ru.vladrus13.RPG.core.main.dungeon.floor;

import ru.vladrus13.RPG.core.main.dungeon.Tile;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.main.dungeon.item.DungeonItem;
import ru.vladrus13.RPG.core.person.Enemy;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.ArrayList;
import java.util.HashSet;

public class StepByStepArena extends Arena {

    private final HashSet<Point> used;
    private final DungeonService dungeonService;

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

    public StepByStepArena(String name, ArrayList<ArrayList<Tile>> tiles, ArrayList<Person> actors, ArrayList<DungeonItem> dungeonItems, ArrayList<Event> events, DungeonService dungeonService) {
        super(name, tiles, actors, dungeonItems, events);
        this.used = new HashSet<>();
        this.dungeonService = dungeonService;
        for (Person person : actors) {
            used.add(person.getPlace());
        }
    }
}

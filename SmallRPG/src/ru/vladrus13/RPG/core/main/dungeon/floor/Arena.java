package ru.vladrus13.RPG.core.main.dungeon.floor;

import ru.vladrus13.RPG.core.main.dungeon.DungeonItem;
import ru.vladrus13.RPG.core.main.dungeon.Tile;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.person.Person;

import java.util.ArrayList;

/**
 * @author vladkuznetsov
 * Arena-class for {@link ru.vladrus13.RPG.core.Dungeon}. At this type of floor we can fight
 */
public class Arena extends Floor {

    /**
     * Constructor of class
     *
     * @param name         name of floor
     * @param tiles        table of tiles
     * @param actors       actors
     * @param dungeonItems items
     * @param events       events
     */
    public Arena(String name, ArrayList<ArrayList<Tile>> tiles, ArrayList<Person> actors, ArrayList<DungeonItem> dungeonItems, ArrayList<Event> events) {
        super(name, tiles, actors, dungeonItems, events);
    }
}

package ru.vladrus13.RPG.core.main.dungeon.floor;

import ru.vladrus13.RPG.core.main.dungeon.Tile;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.main.dungeon.item.DungeonItem;
import ru.vladrus13.RPG.core.person.Person;

import java.util.ArrayList;

public class Arena extends Floor {

    public Arena(String name, ArrayList<ArrayList<Tile>> tiles, ArrayList<Person> actors, ArrayList<DungeonItem> dungeonItems, ArrayList<Event> events) {
        super(name, tiles, actors, dungeonItems, events);
    }
}

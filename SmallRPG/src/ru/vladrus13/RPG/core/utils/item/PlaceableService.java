package ru.vladrus13.RPG.core.utils.item;

import ru.vladrus13.RPG.core.main.dungeon.item.*;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.main.dungeon.Placeable;
import ru.vladrus13.RPG.core.main.dungeon.Wall;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

public class PlaceableService {
    public Placeable parseItem(int id, Point point) throws GameException {
        if (id < -100) return new Event(id, point);
        if (id < -1) return new Person(id, point, Direction.UP);
        if (id == -1) return new Wall(id, point);
        if (id > -1 && id < 251) return new DungeonItem(id, point);
        return null;
    }
}

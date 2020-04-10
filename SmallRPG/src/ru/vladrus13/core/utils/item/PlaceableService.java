package ru.vladrus13.core.utils.item;

import ru.vladrus13.core.item.Item;
import ru.vladrus13.core.item.armor.Armor;
import ru.vladrus13.core.item.poison.Poison;
import ru.vladrus13.core.item.weapon.Weapon;
import ru.vladrus13.core.main.dungeon.Event;
import ru.vladrus13.core.main.dungeon.Placeable;
import ru.vladrus13.core.main.dungeon.Wall;
import ru.vladrus13.core.person.Person;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.ways.Direction;
import ru.vladrus13.core.utils.ways.Point;

public class PlaceableService {
    public Placeable parseItem(int id, Point point) throws GameException {
        if (id < -100) return new Event(id, point);
        if (id < -1) return new Person(id, point, Direction.UP);
        if (id == -1) return new Wall(id, point);
        if (id == 0) return new Item(id, point);
        if (id < 101) return new Weapon(id, point);
        if (id < 201) return new Armor(id, point);
        if (id < 251) return new Poison(id, point);
        return null;
    }
}

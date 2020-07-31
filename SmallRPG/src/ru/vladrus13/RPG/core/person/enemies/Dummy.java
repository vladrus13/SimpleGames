package ru.vladrus13.RPG.core.person.enemies;

import ru.vladrus13.RPG.core.person.Enemy;
import ru.vladrus13.RPG.core.person.Stats;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.HashSet;

/**
 * @author vladkuznetsov
 * Just dummy class
 */
public class Dummy extends Enemy {
    @Override
    public Point next(HashSet<Point> used, DungeonService dungeonService) {
        return getPlace();
    }

    /**
     * Constructor for class
     *
     * @param place     {@link Point} - place
     * @param direction {@link Direction} - direction
     * @param name      name of enemy
     * @param stats     {@link Stats} - stats
     */
    public Dummy(Point place, Direction direction, String name, Stats stats) {
        super(3, place, direction, name, stats, 0, 0);
    }

    /**
     * Constructor for class
     *
     * @param place     {@link Point} - place
     * @param direction {@link Direction} - direction
     */
    public Dummy(Point place, Direction direction) {
        super(3, place, direction, "Dummy", new Stats(100, 100, 0, 0, 0), 0, 0);
    }

    @Override
    public Dummy clone() {
        return new Dummy(place.clone(), direction, name, stats.clone());
    }
}

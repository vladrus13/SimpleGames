package ru.vladrus13.RPG.core.person.enemies;

import ru.vladrus13.RPG.core.person.Enemy;
import ru.vladrus13.RPG.core.person.Stats;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.HashSet;

public class Dummy extends Enemy {
    @Override
    public Point next(HashSet<Point> used, DungeonService dungeonService) {
        return getPlace();
    }

    public Dummy(Point place, Direction direction, String name, Stats stats) {
        super(3, place, direction, name, stats, 0, 0);
    }

    public Dummy(Point place, Direction direction) {
        super(3, place, direction, "Dummy", new Stats(100, 100, 0, 0, 0), 0, 0);
    }
}

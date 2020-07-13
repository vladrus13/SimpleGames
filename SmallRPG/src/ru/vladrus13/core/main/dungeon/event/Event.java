package ru.vladrus13.core.main.dungeon.event;

import ru.vladrus13.core.main.dungeon.Placeable;
import ru.vladrus13.core.utils.DungeonService;
import ru.vladrus13.core.utils.ways.Point;

import java.util.function.Consumer;

public class Event extends Placeable {
    protected Point place;
    protected int id;
    protected Consumer<DungeonService> event;

    public Event(int id, Point point) {
        super(id, point);
    }

    public void run(DungeonService dungeonService) {
        event.accept(dungeonService);
    }
}

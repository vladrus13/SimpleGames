package ru.vladrus13.RPG.core.main.dungeon.event;

import ru.vladrus13.RPG.core.main.dungeon.Placeable;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.function.Consumer;

public class Event extends Placeable {
    private final Consumer<DungeonService> event;
    private final TypeActiveEvent typeActiveEvent;

    public Event(int id, Point point, TypeActiveEvent typeActiveEvent, Consumer<DungeonService> event) {
        super(id, point);
        this.event = event;
        this.typeActiveEvent = typeActiveEvent;
    }

    public TypeActiveEvent getTypeActiveEvent() {
        return typeActiveEvent;
    }

    public void run(DungeonService dungeonService) {
        event.accept(dungeonService);
    }
}

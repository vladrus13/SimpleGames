package ru.vladrus13.RPG.core.main.dungeon.event;

import ru.vladrus13.RPG.core.main.dungeon.Placeable;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.function.Consumer;

/**
 * @author vladkuznetsov
 * Event-class for {@link ru.vladrus13.RPG.core.Dungeon}
 */
public class Event extends Placeable {
    /**
     * Calling, if event run
     */
    private final Consumer<DungeonService> event;
    /**
     * Type of event - {@link TypeActiveEvent}
     */
    private final TypeActiveEvent typeActiveEvent;

    /**
     * Constructor for class
     *
     * @param id              id
     * @param point           where is event - {@link Point}
     * @param typeActiveEvent type of event - {@link TypeActiveEvent}
     * @param event           calling, if event run - {@link Consumer}
     */
    public Event(int id, Point point, TypeActiveEvent typeActiveEvent, Consumer<DungeonService> event) {
        super(id, point);
        this.event = event;
        this.typeActiveEvent = typeActiveEvent;
    }

    /**
     * Getter for type of event
     *
     * @return {@link TypeActiveEvent} - type of event
     */
    public TypeActiveEvent getTypeActiveEvent() {
        return typeActiveEvent;
    }

    /**
     * Runs event
     *
     * @param dungeonService changed {@link DungeonService}
     */
    public void run(DungeonService dungeonService) {
        event.accept(dungeonService);
    }
}

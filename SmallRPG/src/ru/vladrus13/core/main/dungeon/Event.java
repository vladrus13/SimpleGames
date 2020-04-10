package ru.vladrus13.core.main.dungeon;

import ru.vladrus13.core.utils.ways.Point;

public class Event extends Placeable {
    protected Point place;
    protected int id;

    public Event(int id, Point point) {
        super(id, point);
    }

    public void run() {
        // TODO
    }
}

package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.main.dungeon.event.Event;

import java.util.HashMap;

public class EventFactory {

    public final HashMap<String, Event> events;

    public EventFactory() {
        events = new HashMap<>();
    }
}

package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.main.dialog.Dialog;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.HashMap;

import static ru.vladrus13.RPG.core.main.dungeon.event.TypeActiveEvent.TRIGGERED;

public class EventFactory {

    private final HashMap<String, Event> events;

    public EventFactory() {
        events = new HashMap<>();
        events.put("onStart", new Event(0, new Point(0, 0), TRIGGERED, dungeonService -> {
            try {
                dungeonService.setDialog(new Dialog(new String[]{"Где я?"}, new Person[]{dungeonService.getHero()}, dungeonService));
            } catch (GameException e) {
                e.printStackTrace();
            }
        }));
    }

    public Event get(String key) {
        return events.get(key);
    }
}

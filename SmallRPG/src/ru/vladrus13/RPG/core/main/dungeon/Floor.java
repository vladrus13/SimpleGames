package ru.vladrus13.RPG.core.main.dungeon;

import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.main.dungeon.item.DungeonItem;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.picture.Drawing;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Predicate;

import ru.vladrus13.RPG.core.utils.ways.Point;

public class Floor extends Drawing {

    private final String name;
    private final ArrayList<ArrayList<Tile>> tiles;
    /*
        Why tiles is table, but actors and others just list with points? Because tiles.size = width * height,
        however very rarely when the number of actors is greater than the width or height
     */
    private final ArrayList<Person> actors;
    private final ArrayList<DungeonItem> dungeonItems;
    private final ArrayList<Event> events;

    public Floor(String name, ArrayList<ArrayList<Tile>> tiles, ArrayList<Person> actors, ArrayList<DungeonItem> dungeonItems, ArrayList<Event> events) {
        this.name = name;
        this.tiles = tiles;
        this.actors = actors;
        this.dungeonItems = dungeonItems;
        this.events = events;
    }

    @Override
    public void draw(Graphics graphics) {
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                if (tiles.get(i).get(j) != null) {
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(32 * i, 32 * j, 32, 32);
                    tiles.get(i).get(j).draw(graphics);
                } else {
                    graphics.setColor(Color.DARK_GRAY);
                    graphics.fillRect(32 * i, 32 * j, 32, 32);
                }
            }
        }
        actors.forEach(element -> element.draw(graphics));
        dungeonItems.forEach(element -> element.draw(graphics));
    }

    public boolean isWall(Point a) {
        return !getPlaceable(a).isWalked();
    }

    public boolean isCannotWalk(Point a) {
        return isWall(a) || isPerson(a);
    }

    public boolean isPerson(Point a) {
        return actors.stream().anyMatch(placeable -> placeable.getPlace().equals(a));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Person getPerson(Point a) {
        if (!isPerson(a)) return null;
        return actors.stream().filter(placeable -> placeable.getPlace().equals(a)).findFirst().get();
    }

    public boolean isEvent(Point a, Predicate <Event> filter) {
        return events.stream().filter(filter).anyMatch(element -> element.getPlace().equals(a));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Event getEvent(Point a, Predicate <Event> filter) {
        if (!isEvent(a, filter)) return null;
        return events.stream().filter(element -> element.getPlace().equals(a)).findFirst().get();
    }

    public boolean isDungeonItem(Point a) {
        return dungeonItems.stream().anyMatch(element -> element.getPlace().equals(a));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public DungeonItem getDungeonItem(Point a) {
        if (!isDungeonItem(a)) return null;
        return dungeonItems.stream().filter(element -> element.getPlace().equals(a)).findFirst().get();
    }

    public Tile getPlaceable(Point a) {
        return tiles.get(a.getX()).get(a.getY());
    }

    public void eraseItem(Point a, DungeonItem item) {
        for (int i = 0; i < dungeonItems.size(); i++) {
            if (dungeonItems.get(i).getPlace().equals(a) && dungeonItems.get(i).id == item.id) {
                dungeonItems.remove(i);
                break;
            }
        }
    }
}

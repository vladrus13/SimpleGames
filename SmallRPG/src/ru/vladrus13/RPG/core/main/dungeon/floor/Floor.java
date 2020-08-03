package ru.vladrus13.RPG.core.main.dungeon.floor;

import ru.vladrus13.RPG.core.main.dungeon.Tile;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.main.dungeon.DungeonItem;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.Updating;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

/**
 * @author vladkuznetsov
 * Floor class for {@link ru.vladrus13.RPG.core.Dungeon}
 */
public class Floor extends Drawing implements Updating {

    /**
     * Name of floor
     */
    protected final String name;
    /**
     * Table of tiles. {@link Tile}
     */
    protected final ArrayList<ArrayList<Tile>> tiles;
    /*
        Why tiles is table, but actors and others just list with points? Because tiles.size = width * height,
        however very rarely when the number of actors is greater than the width or height
    */
    /**
     * Concurrent array of actors
     */
    protected final CopyOnWriteArrayList<Person> actors;
    /**
     * Concurrent array of items
     */
    protected final CopyOnWriteArrayList<DungeonItem> dungeonItems;
    /**
     * Array of events
     */
    protected final ArrayList<Event> events;

    /**
     * Constructor of class
     *
     * @param name         name of floor
     * @param tiles        table of tiles
     * @param actors       actors
     * @param dungeonItems items
     * @param events       events
     */
    public Floor(String name, ArrayList<ArrayList<Tile>> tiles, ArrayList<Person> actors, ArrayList<DungeonItem> dungeonItems, ArrayList<Event> events) {
        this.name = name;
        this.tiles = tiles;
        this.actors = new CopyOnWriteArrayList<>(actors);
        this.dungeonItems = new CopyOnWriteArrayList<>(dungeonItems);
        this.events = events;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0, 0, 800, 800);
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

    /**
     * Is at this point wall
     *
     * @param a {@link Point}
     * @return true, if where wall, false else
     */
    public boolean isWall(Point a) {
        return !getTile(a).isWalked();
    }

    /**
     * Is person can walk here
     *
     * @param a {@link Point}
     * @return true, if can, false else
     */
    public boolean isCanWalk(Point a) {
        return !isWall(a) && !isPerson(a);
    }

    /**
     * is person here
     *
     * @param a {@link Point}
     * @return true, if person here, false else
     */
    public boolean isPerson(Point a) {
        return actors.stream().anyMatch(placeable -> placeable.getPlace().equals(a));
    }

    /**
     * Get person at this place
     *
     * @param a {@link Point}
     * @return {@link Person}
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Person getPerson(Point a) {
        if (!isPerson(a)) return null;
        return actors.stream().filter(placeable -> placeable.getPlace().equals(a)).findFirst().get();
    }

    /**
     * is event here
     *
     * @param a      {@link Point}
     * @param filter filter of events
     * @return true, if event here, false else
     */
    public boolean isEvent(Point a, Predicate<Event> filter) {
        return events.stream().filter(filter).anyMatch(element -> element.getPlace().equals(a));
    }

    /**
     * Get event at this place
     *
     * @param a      {@link Point}
     * @param filter filter of events
     * @return {@link Event}
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Event getEvent(Point a, Predicate<Event> filter) {
        if (!isEvent(a, filter)) return null;
        return events.stream().filter(element -> element.getPlace().equals(a)).findFirst().get();
    }

    /**
     * is item here
     *
     * @param a {@link Point}
     * @return true, if item here, false else
     */
    public boolean isDungeonItem(Point a) {
        return dungeonItems.stream().anyMatch(element -> element.getPlace().equals(a));
    }

    /**
     * Get item at this place
     *
     * @param a {@link Point}
     * @return {@link DungeonItem}
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public DungeonItem getDungeonItem(Point a) {
        if (!isDungeonItem(a)) return null;
        return dungeonItems.stream().filter(element -> element.getPlace().equals(a)).findFirst().get();
    }

    /**
     * Get tile at this place
     *
     * @param a {@link Point}
     * @return {@link Tile}
     */
    public Tile getTile(Point a) {
        return tiles.get(a.getX()).get(a.getY());
    }

    /**
     * Erase one item
     *
     * @param a    {@link Point}
     * @param item {@link DungeonItem}
     */
    public void eraseItem(Point a, DungeonItem item) {
        for (int i = 0; i < dungeonItems.size(); i++) {
            if (dungeonItems.get(i).getPlace().equals(a) && dungeonItems.get(i).getId() == item.getId()) {
                dungeonItems.remove(i);
                break;
            }
        }
    }

    @Override
    public void update(DungeonService dungeonService, long time) {
        for (Person person : actors) {
            person.update(dungeonService, time);
        }
    }

    /**
     * Getter for actors
     *
     * @return actors
     */
    public CopyOnWriteArrayList<Person> getActors() {
        return actors;
    }
}

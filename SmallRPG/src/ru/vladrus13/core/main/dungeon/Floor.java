package ru.vladrus13.core.main.dungeon;

import ru.vladrus13.core.item.Item;
import ru.vladrus13.core.person.Person;
import ru.vladrus13.core.utils.Drawing;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.item.PlaceableService;
import ru.vladrus13.core.utils.ways.Point;

public class Floor implements Drawing {

    private final ArrayList<ArrayList<Placeable>> floors;
    private final ArrayList<Person> actors;
    private final ArrayList<Item> items;
    private int width, height, actorCount, itemCount;

    public Floor(int level) {
        floors = new ArrayList<>();
        actors = new ArrayList<>();
        items = new ArrayList<>();
        PlaceableService placeableService = new PlaceableService();
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Path.of("assets/data/floor/" + level + ".lvl"));
            String[] line = bufferedReader.readLine().split(" ");
            width = Integer.parseInt(line[0]);
            height = Integer.parseInt(line[1]);
            for (int i = 0; i < width; i++) {
                ArrayList<Placeable> temp = new ArrayList<>();
                line = bufferedReader.readLine().split(" ");
                for (int j = 0; j < height; j++) {
                    temp.add(placeableService.parseItem(Integer.parseInt(line[j]), new Point(i, j)));
                }
                floors.add(temp);
            }
            line = bufferedReader.readLine().split(" ");
            actorCount = Integer.parseInt(line[0]);
            for (int i = 0; i < actorCount; i++) {
                line = bufferedReader.readLine().split(" ");
                actors.add((Person) placeableService.parseItem(Integer.parseInt(line[0]), new Point(Integer.parseInt(line[1]), Integer.parseInt(line[2]))));
            }
            line = bufferedReader.readLine().split(" ");
            itemCount = Integer.parseInt(line[0]);
            for (int i = 0; i < itemCount; i++) {
                line = bufferedReader.readLine().split(" ");
                items.add((Item) placeableService.parseItem(Integer.parseInt(line[0]), new Point(Integer.parseInt(line[1]), Integer.parseInt(line[2]))));
            }
        } catch (IOException | GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (floors.get(i).get(j) != null) {
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(32 * i, 32 * j, 32, 32);
                    floors.get(i).get(j).draw(graphics);
                } else {
                    graphics.setColor(Color.DARK_GRAY);
                    graphics.fillRect(32 * i, 32 * j, 32, 32);
                }
            }
        }
        for (int i = 0; i < actorCount; i++) {
            actors.get(i).draw(graphics);
        }
    }

    public boolean isWall(Point a) {
        return getPlaceable(a).getId() == -1;
    }

    public boolean isCannotWalk(Point a) {
        return isWall(a) || isPerson(a);
    }

    public boolean isPerson(Point a) {
        return actors.stream().anyMatch(placeable -> placeable.getPlace().equals(a));
    }

    public Person getPerson(Point a) {
        if (!isPerson(a)) return null;
        return (Person) actors.stream().filter(placeable -> placeable.getPlace().equals(a)).findFirst().get();
    }

    public boolean isItem(Point a) {
        return getPlaceable(a).getId() > 0;
    }

    public boolean isEvent(Point a) {
        return getPlaceable(a).getId() < -1;
    }

    public Placeable getPlaceable(Point a) {
        return floors.get(a.getX()).get(a.getY());
    }
}

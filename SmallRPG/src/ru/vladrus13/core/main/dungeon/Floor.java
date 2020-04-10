package ru.vladrus13.core.main.dungeon;

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

    private final ArrayList<ArrayList<Placeable>> items;
    private final ArrayList<Placeable> actors;
    private int width, height, actorCount;

    public Floor(int level) {
        items = new ArrayList<>();
        actors = new ArrayList<>();
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
                items.add(temp);
            }
            line = bufferedReader.readLine().split(" ");
            actorCount = Integer.parseInt(line[0]);
            for (int i = 0; i < actorCount; i++) {
                line = bufferedReader.readLine().split(" ");
                actors.add(placeableService.parseItem(Integer.parseInt(line[0]), new Point(Integer.parseInt(line[1]), Integer.parseInt(line[2]))));
            }
        } catch (IOException | GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (items.get(i).get(j) != null) {
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(32 * i, 32 * j, 32, 32);
                    items.get(i).get(j).draw(graphics);
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

    public boolean isItem(Point a) {
        return getPlaceable(a).getId() > 0;
    }

    public boolean isEvent(Point a) {
        return getPlaceable(a).getId() < -1;
    }

    public Placeable getPlaceable(Point a) {
        return items.get(a.getX()).get(a.getY());
    }
}

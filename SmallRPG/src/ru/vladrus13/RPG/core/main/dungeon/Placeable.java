package ru.vladrus13.RPG.core.main.dungeon;

import ru.vladrus13.RPG.core.utils.Drawing;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Placeable implements Drawing {
    protected BufferedImage picture;
    protected Point place;
    protected final int id;

    public Placeable(int id, Point place) {
        this.id = id;
        this.place = place;
    }

    public Point getPlace() {
        return place;
    }

    public int getId() {
        return id;
    }

    @Override
    public void draw(Graphics graphics) {

    }

    public void setPlace(Point place) {
        this.place = place;
    }
}

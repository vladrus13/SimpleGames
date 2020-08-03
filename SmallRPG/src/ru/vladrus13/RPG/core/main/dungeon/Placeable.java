package ru.vladrus13.RPG.core.main.dungeon;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author vladkuznetsov
 * Placeable item - class
 */
public class Placeable extends Drawing {
    /**
     * Picture - {@link BufferedImage}
     */
    protected BufferedImage picture;
    /**
     * Place of picture - {@link Point}
     */
    protected Point place;
    /**
     * Id
     */
    protected final int id;

    /**
     * Constructor
     *
     * @param id    id
     * @param place place - {@link Point}
     */
    public Placeable(int id, Point place) {
        this.id = id;
        this.place = place;
    }

    /**
     * Getter for place
     *
     * @return place
     */
    public Point getPlace() {
        return place;
    }

    /**
     * Getter for id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    @Override
    public void draw(Graphics graphics) {
        // do nothing, just override this
    }

}

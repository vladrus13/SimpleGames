package ru.vladrus13.RPG.core.graphics;

import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author vladkuznetsov
 * Class picture
 */
public class Picture extends Drawing {
    /**
     * Position of image {@link Point}
     */
    private final Point position;
    /**
     * Size of image {@link Point}
     */
    private final Point size;
    /**
     * {@link BufferedImage} image
     */
    private final BufferedImage image;

    /**
     * Constructor of class
     *
     * @param position position of image
     * @param size     size of image
     * @param image    image
     */
    public Picture(Point position, Point size, BufferedImage image) {
        this.position = position;
        this.size = size;
        this.image = image;
    }

    /**
     * Constructor of class without size
     *
     * @param position position of image
     * @param image    image
     */
    public Picture(Point position, BufferedImage image) {
        this.position = position;
        this.size = null;
        this.image = image;
    }

    @Override
    public void draw(Graphics graphics) {
        if (size == null) {
            graphics.drawImage(image, position.getX(), position.getY(), null);
        } else {
            graphics.drawImage(image, position.getX(), position.getY(), size.getX(), size.getY(), null);
        }
    }
}

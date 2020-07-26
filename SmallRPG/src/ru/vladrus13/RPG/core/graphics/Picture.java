package ru.vladrus13.RPG.core.graphics;

import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Picture extends Drawing {
    private final Point position, size;
    private final BufferedImage image;

    public Picture(Point position, Point size, BufferedImage image) {
        this.position = position;
        this.size = size;
        this.image = image;
    }

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

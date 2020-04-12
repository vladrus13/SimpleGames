package ru.vladrus13.core.item;

import ru.vladrus13.core.main.dungeon.Placeable;
import ru.vladrus13.core.utils.Drawing;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.PictureService;
import ru.vladrus13.core.utils.ways.Point;

import java.awt.*;
import java.nio.file.Path;

public class Item extends Placeable implements Drawing {

    public Item(int id, Point point) {
        super(id, point);
        loadPicture(new PictureService());
    }

    public void loadPicture(PictureService pictureService) {
        try {
            picture = pictureService.loadImage(Path.of("assets/pictures/items/" + id + ".png"));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(picture, 32 * place.getX(), 32 * place.getY(), 32, 32, null);
    }

    public boolean equals(Item other) {
        return id == other.id;
    }

    public boolean absoluteEquals(Item other) {
        return id == other.id && place.equals(other.place);
    }
}

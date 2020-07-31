package ru.vladrus13.RPG.core.main.dungeon.item;

import ru.vladrus13.RPG.core.main.dungeon.Placeable;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.PictureService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.nio.file.Path;

/**
 * @author vladkuznetsov
 * Dungeon items
 */
public class DungeonItem extends Placeable {

    /**
     * Constructor for class
     *
     * @param id    id
     * @param point {@link Point} position of item
     */
    public DungeonItem(int id, Point point) {
        super(id, point);
        loadPicture(new PictureService());
    }

    /**
     * Load picture and saved it ot "picture"
     *
     * @param pictureService {@link PictureService}
     */
    public void loadPicture(PictureService pictureService) {
        try {
            picture = pictureService.loadImage(Path.of("assets/pictures/items/" + id + ".png"));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(picture, 32 * place.getX(), 32 * place.getY(), 32, 32, null);
    }
}

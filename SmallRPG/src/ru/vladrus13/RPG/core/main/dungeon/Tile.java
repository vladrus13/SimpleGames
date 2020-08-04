package ru.vladrus13.RPG.core.main.dungeon;

import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.graphics.PictureService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.nio.file.Path;

/**
 * @author vladkuznetsov
 * Tile for {@link ru.vladrus13.RPG.core.Dungeon}
 */
public class Tile extends Placeable {
    /**
     * Can we walk here boolean
     */
    private final boolean isWalked;

    /**
     * Constructor for class
     *
     * @param id    id
     * @param place place
     */
    public Tile(int id, Point place) {
        super(id, place);
        isWalked = id != 1;
        loadPicture(new PictureService());
    }

    /**
     * Load picture and saved it ot "picture"
     *
     * @param pictureService {@link PictureService}
     */
    public void loadPicture(PictureService pictureService) {
        try {
            picture = pictureService.loadImage(Path.of("resources/assets/pictures/tiles/" + id + ".png"));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(picture, 32 * place.getX(), 32 * place.getY(), 32, 32, null);
    }

    /**
     * Is we can walk here
     *
     * @return true, if can, false else
     */
    public boolean isWalked() {
        return isWalked;
    }
}

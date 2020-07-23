package ru.vladrus13.RPG.core.main.dungeon;

import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.PictureService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.nio.file.Path;

public class Tile extends Placeable {
    public Tile(int id, Point place) {
        super(id, place);
        loadPicture(new PictureService());
    }

    public void loadPicture(PictureService pictureService) {
        try {
            picture = pictureService.loadImage(Path.of("assets/pictures/tiles/" + id + ".png"));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(picture, 32 * place.getX(), 32 * place.getY(), 32, 32, null);
    }
}

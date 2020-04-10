package ru.vladrus13.core.item.weapon;

import ru.vladrus13.core.item.Item;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.PictureService;
import ru.vladrus13.core.utils.ways.Point;

import java.nio.file.Path;

public class Weapon extends Item {
    public Weapon(int id, Point point) {
        super(id, point);
    }
/*
    public Weapon(int id) {
        super(id);
    }
*/
    @Override
    public void loadPicture(PictureService pictureService) {
        try {
            picture = pictureService.loadImage(Path.of("assets/pictures/item/weapon/" + id + ".png"));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }
}

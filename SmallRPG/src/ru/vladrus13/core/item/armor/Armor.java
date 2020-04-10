package ru.vladrus13.core.item.armor;

import ru.vladrus13.core.item.Item;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.PictureService;
import ru.vladrus13.core.utils.ways.Point;

import java.nio.file.Path;

public class Armor extends Item {
    public Armor(int id, Point point) {
        super(id, point);
    }
/*
    public Armor(int id) {
        super(id);
    }
*/
    @Override
    public void loadPicture(PictureService pictureService) {
        try {
            picture = pictureService.loadImage(Path.of("assets/pictures/item/armor/" + id + ".png"));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }
}

package ru.vladrus13.core.main.dungeon;

import ru.vladrus13.core.item.Item;
import ru.vladrus13.core.utils.picture.PictureService;
import ru.vladrus13.core.utils.ways.Point;

import java.awt.image.BufferedImage;

public class Wall extends Item {

    public Wall(int id, Point point) {
        super(id, point);
    }

    @Override
    public void loadPicture(PictureService pictureService) {
        picture = new BufferedImage(1, 1, 1);
    }
}

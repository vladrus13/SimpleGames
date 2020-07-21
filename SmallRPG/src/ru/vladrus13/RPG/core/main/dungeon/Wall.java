package ru.vladrus13.RPG.core.main.dungeon;

import ru.vladrus13.RPG.core.main.dungeon.item.DungeonItem;
import ru.vladrus13.RPG.core.utils.picture.PictureService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.image.BufferedImage;

public class Wall extends DungeonItem {

    public Wall(int id, Point point) {
        super(id, point);
    }

    @Override
    public void loadPicture(PictureService pictureService) {
        picture = new BufferedImage(1, 1, 1);
    }
}
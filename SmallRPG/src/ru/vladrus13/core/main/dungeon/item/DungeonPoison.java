package ru.vladrus13.core.main.dungeon.item;

import ru.vladrus13.core.main.dungeon.item.DungeonItem;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.PictureService;
import ru.vladrus13.core.utils.ways.Point;

import java.nio.file.Path;

public class DungeonPoison extends DungeonItem {
    public DungeonPoison(int id, Point point) {
        super(id, point);
    }

    @Override
    public void loadPicture(PictureService pictureService) {
        try {
            picture = pictureService.loadImage(Path.of("assets/pictures/items/poisons/" + id + ".png"));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }
}

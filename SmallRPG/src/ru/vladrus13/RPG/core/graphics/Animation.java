package ru.vladrus13.RPG.core.graphics;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.picture.Updating;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation extends Drawing implements Updating {
    private final ArrayList<BufferedImage> images;
    private int time;
    private final int pause;
    private int count;
    private final Point position, compact;
    private boolean isEnd = false;

    public Animation(ArrayList<BufferedImage> images, int pause, Point position) {
        this.images = images;
        this.pause = pause;
        this.position = position;
        this.compact = null;
    }

    public Animation(BufferedImage image, int pause, Point position, Point size) throws GameException {
        if (image.getHeight() % size.getY() != 0 || image.getWidth() % size.getX() != 0) {
            throw new GameException("Image size is non-divide on part. Image size: " + image.getHeight() + ":" + image.getWidth() +
                    ". Part size: " + size.getY() + ":" + size.getX());
        }
        int countWidth = image.getWidth() / size.getX();
        int countHeight = image.getHeight() / size.getY();
        images = new ArrayList<>();
        this.pause = pause;
        this.position = position;
        this.compact = null;
        for (int j = 0; j < countHeight; j++) {
            for (int i = 0; i < countWidth; i++) {
                images.add(image.getSubimage(i * size.getX(), j * size.getY(), size.getX(), size.getY()));
            }
        }
    }

    public Animation(BufferedImage image, int pause, Point position, Point size, Point compact) throws GameException {
        if (image.getHeight() % size.getY() != 0 || image.getWidth() % size.getX() != 0) {
            throw new GameException("Image size is non-divide on part. Image size: " + image.getHeight() + ":" + image.getWidth() +
                    ". Part size: " + size.getY() + ":" + size.getX());
        }
        int countWidth = image.getWidth() / size.getX();
        int countHeight = image.getHeight() / size.getY();
        images = new ArrayList<>();
        this.pause = pause;
        this.position = position;
        this.compact = compact;
        for (int j = 0; j < countHeight; j++) {
            for (int i = 0; i < countWidth; i++) {
                images.add(image.getSubimage(i * size.getX(), j * size.getY(), size.getX(), size.getY()));
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        if (!isEnd) {
            if (compact != null) {
                graphics.drawImage(images.get(count), position.getX(), position.getY(), compact.getX(), compact.getY(), null);
            } else {
                graphics.drawImage(images.get(count), position.getX(), position.getY(), null);
            }
        }
    }

    @Override
    public void update(DungeonService dungeonService, long time) {
        this.time += time;
        if (this.time > pause) {
            if (count + 1 == images.size()) {
                isEnd = true;
                dungeonService.getDungeon().removeDrawing(this);
            }
            count++;
            this.time = 0;
        }
    }
}

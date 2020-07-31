package ru.vladrus13.RPG.core.graphics;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.picture.Updating;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author vladkuznetsov
 * Class for animation - pictures shown in certain sequence
 */
public class Animation extends Drawing implements Updating {
    /**
     * {@link ArrayList} of images
     */
    private final ArrayList<BufferedImage> images;
    /**
     * Current time of picture
     */
    private int time;
    /**
     * Result time for one picture shown
     */
    private final int pause;
    /**
     * Which picture shown now
     */
    private int count;
    /**
     * Position of animation
     */
    private final Point position;
    /**
     * Size of compression image
     */
    private final Point compact;
    /**
     * Is end animation
     */
    private boolean isEnd = false;

    /**
     * Constructor for class
     *
     * @param images   list of pictures
     * @param pause    time for one picture shown
     * @param position position of animation
     */
    public Animation(ArrayList<BufferedImage> images, int pause, Point position) {
        this.images = images;
        this.pause = pause;
        this.position = position;
        this.compact = null;
    }

    /**
     * Constructor for class
     *
     * @param image    big image with all pictures in one
     * @param pause    time for one picture shown
     * @param position position of animation
     * @param size     size of animation
     * @throws GameException if size non available
     */
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

    /**
     * Constructor for class
     *
     * @param image    big image with all pictures in one
     * @param pause    time for one picture shown
     * @param position position of animation
     * @param size     size of animation
     * @param compact  size of compress picture
     * @throws GameException if size non available
     */
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

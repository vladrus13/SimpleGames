package ru.vladrus13.RPG.core.utils.picture;

import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vladkuznetsov
 * Picture service
 */
public class PictureService {
    /**
     * Return image
     *
     * @param path path of image
     * @return {@link BufferedImage}
     * @throws GameException if we can't load this file
     */
    public BufferedImage loadImage(Path path) throws GameException {
        try {
            return ImageIO.read(path.toFile());
        } catch (IOException e) {
            throw new GameException("Error load " + path, e);
        }
    }

    /**
     * Load units pictures: UP, DOWN, LEFT, RIGHT
     *
     * @param path path to directory to files Up.png, Left.png, ect.
     * @return map of these pictures
     */
    public Map<Direction, BufferedImage> loadUnit(Path path) {
        Map<Direction, BufferedImage> returned = new HashMap<>();
        try {
            returned.put(Direction.UP, loadImage(Path.of(path.toString() + "/Up.png")));
            returned.put(Direction.LEFT, loadImage(Path.of(path.toString() + "/Left.png")));
            returned.put(Direction.RIGHT, loadImage(Path.of(path.toString() + "/Right.png")));
            returned.put(Direction.DOWN, loadImage(Path.of(path.toString() + "/Down.png")));
        } catch (GameException e) {
            e.printStackTrace();
        }
        return returned;
    }
}

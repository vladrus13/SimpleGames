package ru.vladrus13.core.utils.picture;

import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.ways.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PictureService {
    /**
     * Return image, if it is exists, else return null
     * @param path path of image
     * @return image
     */
    public BufferedImage loadImage(Path path) throws GameException {
        try {
            return ImageIO.read(path.toFile());
        } catch (IOException e) {
            throw new GameException("Error load " + path, e);
        }
    }

    public Map<Direction, BufferedImage> loadUnit(Path path) {
        Map <Direction, BufferedImage> returned = new HashMap<>();
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

    public Color getDialogColor() {
        return new Color(97, 183, 207);
    }

    public Color getBackgroundShortMenuColor() {
        return new Color(0, 64, 180, 200);
    }
}

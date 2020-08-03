package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;

/**
 * Text-class
 */
public class Text extends Drawing {
    /**
     * Text
     */
    private final String text;
    /**
     * Position of class
     */
    private Point position;
    /**
     * {@link Font}
     */
    private final Font font;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * Size of formation of texts
     */
    private final int size;
    /**
     * Color of texts
     */
    private final Color color = Color.BLACK;
    /**
     * Chooses text or not
     */
    private boolean isChooses = false;
    /**
     * Width from left position
     */
    private int width = -1;

    /**
     * Constructor for class
     *
     * @param text           text
     * @param leftUp         left up {@link Point} for text
     * @param font           {@link Font}
     * @param size           size of formation of text
     * @param dungeonService {@link DungeonService}
     */
    public Text(String text, Point leftUp, Font font, int size, DungeonService dungeonService) {
        this.text = text;
        position = new Point(leftUp.getX(), leftUp.getY() + size);
        this.font = font;
        this.dungeonService = dungeonService;
        this.size = size;
    }

    @Override
    public void draw(Graphics graphics) {
        if (isChooses) {
            graphics.setColor(Color.WHITE);
            if (width == -1) {
                width = dungeonService.getFontService().fontWidth(text, font, graphics);
            }
            graphics.fillRect(position.getX(), position.getY() - size, width, size);
        }
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, position.getX(), position.getY());
    }

    /**
     * Set chooses text or not
     *
     * @param chooses chooses
     */
    public void setChooses(boolean chooses) {
        isChooses = chooses;
    }

    /**
     * Set position of text
     *
     * @param position position
     */
    public void setPosition(Point position) {
        this.position = new Point(position.getX(), position.getY() + size);
    }
}

package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.util.ArrayList;

/**
 * Text for descriptions
 */
public class BigText extends Drawing {
    /**
     * Not parsed text
     */
    private final String notParsedText;
    /**
     * Text
     */
    private ArrayList<String> text;
    /**
     * Position of text
     */
    private final Point position;
    /**
     * Size of text. If y-axis is 0, is not limited
     */
    private final Point size;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * {@link Font}
     */
    private final Font font;
    /**
     * {@link Color} - color of text
     */
    private final Color color;

    /**
     * Constructor of class
     * @param text text, which we show
     * @param position position of text - {@link Point}
     * @param size size of text
     * @param dungeonService {@link DungeonService}
     * @param font {@link Font} - font
     * @param color {@link Color} - color
     */
    public BigText(String text, Point position, Point size, DungeonService dungeonService, Font font, Color color) {
        this.notParsedText = text;
        this.position = position;
        this.size = size;
        this.dungeonService = dungeonService;
        this.font = font;
        this.color = color;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        if (notParsedText.length() != 0 && text == null) {
            text = dungeonService.getFontService().splitByWidth(notParsedText, font, graphics, size.getX());
        }
        for (int it = 0; it < text.size() && (size.getY() == 0 || it * font.getSize() < size.getY()); it++) {
            graphics.drawString(text.get(it), position.getX(), position.getY() + (it + 1) * font.getSize());
        }
    }
}

package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.graphics.FontService;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

/**
 * Button-class
 */
public class Button extends Drawing implements KeyTaker {
    /**
     * {@link Consumer} if we click
     */
    private final Consumer<DungeonService> atClick;
    /**
     * Text on button
     */
    private final String text;
    /**
     * Left up position of button
     */
    private final Point leftUp;
    /**
     * Size of button
     */
    private final Point size;
    /**
     * Text position
     */
    private Point textPosition;
    /**
     * Color of font
     */
    private final Color color;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * {@link Font}
     */
    private final Font font;
    /**
     * If this button chooses
     */
    private boolean isChoose = false;
    /**
     * Font size
     */
    private final int FONT_SIZE = 20;

    /**
     * Calculate text position
     *
     * @return text position
     */
    private Point calculateTextPosition() {
        return new Point(0, (size.getY() + FONT_SIZE) / 2 + leftUp.getY());
    }

    /**
     * @param atClick        {@link Consumer} if we click
     * @param text           Text on button
     * @param leftUp         Left up position of button
     * @param size           Size of button
     * @param color          Color of font
     * @param dungeonService {@link DungeonService}
     * @throws GameException if we have problem with fonts on {@link FontService} getFont method
     */
    public Button(Consumer<DungeonService> atClick, String text, Point leftUp, Point size, Color color, DungeonService dungeonService) throws GameException {
        this.atClick = atClick;
        this.text = text;
        this.leftUp = leftUp;
        this.size = size;
        this.color = color;
        this.dungeonService = dungeonService;
        this.font = dungeonService.getFontService().getFont("Inventory", FONT_SIZE);
        this.textPosition = calculateTextPosition();
    }

    /**
     * @param atClick        {@link Consumer} if we click
     * @param text           Text on button
     * @param leftUp         Left up position of button
     * @param size           Size of button
     * @param dungeonService {@link DungeonService}
     * @throws GameException if we have problem with fonts on {@link FontService} getFont method
     */
    public Button(Consumer<DungeonService> atClick, String text, Point leftUp, Point size, DungeonService dungeonService) throws GameException {
        this.atClick = atClick;
        this.text = text;
        this.leftUp = leftUp;
        this.size = size;
        this.color = Color.WHITE;
        this.dungeonService = dungeonService;
        this.font = dungeonService.getFontService().getFont("Inventory", FONT_SIZE);
        this.textPosition = calculateTextPosition();
    }

    @Override
    public void draw(Graphics graphics) {
        if (textPosition.getX() == 0) {
            int widthFont = dungeonService.getFontService().fontWidth(text, font, graphics);
            textPosition = new Point(textPosition.getX() + (size.getX() - widthFont) / 2, textPosition.getY());
        }
        if (isChoose) {
            graphics.setColor(Color.WHITE.darker());
            graphics.fillRect(leftUp.getX() - 3, leftUp.getY() - 3, size.getX() + 6, size.getY() + 6);
        }
        graphics.setColor(Color.BLACK.brighter());
        graphics.fillRect(leftUp.getX(), leftUp.getY(), size.getX(), size.getY());
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, textPosition.getX(), textPosition.getY());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            atClick.accept(dungeonService);
        }
    }

    /**
     * Set, chooses button or not
     *
     * @param choose boolean
     */
    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}

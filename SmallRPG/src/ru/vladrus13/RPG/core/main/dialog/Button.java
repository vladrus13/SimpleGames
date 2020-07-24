package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class Button extends Drawing implements KeyTaker {
    private final Consumer<DungeonService> atClick;
    private final String text;
    private final Point leftUp;
    private final Point size;
    private Point textPosition;
    private final Color color;
    private final DungeonService dungeonService;
    private final Font font;
    private boolean isChoose = false;
    private final int FONT_SIZE = 20;

    private Point calculateTextPosition() {
        return new Point(0, (size.getY() + FONT_SIZE) / 2 + leftUp.getY());
    }

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

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}

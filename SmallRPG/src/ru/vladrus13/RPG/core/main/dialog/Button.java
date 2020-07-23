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
    private final Point leftUp, size;
    private final Color color;
    private final DungeonService dungeonService;
    private final Font font;
    private boolean isChoose = false;

    public Button(Consumer<DungeonService> atClick, String text, Point leftUp, Point size, Color color, DungeonService dungeonService) throws GameException {
        this.atClick = atClick;
        this.text = text;
        this.leftUp = leftUp;
        this.size = size;
        this.color = color;
        this.dungeonService = dungeonService;
        this.font = dungeonService.getFontService().getFont("Inventory", 10);
    }

    public Button(Consumer<DungeonService> atClick, String text, Point leftUp, Point size, DungeonService dungeonService) throws GameException {
        this.atClick = atClick;
        this.text = text;
        this.leftUp = leftUp;
        this.size = size;
        this.color = Color.WHITE;
        this.dungeonService = dungeonService;
        this.font = dungeonService.getFontService().getFont("Inventory", 20);
    }

    @Override
    public void draw(Graphics graphics) {
        if (isChoose) {
            graphics.setColor(Color.WHITE.darker());
            graphics.fillRect(leftUp.getX() - 2, leftUp.getY() - 2, size.getX() + 2, size.getY() + 2);
        }
        graphics.setColor(Color.BLACK.brighter());
        graphics.fillRect(leftUp.getX(), leftUp.getY(), size.getX(), size.getY());
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, leftUp.getX(), leftUp.getY() + size.getY());
    }


    @Override
    public void keyPressed(KeyEvent e) {
        atClick.accept(dungeonService);
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}

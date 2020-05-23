package ru.vladrus13.core;

import ru.vladrus13.core.utils.Drawing;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.FontService;
import ru.vladrus13.core.utils.picture.PictureService;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ShortMenu implements Drawing {

    private final PictureService pictureService;
    private final Dungeon dungeon;
    private final Font menuFont;

    public ShortMenu(Dungeon dungeon) throws GameException {
        this.dungeon = dungeon;
        this.pictureService = new PictureService();
        this.menuFont = new FontService().getFont("MK-90", 30);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(pictureService.getBackgroundShortMenuColor());
        graphics.fillRect(0, 0, 810, 800);
        graphics.setFont(menuFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString("1     Продолжить", 50, 50);
        graphics.drawString("2     В меню", 50, 85);

    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_1:
                dungeon.notShortMenu();
                break;
            case KeyEvent.VK_2:
                dungeon.notShortMenu();
                dungeon.exitToMenu();
            default:
                break;
        }
    }
}

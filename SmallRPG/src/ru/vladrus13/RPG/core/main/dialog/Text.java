package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;

public class Text extends Drawing {
    private final String text;
    private Point position;
    private final Font font;
    private final DungeonService dungeonService;
    private final int size;
    private final Color color = Color.BLACK;
    private boolean isChooses = false;
    private int width = -1;

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

    public void setChooses(boolean chooses) {
        isChooses = chooses;
    }

    public void setPosition(Point position) {
        this.position = new Point(position.getX(), position.getY() + size);
    }
}

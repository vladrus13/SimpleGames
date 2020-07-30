package ru.vladrus13.RPG.core.main.frame.game;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;
import ru.vladrus13.RPG.core.utils.picture.Updating;
import ru.vladrus13.RPG.core.utils.ways.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.LinkedList;

import static java.awt.event.KeyEvent.*;

public class ArrowGame extends Drawing implements KeyTaker, Updating {

    private final LinkedList<Direction> arrows;
    private int time;
    private int startDrawing;
    private final int leftX = 30, fullTime;
    private final BufferedImage UP, DOWN, LEFT, RIGHT;
    private final DungeonService dungeonService;
    private boolean isFailed = false;
    private boolean isEnd = false;

    public ArrowGame(LinkedList<Direction> arrows, int time, DungeonService dungeonService) throws GameException {
        this.arrows = arrows;
        this.time = time;
        this.fullTime = time;
        this.UP = dungeonService.getPictureService().loadImage(Path.of("assets/pictures/games/arrows/up.png"));
        this.DOWN = dungeonService.getPictureService().loadImage(Path.of("assets/pictures/games/arrows/down.png"));
        this.LEFT = dungeonService.getPictureService().loadImage(Path.of("assets/pictures/games/arrows/left.png"));
        this.RIGHT = dungeonService.getPictureService().loadImage(Path.of("assets/pictures/games/arrows/right.png"));
        this.dungeonService = dungeonService;
    }

    @Override
    public void draw(Graphics graphics) {
        int leftDraw = startDrawing;
        BufferedImage temp;
        for (Direction direction : arrows) {
            if (leftDraw > 800) break;
            switch (direction) {
                case RIGHT:
                    temp = RIGHT;
                    break;
                case LEFT:
                    temp = LEFT;
                    break;
                case UP:
                    temp = UP;
                    break;
                case DOWN:
                    temp = DOWN;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
            graphics.drawImage(temp, leftDraw, 552, 96, 96, null);
            leftDraw += 96;
        }
    }

    public void returnDrawingAndFocus() {
        try {
            dungeonService.getDungeon().removeFocus(this);
        } catch (GameException gameException) {
            gameException.printStackTrace();
        }
        dungeonService.getDungeon().removeDrawing(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isFailed) return;
        int keyCode = e.getKeyCode();
        boolean isAccepted = false;
        switch (keyCode) {
            case VK_UP:
                if (arrows.getFirst() == Direction.UP) {
                    isAccepted = true;
                }
                break;
            case VK_DOWN:
                if (arrows.getFirst() == Direction.DOWN) {
                    isAccepted = true;
                }
                break;
            case VK_RIGHT:
                if (arrows.getFirst() == Direction.RIGHT) {
                    isAccepted = true;
                }
                break;
            case VK_LEFT:
                if (arrows.getFirst() == Direction.LEFT) {
                    isAccepted = true;
                }
                break;
        }
        if (isAccepted) {
            arrows.removeFirst();
            startDrawing += 96;
            if (arrows.isEmpty()) {
                isEnd = true;
                returnDrawingAndFocus();
            }
        }
    }

    @Override
    public void update(DungeonService dungeonService, long time) {
        if (this.time > 0) {
            this.time -= Math.min(time, this.time);
        }
        if (this.time == 0) {
            isFailed = true;
            try {
                dungeonService.getDungeon().removeFocus(this);
            } catch (GameException gameException) {
                gameException.printStackTrace();
            }
            dungeonService.getDungeon().removeDrawing(this);
        }
        startDrawing = Math.max(leftX, startDrawing - 5);
    }

    public boolean isFailed() {
        return isFailed;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

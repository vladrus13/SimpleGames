package ru.vladrus13.RPG.core.main.frame.game;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.graphics.Updating;
import ru.vladrus13.RPG.core.utils.ways.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.LinkedList;

import static java.awt.event.KeyEvent.*;

/**
 * @author vladkuznetsov
 * Arrow game class
 */
@SuppressWarnings("FieldCanBeLocal")
public class ArrowGame extends Drawing implements KeyTaker, Updating {

    /**
     * Collections of sequence arrows
     */
    private final LinkedList<Direction> arrows;
    /**
     * How much time
     */
    private int time;
    /**
     * Position, where we start drawing arrows
     */
    private int startDrawing;
    /**
     * Indent from left border
     */
    private final int leftX = 30;
    /**
     * Time limit.
     */
    private final int fullTime;
    /**
     * {@link BufferedImage} of arrow UP
     */
    private final BufferedImage UP;
    /**
     * {@link BufferedImage} of arrow DOWN
     */
    private final BufferedImage DOWN;
    /**
     * {@link BufferedImage} of arrow LEFT
     */
    private final BufferedImage LEFT;
    /**
     * {@link BufferedImage} of arrow RIGHT
     */
    private final BufferedImage RIGHT;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * Is failed game
     */
    private boolean isFailed = false;
    /**
     * Is game successfully end
     */
    private boolean isEnd = false;

    /**
     * Constructor for class
     *
     * @param arrows         collections of sequence arrows
     * @param time           time limit
     * @param dungeonService {@link DungeonService}
     * @throws GameException if load arrows pictures was failed
     */
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

    /**
     * Remove this from drawing list and no more take keyboard command
     */
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

    /**
     * Is game failed
     *
     * @return boolean
     */
    public boolean isFailed() {
        return isFailed;
    }

    /**
     * Is game successfully end
     *
     * @return boolean
     */
    public boolean isEnd() {
        return isEnd;
    }
}

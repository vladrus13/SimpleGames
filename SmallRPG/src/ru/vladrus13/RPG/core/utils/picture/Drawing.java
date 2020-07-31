package ru.vladrus13.RPG.core.utils.picture;

import java.awt.*;

/**
 * @author vladkuznetsov
 * Abstract class for drawing classes
 */
public abstract class Drawing {
    /**
     * Is this object paused
     */
    protected boolean isPause = false;
    /**
     * Is this object drawing
     */
    protected boolean isDrawing = true;

    /**
     * Draw object
     *
     * @param graphics {@link Graphics} - graphics
     */
    public abstract void draw(Graphics graphics);

    /**
     * Getter for boolean paused
     *
     * @return paused is or not
     */
    public boolean isPause() {
        return isPause;
    }

    /**
     * Setter for boolean paused
     *
     * @param pause paused is or not
     */
    public void setPause(boolean pause) {
        isPause = pause;
    }

    /**
     * Getter for boolean drawing
     *
     * @return drawing is or not
     */
    public boolean isDrawing() {
        return isDrawing;
    }

    /**
     * Setter for boolean drawing
     *
     * @param drawing drawing is or not
     */
    public void setDrawing(boolean drawing) {
        isDrawing = drawing;
    }
}

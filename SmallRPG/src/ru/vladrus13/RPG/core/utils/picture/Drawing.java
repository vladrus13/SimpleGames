package ru.vladrus13.RPG.core.utils.picture;

import java.awt.*;

public abstract class Drawing {
    protected boolean isPause = false;
    protected boolean isDrawing = true;

    public abstract void draw(Graphics graphics);

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isDrawing() {
        return isDrawing;
    }

    public void setDrawing(boolean drawing) {
        isDrawing = drawing;
    }
}

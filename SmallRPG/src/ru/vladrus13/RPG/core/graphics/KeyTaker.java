package ru.vladrus13.RPG.core.graphics;

import java.awt.event.KeyEvent;

/**
 * @author vladkuznetsov
 * Interface for classes, who can takes keyboard commands
 */
public interface KeyTaker {
    /**
     * Keyboard command
     *
     * @param e {@link KeyEvent}
     */
    void keyPressed(KeyEvent e);
}

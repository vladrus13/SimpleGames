package ru.vladrus13.RPG.core.graphics;

import java.awt.event.MouseEvent;

/**
 * @author vladkuznetsov
 * Interface for classes, who can takes mouse commands
 */
public interface MouseTaker {

    /**
     * Mouse command
     *
     * @param e {@link MouseEvent}
     */
    void mousePressed(MouseEvent e);
}

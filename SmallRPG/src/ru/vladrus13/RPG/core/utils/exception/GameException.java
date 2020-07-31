package ru.vladrus13.RPG.core.utils.exception;

/**
 * @author vladkuznetsov
 * Exception class for game
 */
public class GameException extends Exception {
    /**
     * Constructor for exception
     *
     * @param s message
     */
    public GameException(String s) {
        super(s);
    }

    /**
     * Constructor with exception
     *
     * @param s message
     * @param e suppressed exception
     */
    public GameException(String s, Exception e) {
        super(s, e);
    }
}

package ru.vladrus13.core.utils.exception;

public class GameException extends Exception {
    public GameException(String s) {super(s);}
    public GameException(String s, Exception e) {super(s, e);}
}

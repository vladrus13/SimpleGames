package ru.vladrus13;

import ru.vladrus13.core.Dungeon;
import ru.vladrus13.core.utils.exception.GameException;

public class Launcher {

    public static void main(String[] args) {
        if (args.length == 0) {
            try {
                new Dungeon();
            } catch (GameException e) {
                e.printStackTrace();
            }
        }
    }
}

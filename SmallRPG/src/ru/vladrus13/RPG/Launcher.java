package ru.vladrus13.RPG;

import ru.vladrus13.RPG.core.Game;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.io.IOException;
import java.util.logging.LogManager;

public class Launcher {

    public static void main(String[] args) {
        if (args.length == 0) {
            try {
                new Game();
            } catch (GameException e) {
                e.printStackTrace();
            }
        }
    }
}

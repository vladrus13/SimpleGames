package ru.vladrus13.RPG;

import ru.vladrus13.RPG.core.Game;
import ru.vladrus13.RPG.core.utils.exception.GameException;

/**
 * @author vladkuznetsov
 * Class launcher
 */
public class Launcher {

    /**
     * Launcher
     *
     * @param args nothing
     */
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

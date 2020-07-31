package ru.vladrus13.RPG.core;

import java.awt.*;

/**
 * @author vladkuznetsov
 * Menu class
 */
public class Menu {
    /**
     * Button to get dungeon
     */
    final Button buttonDungeon;
    /**
     * Button to exit game
     */
    final Button exit;

    /**
     * Constructor for class
     *
     * @param game  {@link Game}
     * @param frame {@link Frame}
     */
    public Menu(Game game, Frame frame) {
        buttonDungeon = new Button("Новая игра!");
        exit = new Button("Выход!");
        buttonDungeon.setBounds(200, 325, 400, 100);
        exit.setBounds(200, 425, 400, 100);
        buttonDungeon.addActionListener(
                e -> {
                    setVisible(false);
                    game.setStatusGame(Game.STATUS_GAME.DUNGEON);
                }
        );
        exit.addActionListener(e -> System.exit(0));
        frame.add(buttonDungeon);
        frame.add(exit);
        setVisible(true);
    }

    /**
     * Set visibility of menu
     *
     * @param b boolean visibility
     */
    public void setVisible(boolean b) {
        buttonDungeon.setVisible(b);
        exit.setVisible(b);
    }
}

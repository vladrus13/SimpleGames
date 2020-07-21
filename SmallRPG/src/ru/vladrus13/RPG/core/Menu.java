package ru.vladrus13.RPG.core;

import java.awt.*;

public class Menu {
    final Button buttonDungeon;
    final Button exit;
    Game game;

    public Menu(Game game, Frame frame) {
        buttonDungeon = new Button("Новая игра!");
        exit = new Button("Выход!");
        buttonDungeon.setBounds(200, 325, 400, 100);
        exit.setBounds(200, 425, 400, 100);
        buttonDungeon.addActionListener(
                e -> {
                    setVisible(false);
                    game.setStatusGame(Game.STATUSGAME.DUNGEON);
                }
        );
        exit.addActionListener(e -> System.exit(0));
        frame.add(buttonDungeon);
        frame.add(exit);
        setVisible(true);
    }

    public void setVisible(boolean b) {
        buttonDungeon.setVisible(b);
        exit.setVisible(b);
    }
}

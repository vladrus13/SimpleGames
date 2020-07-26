package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.Drawing;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Choose extends Drawing implements KeyTaker {

    Button[] buttons;
    private final DungeonService dungeonService;
    int chooses = 0;
    private final int X_START_BUTTON = 50;
    private final int X_FINISH_BUTTON = 750;
    private final int HEIGHT_BUTTON = 50;

    public Choose(ArrayList<String> texts, ArrayList<Consumer<DungeonService>> atClickArray, DungeonService dungeonService) throws GameException {
        this.dungeonService = dungeonService;
        if (texts.size() > 5) {
            throw new GameException("Too many buttons. May be fixed in next versions");
            // TODO fix this. But I can't generate infinity count. Maybe I can use "next page" "previous page" buttons.
        }
        if (texts.size() < 1) {
            throw new IndexOutOfBoundsException("Can't create 0 buttons");
        }
        if (texts.size() != atClickArray.size()) {
            throw new GameException("Texts count and consumers count not equals");
        }
        buttons = new Button[texts.size()];
        int start_y = 50;
        int interval = (400 - HEIGHT_BUTTON) / (buttons.length - 1) - HEIGHT_BUTTON;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(
                    atClickArray.get(i), texts.get(i),
                    new Point(X_START_BUTTON, start_y),
                    new Point(X_FINISH_BUTTON - X_START_BUTTON, HEIGHT_BUTTON),
                    dungeonService);
            start_y += interval + HEIGHT_BUTTON;
        }
        buttons[0].setChoose(true);
    }

    @Override
    public void draw(Graphics graphics) {
        for (Button button : buttons) {
            button.draw(graphics);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                buttons[chooses].setChoose(false);
                chooses = (chooses + buttons.length - 1) % buttons.length;
                buttons[chooses].setChoose(true);
                break;
            case KeyEvent.VK_DOWN:
                buttons[chooses].setChoose(false);
                chooses = (chooses + 1) % buttons.length;
                buttons[chooses].setChoose(true);
                break;
            case KeyEvent.VK_ENTER:
                dungeonService.getDungeon().removeDrawing(this);
                try {
                    dungeonService.getDungeon().removeFocus(this);
                } catch (GameException gameException) {
                    gameException.printStackTrace();
                }
                buttons[chooses].keyPressed(e);
                break;
            case KeyEvent.VK_ESCAPE:
                dungeonService.getDungeon().removeDrawing(this);
                try {
                    dungeonService.getDungeon().removeFocus(this);
                } catch (GameException gameException) {
                    gameException.printStackTrace();
                }
                break;
        }
    }
}

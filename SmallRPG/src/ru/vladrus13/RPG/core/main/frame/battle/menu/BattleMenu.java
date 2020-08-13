package ru.vladrus13.RPG.core.main.frame.battle.menu;

import ru.vladrus13.RPG.core.main.dialog.KeyTakerReturner;
import ru.vladrus13.RPG.core.main.frame.battle.Battle;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Battle menu for {@link Battle}
 */
public class BattleMenu extends KeyTakerReturner {




    @Override
    public void draw(Graphics graphics) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public Object returner() throws GameException {
        if (!isEnd) {
            throw new GameException("Battle menu not end");
        }
        return null;
    }
}

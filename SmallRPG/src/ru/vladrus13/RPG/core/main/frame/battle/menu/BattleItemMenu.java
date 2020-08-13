package ru.vladrus13.RPG.core.main.frame.battle.menu;

import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.main.dialog.KeyTakerReturner;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Item menu on battle
 */
public class BattleItemMenu extends KeyTakerReturner {

    ArrayList<Inventory.Items> items;

    public BattleItemMenu(DungeonService dungeonService) {

    }

    @Override
    public Object returner() throws GameException {
        return null;
    }

    @Override
    public void draw(Graphics graphics) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Is inventory empty
     */
    public void isEmpty() {

    }
}

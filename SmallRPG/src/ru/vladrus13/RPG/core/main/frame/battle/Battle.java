package ru.vladrus13.RPG.core.main.frame.battle;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.graphics.Updating;
import ru.vladrus13.RPG.core.main.dialog.KeyTakerReturner;
import ru.vladrus13.RPG.core.person.Hero;
import ru.vladrus13.RPG.core.utils.DungeonService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author vladkuznetsov
 * Abstract class for battle
 */
public class Battle extends Drawing implements KeyTaker, Updating {

    Hero hero;
    BattlePerson enemy;
    KeyTakerReturner currentKeyTaker;

    @Override
    public void draw(Graphics graphics) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void update(DungeonService dungeonService, long time) {

    }
}

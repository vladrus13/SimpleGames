package ru.vladrus13.RPG.core.main.frame.battle;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.graphics.Updating;
import ru.vladrus13.RPG.core.main.dialog.KeyTakerReturner;
import ru.vladrus13.RPG.core.utils.DungeonService;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author vladkuznetsov
 * Abstract class for battle
 */
public abstract class Battle extends Drawing implements KeyTaker, Updating {

    /**
     * Hero on battle
     */
    private final BattlePerson hero;
    /**
     * Enemy on battle
     */
    private final BattlePerson enemy;
    /**
     * Current key taker
     */
    private KeyTakerReturner currentKeyTaker;

    /**
     * Constructor of class
     * @param hero hero
     * @param enemy enemy
     */
    public Battle(BattlePerson hero, BattlePerson enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 200));
        graphics.fillRect(0, 0, 800, 800);
        hero.draw(graphics);
        enemy.draw(graphics);
        currentKeyTaker.draw(graphics);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentKeyTaker != null) {
            currentKeyTaker.keyPressed(e);
            if (currentKeyTaker.isEnd()) {
                // TODO isAccepted
                currentKeyTaker = null;
            }
        }
    }

    @Override
    public void update(DungeonService dungeonService, long time) {
        if (currentKeyTaker instanceof Updating) {
            ((Updating) currentKeyTaker).update(dungeonService, time);
        }
    }

    /**
     * Next turn for hero
     */
    public abstract void nextTurn();
}

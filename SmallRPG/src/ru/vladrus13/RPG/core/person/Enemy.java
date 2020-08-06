package ru.vladrus13.RPG.core.person;

import ru.vladrus13.RPG.core.graphics.ColorService;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

/**
 * @author vladkuznetsov
 * Enemy class for {@link ru.vladrus13.RPG.core.Dungeon}
 */
public class Enemy extends Person {
    /**
     * {@link Stats} - stats
     */
    protected final Stats stats;
    /**
     * Attack speed
     */
    protected final long attackSpeed;
    /**
     * Time form last attack
     */
    protected long timeFromLastAttack;

    /**
     * Do random move
     *
     * @param used           used titles
     * @param dungeonService {@link DungeonService}
     * @return where we went
     */
    public Point next(HashSet<Point> used, DungeonService dungeonService) {
        if (dungeonService.getHero().getPlace().ManhattanDistance(getPlace()) == 1) {
            dungeonService.getHero().damage(stats.getAttack());
            return getPlace();
        } else {
            int random = new Random().nextInt(4);
            Direction direction;
            switch (random) {
                case 0:
                    direction = Direction.UP;
                    break;
                case 1:
                    direction = Direction.DOWN;
                    break;
                case 2:
                    direction = Direction.LEFT;
                    break;
                case 3:
                    direction = Direction.RIGHT;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + random);
            }
            Point future = getPlace().makePoint(direction);
            if (!used.contains(future) && dungeonService.getCurrentFloor().isCanWalk(future)) {
                startWent(direction, dungeonService);
                return future;
            } else {
                return getPlace();
            }
        }
    }

    /**
     * Constructor for class
     *
     * @param id          id
     * @param place       {@link Point} - place
     * @param direction   {@link Direction} - direction
     * @param name        name of enemy
     * @param stats       {@link Stats} - stats
     * @param speed       speed of walking
     * @param attackSpeed attack speed
     */
    public Enemy(int id, Point place, Direction direction, String name, Stats stats, int speed, long attackSpeed) {
        super(id, place, direction, name, speed);
        this.stats = stats;
        this.attackSpeed = attackSpeed;
        this.timeFromLastAttack = System.currentTimeMillis();
    }

    @Override
    public void draw(Graphics graphics) {
        if (!isDead()) {
            super.draw(graphics);
            graphics.setColor(ColorService.underHPColor);
            // int posX = 32 * getPlace().getX(), posY = 32 * getPlace().getY();
            int posX = realPlace.getX(), posY = realPlace.getY();
            graphics.fillRect(posX, posY, 32, 8);
            graphics.setColor(ColorService.HPColor);
            graphics.fillRect(posX, posY, 32 * stats.getHp() / stats.getMaxHp(), 8);
        }
    }

    /**
     * Get damage
     *
     * @param damage how much we get
     */
    public void damage(int damage) {
        this.stats.setHp(Math.max(0, stats.getHp() - Math.max(0, damage - stats.getArmor())));
    }

    @Override
    public Enemy clone() {
        return new Enemy(id, place.clone(), direction, name, stats.clone(), speed, attackSpeed);
    }

    /**
     * Is enemy dead
     *
     * @return true, if dead, false else
     */
    public boolean isDead() {
        return stats.getHp() <= 0;
    }
}

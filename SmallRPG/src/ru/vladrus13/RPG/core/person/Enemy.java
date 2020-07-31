package ru.vladrus13.RPG.core.person;

import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.picture.ColorService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class Enemy extends Person {
    protected final Stats stats;
    protected final long attackSpeed;
    protected long timeFromLastAttack;

    public Point next(HashSet<Point> used, DungeonService dungeonService) {
        if (dungeonService.getHero().getPlace().distance(getPlace()) == 1) {
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

    public void damage(int damage) {
        this.stats.setHp(Math.max(0, stats.getHp() - Math.max(0, damage - stats.getArmor())));
    }

    @Override
    public Enemy clone() {
        return new Enemy(id, place.clone(), direction, name, stats.clone(), speed, attackSpeed);
    }

    public boolean isDead() {
        return stats.getHp() <= 0;
    }
}

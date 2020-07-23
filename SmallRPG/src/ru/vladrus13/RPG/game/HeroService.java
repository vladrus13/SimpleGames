package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.person.unit.Hero;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;

import static ru.vladrus13.RPG.core.utils.ways.Direction.UP;

public class HeroService {
    Hero hero;

    public HeroService(DungeonService dungeonService) {
        try {
            hero = new Hero(0, new Point(1, 1), UP, dungeonService);
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}

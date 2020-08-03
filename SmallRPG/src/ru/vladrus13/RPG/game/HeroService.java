package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.person.Hero;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import static ru.vladrus13.RPG.core.utils.ways.Direction.UP;

/**
 * @author vladkuznetsov
 * Hero service
 */
public class HeroService {

    /**
     * {@link Hero} - hero
     */
    Hero hero;

    /**
     * Constructor for class
     *
     * @param dungeonService {@link DungeonService}
     */
    public HeroService(DungeonService dungeonService) {
        hero = new Hero(0, new Point(1, 1), UP, dungeonService, "Hero");
    }

    /**
     * Getter for hero
     *
     * @return {@link Hero}
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Setter for hero
     *
     * @param hero hero
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }
}

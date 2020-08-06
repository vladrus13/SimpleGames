package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.person.Hero;
import ru.vladrus13.RPG.core.person.Skills;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.event.KeyEvent;

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
        Skills heroSkills = new Skills();
        heroSkills.set(0, dungeonService.getSkillFactory().get("UsualAttack"));
        heroSkills.set(KeyEvent.VK_C, dungeonService.getSkillFactory().get("Combustion"));
        hero = new Hero(0, new Point(1, 1), UP, dungeonService, "Hero", heroSkills);
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

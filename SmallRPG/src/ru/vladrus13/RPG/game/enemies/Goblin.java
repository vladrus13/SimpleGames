package ru.vladrus13.RPG.game.enemies;

import ru.vladrus13.RPG.core.person.Enemy;
import ru.vladrus13.RPG.core.person.Skills;
import ru.vladrus13.RPG.core.person.Stats;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Goblin class - enemy
 */
public class Goblin extends Enemy {
    /**
     * Constructor for class
     *
     * @param dungeonService {@link DungeonService}
     */
    public Goblin(DungeonService dungeonService) {
        super(4, new Point(0, 0), Direction.UP, "Goblin", new Stats(50, 100, 0, 5, 0),
                new Skills(new ArrayList<>(Collections.singleton(dungeonService.getSkillFactory().get("UsualAttack")))), 2);
    }
}

package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.buff.Skill;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.game.skills.UsualAttack;

import java.util.HashMap;

/**
 * Skill factory
 */
public class SkillFactory {

    /**
     * Skills map by name
     */
    HashMap<String, Skill> skills;

    /**
     * Constructor for class
     * @param dungeonService {@link DungeonService}
     */
    public SkillFactory(DungeonService dungeonService) {
        skills = new HashMap<>();
        try {
            skills.put("UsualAttack", new UsualAttack(dungeonService));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for skills
     * @param name skill name
     * @return skill
     */
    public Skill get(String name) {
        return skills.get(name);
    }
}

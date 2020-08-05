package ru.vladrus13.RPG.core.person;

import ru.vladrus13.RPG.core.buff.Skill;

import java.util.ArrayList;

/**
 * @author vladkuznetsov
 * Skills class for {@link Person}
 */
public class Skills {
    /**
     * Skills array
     */
    private final ArrayList<Skill> skills;

    /**
     * Constructor for class
     *
     * @param skills skills array
     */
    public Skills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Constructor for class without any skills
     */
    public Skills() {
        this.skills = new ArrayList<>();
    }

    /**
     * Getter for skills
     *
     * @return skills array
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Get skill by his keycode
     * @param keyCode keycode
     * @return {@link Skill}
     */
    public Skill getSkill(int keyCode) {
        return skills.get(keyCode);
    }

    /**
     * Set on keyCode this skill
     * @param keyCode keyCode - {@link java.awt.event.KeyEvent}
     * @param skill {@link Skill}
     */
    public void add(int keyCode, Skill skill) {
        skills.add(keyCode, skill);
    }
}

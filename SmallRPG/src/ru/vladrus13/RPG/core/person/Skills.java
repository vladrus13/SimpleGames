package ru.vladrus13.RPG.core.person;

import ru.vladrus13.RPG.core.buff.Skill;

import java.util.ArrayList;
import java.util.Collections;

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
     * Last turn time
     */
    private final ArrayList<Long> lastTurn;

    /**
     * Constructor for class
     *
     * @param skills skills array
     */
    public Skills(ArrayList<Skill> skills) {
        this.skills = skills;
        lastTurn = new ArrayList<>(Collections.nCopies(skills.size(), 0L));
    }

    /**
     * Constructor for class without any skills
     */
    public Skills() {
        this.skills = new ArrayList<>();
        this.lastTurn = new ArrayList<>();
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
     *
     * @param keyCode keycode
     * @return {@link Skill}
     */
    public Skill getSkill(int keyCode) {
        return skills.get(keyCode);
    }

    /**
     * Set on keyCode this skill
     *
     * @param keyCode keyCode - {@link java.awt.event.KeyEvent}
     * @param skill   {@link Skill}
     */
    public void set(int keyCode, Skill skill) {
        if (keyCode < 0) {
            throw new IndexOutOfBoundsException("Index is negative");
        }
        if (keyCode > 1000) {
            throw new IllegalArgumentException("Keycode more than 1000: " + keyCode);
        }
        while (skills.size() <= keyCode) {
            skills.add(null);
            lastTurn.add(0L);
        }
        skills.set(keyCode, skill);
    }

    /**
     * @param keyCode keyCode
     * @return true, if we can use this skill, else false (because cooldown)
     */
    public boolean canTake(int keyCode) {
        return System.currentTimeMillis() - lastTurn.get(keyCode) >= skills.get(keyCode).getCooldown();
    }

    /**
     * Reset last using skill
     * @param keyCode keyCode
     */
    public void use(int keyCode) {
        lastTurn.set(keyCode, System.currentTimeMillis());
    }
}

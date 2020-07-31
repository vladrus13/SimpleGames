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
}

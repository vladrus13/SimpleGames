package ru.vladrus13.core.person;

import ru.vladrus13.core.buff.Skill;

import java.util.ArrayList;


public class Skills {
    private final ArrayList<Skill> skills;

    public Skills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Skills() { this.skills = new ArrayList<>(); }

    public ArrayList<Skill> getSkills() {
        return skills;
    }
}

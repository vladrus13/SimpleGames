package ru.vladrus13.RPG.core.buff;

import ru.vladrus13.RPG.core.buff.on.Activating;
import ru.vladrus13.RPG.core.person.Stats;

public class Skill implements Activating {

    @Override
    public Stats onActivate(Stats stats) {
        return stats;
    }
}

package ru.vladrus13.core.buff;

import ru.vladrus13.core.buff.on.Activating;
import ru.vladrus13.core.person.Stats;

public class Skill implements Activating {

    @Override
    public Stats onActivate(Stats stats) {
        return stats;
    }
}

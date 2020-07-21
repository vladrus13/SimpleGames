package ru.vladrus13.RPG.core.buff.on;

import ru.vladrus13.RPG.core.person.Stats;

public interface Activating {
    Stats onActivate(Stats stats);
}

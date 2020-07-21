package ru.vladrus13.RPG.core.buff.on;

import ru.vladrus13.RPG.core.person.Stats;

public interface Attacking {
    Stats onAttack(Stats stats);
}

package ru.vladrus13.RPG.core.utils.unit;

import ru.vladrus13.RPG.core.utils.exception.GameException;

public class UnitService {
    public String getNameFromId(int id) throws GameException {
        if (id == 0) return "Hero";
        if (id == -2) return "Cashier";
        throw new GameException("Unknown person with id: " + id);
    }
}

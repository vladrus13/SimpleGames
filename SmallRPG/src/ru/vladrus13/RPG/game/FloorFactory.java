package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.main.dungeon.Floor;
import ru.vladrus13.RPG.core.utils.exception.GameException;

public class FloorFactory {

    private final Floor[] floors;



    public FloorFactory() {
        floors = new Floor[2];
        floors[0] = new Floor(1);
        floors[1] = new Floor(2);
    }

    public Floor getCurrentFloor(int floor) throws GameException {
        if (floor < 0 || floors.length <= floor) {
            throw new GameException("Can't take " + floor + " floor from factory");
        }
        return floors[floor];
    }
}

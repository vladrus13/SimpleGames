package ru.vladrus13.RPG.core.buff.on;

import ru.vladrus13.RPG.core.utils.DungeonService;

/**
 * @author vladkuznetsov
 * Special interface for defending
 */
public interface Defending {

    /**
     * Method for change {@link DungeonService} on defending
     *
     * @param dungeonService {@link DungeonService} - main dungeon service
     * @return changed dungeon service
     */
    DungeonService onDefence(DungeonService dungeonService);
}

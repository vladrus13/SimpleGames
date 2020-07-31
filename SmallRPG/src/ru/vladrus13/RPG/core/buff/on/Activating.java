package ru.vladrus13.RPG.core.buff.on;

import ru.vladrus13.RPG.core.utils.DungeonService;

/**
 * @author vladkuznetsov
 * Special interface for activating skills or items
 */
public interface Activating {

    /**
     * Method for change {@link DungeonService} on activating skill or item
     *
     * @param dungeonService {@link DungeonService} - main dungeon service
     * @return changed dungeon service
     */
    DungeonService onActivate(DungeonService dungeonService);
}

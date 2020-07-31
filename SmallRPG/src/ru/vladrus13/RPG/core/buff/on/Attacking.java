package ru.vladrus13.RPG.core.buff.on;

import ru.vladrus13.RPG.core.utils.DungeonService;

/**
 * @author vladkuznetsov
 * Special interface for attacking skills or items
 */
public interface Attacking {

    /**
     * Method for change {@link DungeonService} on attacking skill or item
     *
     * @param dungeonService {@link DungeonService} - main dungeon service
     * @return changed dungeon service
     */
    DungeonService onAttack(DungeonService dungeonService);
}

package ru.vladrus13.RPG.core.utils.picture;

import ru.vladrus13.RPG.core.utils.DungeonService;

/**
 * @author vladkuznetsov
 * Interface for updating every frame classes
 */
public interface Updating {
    /**
     * Update object
     *
     * @param dungeonService {@link DungeonService}
     * @param time           how much time from last frame
     */
    void update(DungeonService dungeonService, long time);
}

package ru.vladrus13.RPG.core.buff.on;

import ru.vladrus13.RPG.core.utils.DungeonService;

public interface Activating {
    DungeonService onActivate(DungeonService dungeonService);
}

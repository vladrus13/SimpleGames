package ru.vladrus13.RPG.core.buff;

import ru.vladrus13.RPG.core.buff.on.Activating;
import ru.vladrus13.RPG.core.graphics.Animation;
import ru.vladrus13.RPG.core.utils.DungeonService;

public class Skill implements Activating {
    private final Animation animation;

    Skill(Animation animation) {
        this.animation = animation;
    }

    @Override
    public DungeonService onActivate(DungeonService dungeonService) {
        return dungeonService;
    }

    public Animation getAnimation() {
        return animation;
    }
}

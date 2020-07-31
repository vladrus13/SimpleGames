package ru.vladrus13.RPG.core.buff;

import ru.vladrus13.RPG.core.buff.on.Activating;
import ru.vladrus13.RPG.core.graphics.Animation;
import ru.vladrus13.RPG.core.utils.DungeonService;

/**
 * @author vladkuznetsov
 * Class-skills for {@link ru.vladrus13.RPG.core.person.Person}
 */
public class Skill implements Activating {
    /**
     * {@link Animation} on using this skill
     */
    private final Animation animation;

    /**
     * Constructor for Skill
     *
     * @param animation animation
     */
    Skill(Animation animation) {
        this.animation = animation;
    }

    @Override
    public DungeonService onActivate(DungeonService dungeonService) {
        return dungeonService;
    }

    /**
     * Getter for animation. Using if we need play animation.
     *
     * @return {@link Animation}
     */
    public Animation getAnimation() {
        return animation;
    }
}

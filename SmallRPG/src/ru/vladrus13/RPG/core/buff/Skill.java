package ru.vladrus13.RPG.core.buff;

import ru.vladrus13.RPG.core.buff.on.Activating;
import ru.vladrus13.RPG.core.graphics.Animation;
import ru.vladrus13.RPG.core.sound.Sound;
import ru.vladrus13.RPG.core.utils.DungeonService;

/**
 * @author vladkuznetsov
 * Class-skills for {@link ru.vladrus13.RPG.core.person.Person}
 */
public abstract class Skill implements Activating {
    /**
     * {@link Animation} on using this skill on dungeon
     */
    protected final Animation dungeonAnimation;
    /**
     * {@link Animation} on using this skill on battle
     */
    protected final Animation battleAnimation;

    /**
     * Sound of used
     */
    protected final Sound sound;

    /**
     * keyCode to activate skill
     */
    protected final int keyCode;
    /**
     * Constructor for Skill
     *  @param dungeonAnimation dungeon animation. Must be null, if this skill not used on dungeon
     * @param battleAnimation battle animation, Must be null, if this skill not used on battle
     * @param sound sound, that we play on use skill
     * @param keyCode keycode activate this skill
     */
    public Skill(Animation dungeonAnimation, Animation battleAnimation, Sound sound, int keyCode) {
        this.dungeonAnimation = dungeonAnimation;
        this.battleAnimation = battleAnimation;
        this.keyCode = keyCode;
        this.sound = sound;
    }

    @Override
    public abstract void onActivate(DungeonService dungeonService);

    /**
     * Getter for animation. Using if we need play animation.
     *
     * @return {@link Animation}
     */
    public Animation getDungeonAnimation() {
        return dungeonAnimation;
    }

    /**
     * Getter for animation. Using if we need play animation.
     * @return {@link Animation}
     */
    public Animation getBattleAnimation() {
        return battleAnimation;
    }

    /**
     * @return used on battle or not
     */
    public boolean usedOnBattle() {
        return battleAnimation != null;
    }

    /**
     * @return used on dungeon or not
     */
    public boolean usedOnDungeon() {
        return dungeonAnimation != null;
    }
}

package ru.vladrus13.RPG.game.skills;

import ru.vladrus13.RPG.core.buff.Skill;
import ru.vladrus13.RPG.core.graphics.Animation;
import ru.vladrus13.RPG.core.person.Stats;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.event.KeyEvent;
import java.nio.file.Path;

/**
 * Heal skill
 */
public class Heal extends Skill {

    /**
     * Constructor for Skill
     *
     * @param dungeonService {@link DungeonService}
     * @throws GameException if we can't load image
     */
    public Heal(DungeonService dungeonService) throws GameException {
        super("Heal", "Healing you",
                new Animation(dungeonService.getPictureService().loadImage(Path.of("resources/assets/pictures/animations/Heal.png")),
                        100, new Point(0, 0), new Point(192, 192), new Point(32, 32)),
                new Animation(dungeonService.getPictureService().loadImage(Path.of("resources/assets/pictures/animations/Heal.png")),
                        100, new Point(0, 0), new Point(192, 192)),
                dungeonService.getSoundFactory().getCloned("skills/Heal"),
                KeyEvent.VK_H,
                2000);
    }

    @Override
    public void onActivate(DungeonService dungeonService) {
        sound.stop();
        sound.play(0.5D);
        Stats stats = dungeonService.getHero().getStats();
        stats.setHp(Math.min(stats.getMaxHp(), stats.getHp() + 10));
        Animation animation = dungeonAnimation.clone();
        Point heroPoint = dungeonService.getHero().getPlace();
        animation.setPosition(new Point(heroPoint.getX() * 32, heroPoint.getY() * 32));
        dungeonService.getDungeon().addDrawing(animation);
    }
}

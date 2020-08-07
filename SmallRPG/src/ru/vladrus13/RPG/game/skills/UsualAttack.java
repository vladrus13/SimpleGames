package ru.vladrus13.RPG.game.skills;

import ru.vladrus13.RPG.core.buff.Skill;
import ru.vladrus13.RPG.core.graphics.Animation;
import ru.vladrus13.RPG.core.person.Enemy;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.nio.file.Path;

/**
 * Usual attack
 */
public class UsualAttack extends Skill {

    /**
     * Constructor for Skill
     *
     * @param dungeonService {@link DungeonService}
     * @throws GameException if pictures can't find
     */
    public UsualAttack(DungeonService dungeonService) throws GameException {
        super(
                "Usual attack", "Usual attack", new Animation(dungeonService.getPictureService().loadImage(Path.of("resources/assets/pictures/animations/UsualAttack.png")),
                        100, new Point(0, 0), new Point(192, 192), new Point(32, 32)),
                new Animation(dungeonService.getPictureService().loadImage(Path.of("resources/assets/pictures/animations/UsualAttack.png")),
                        100, new Point(0, 0), new Point(192, 192)),
                dungeonService.getSoundFactory().getCloned("skills/UsualAttack"),
                0,
                500
        );
    }

    @Override
    public void onActivate(DungeonService dungeonService) {
        Point attackPoint = dungeonService.getHero().getPlace().makePoint(dungeonService.getHero().getDirection());
        Animation animation = dungeonAnimation.clone();
        sound.stop();
        sound.play(0.5D);
        animation.setPosition(new Point(attackPoint.getX() * 32, attackPoint.getY() * 32));
        dungeonService.getDungeon().addDrawing(animation);
        if (dungeonService.getCurrentFloor().isPerson(attackPoint) && dungeonService.getCurrentFloor().getPerson(attackPoint) instanceof Enemy) {
            ((Enemy) dungeonService.getCurrentFloor().getPerson(attackPoint)).damage(dungeonService.getHero().getStats().getAttack());
        }
    }
}

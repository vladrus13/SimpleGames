package ru.vladrus13.RPG.game.skills;


import ru.vladrus13.RPG.core.buff.Skill;
import ru.vladrus13.RPG.core.graphics.Animation;
import ru.vladrus13.RPG.core.person.Enemy;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;
import ru.vladrus13.RPG.core.utils.ways.WayService;

import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Combustion skill
 */
public class Combustion extends Skill {

    /**
     * @param dungeonService {@link DungeonService}
     * @throws GameException if loading pictures is failed
     */
    public Combustion(DungeonService dungeonService) throws GameException {
        super(
                "Combustion", "Fire-element attack", new Animation(dungeonService.getPictureService().loadImage(Path.of("resources/assets/pictures/animations/Combustion.png")),
                        70, new Point(0, 0), new Point(192, 192), new Point(96, 96)),
                new Animation(dungeonService.getPictureService().loadImage(Path.of("resources/assets/pictures/animations/Combustion.png")),
                        70, new Point(0, 0), new Point(192, 192)),
                dungeonService.getSoundFactory().getCloned("skills/Combustion"),
                KeyEvent.VK_C,
                1000
        );
    }

    @Override
    public void onActivate(DungeonService dungeonService) {
        Point heroPoint = dungeonService.getHero().getPlace();
        Animation animation = dungeonAnimation.clone();
        sound.stop();
        sound.play(0.5D);
        animation.setPosition(new Point((heroPoint.getX() - 1) * 32, (heroPoint.getY() - 1) * 32));
        dungeonService.getDungeon().addDrawing(animation);
        ArrayList<Point> onDistance = WayService.findPointInDistance(heroPoint, 2);
        for (Point p : onDistance) {
            if (dungeonService.getCurrentFloor().isPerson(p)) {
                Person person = dungeonService.getCurrentFloor().getPerson(p);
                if (person instanceof Enemy) {
                    ((Enemy) person).damage(30);
                }
            }
        }
    }
}
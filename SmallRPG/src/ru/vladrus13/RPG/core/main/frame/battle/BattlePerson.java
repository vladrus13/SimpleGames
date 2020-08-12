package ru.vladrus13.RPG.core.main.frame.battle;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.person.Skills;
import ru.vladrus13.RPG.core.person.Stats;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Battle person class
 */
public class BattlePerson extends Drawing {

    /**
     * Image of person
     */
    private final BufferedImage image;
    /**
     * Position of image
     */
    private final Point position;
    /**
     * {@link Skills}
     */
    private final Skills skills;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * {@link Stats}
     */
    private final Stats stats;

    /**
     * Constructor for class
     * @param image image of person
     * @param position position of image
     * @param skills {@link Skills}
     * @param dungeonService {@link DungeonService}
     * @param stats {@link Stats}
     */
    public BattlePerson(BufferedImage image, Point position, Skills skills, DungeonService dungeonService, Stats stats) {
        this.image = image;
        this.position = position;
        this.skills = skills;
        this.dungeonService = dungeonService;
        this.stats = stats;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(image, position.getX(), position.getY(), null);
    }
}

package ru.vladrus13.RPG.core.person;

import ru.vladrus13.RPG.core.main.dungeon.Placeable;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.graphics.PictureService;
import ru.vladrus13.RPG.core.graphics.Updating;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author vladkuznetsov
 * Person class
 */
public class Person extends Placeable implements Updating, Cloneable {
    /**
     * Real position on screen
     */
    protected Point realPlace;
    /**
     * Map of {@link BufferedImage} of person
     */
    protected Map<Direction, BufferedImage> picture;
    /**
     * {@link Direction} - current direction of person
     */
    protected Direction direction;
    /**
     * Moving speed of person
     */
    protected final int speed;
    /**
     * Name of person
     */
    protected final String name;
    /**
     * Walking queue
     */
    protected final Queue<Direction> directionQueue;

    /**
     * Get current direction
     *
     * @return {@link Direction} - direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Get name of person
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Constructor for class
     *
     * @param id        id
     * @param place     {@link Point} - place
     * @param direction {@link Direction} - direction
     * @param name      name of person
     */
    public Person(int id, Point place, Direction direction, String name) {
        this(id, place, direction, name, 2);
    }

    /**
     * Constructor for class
     *
     * @param id        id
     * @param place     {@link Point} - place
     * @param direction {@link Direction} - direction
     * @param name      name of person
     * @param speed     speed of walking
     */
    public Person(int id, Point place, Direction direction, String name, int speed) {
        super(id, place);
        this.name = name;
        this.picture = new PictureService().loadUnit(Path.of("resources/assets/pictures/units/" + name.toLowerCase()));
        this.realPlace = new Point(place.getX() * 32, place.getY() * 32);
        this.direction = direction;
        directionQueue = new LinkedList<>();
        this.speed = speed;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(picture.get(direction), realPlace.getX(), realPlace.getY(), 32, 32, null);
    }

    @Override
    public void update(DungeonService dungeonService, long time) {
        if (isWent()) {
            went(dungeonService);
        } else {
            if (!directionQueue.isEmpty()) {
                startWent(directionQueue.poll(), dungeonService);
            }
        }
    }

    /**
     * Went on speed
     *
     * @param dungeonService {@link DungeonService}
     */
    public void went(DungeonService dungeonService) {
        switch (direction) {
            case UP:
                realPlace.incY(-speed);
                break;
            case DOWN:
                realPlace.incY(speed);
                break;
            case LEFT:
                realPlace.incX(-speed);
                break;
            case RIGHT:
                realPlace.incX(speed);
                break;
        }
    }

    /**
     * Start went
     *
     * @param direction      where we went
     * @param dungeonService {@link DungeonService}
     */
    public void startWent(Direction direction, DungeonService dungeonService) {
        if (isWent()) return;
        this.direction = direction;
        Point future = new Point(place.getX(), place.getY());
        future = future.makePoint(direction);
        if (dungeonService.getCurrentFloor().isCanWalk(future)) {
            place = place.makePoint(direction);
            went(dungeonService);
        }
    }

    /**
     * Is we went
     *
     * @return true, if went, false else
     */
    public boolean isWent() {
        return realPlace.getX() != place.getX() * 32 || realPlace.getY() != place.getY() * 32;
    }

    /**
     * If we press enter
     *
     * @param dungeonService {@link DungeonService}
     * @throws GameException overload
     */
    public void onPressEnter(DungeonService dungeonService) throws GameException {
        if (id == 2) {
            dungeonService.getEventFactory().get("PirateDialog").run(dungeonService);
        }
    }

    /**
     * Teleport person
     *
     * @param place where teleport
     */
    public void setPoint(Point place) {
        this.place = place;
        this.realPlace = new Point(this.place.getX() * 32, this.place.getY() * 32);
    }

    /**
     * Teleport people
     *
     * @param place     where teleport
     * @param direction {@link Direction} - direction
     */
    public void teleport(Point place, Direction direction) {
        setPoint(place);
        this.direction = direction;
    }

    // TODO warnings ??????
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Person clone() {
        return new Person(id, place.clone(), direction, name, speed);
    }
}

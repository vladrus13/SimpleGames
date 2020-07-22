package ru.vladrus13.RPG.core.person;

import ru.vladrus13.RPG.core.main.dialog.Dialog;
import ru.vladrus13.RPG.core.main.dungeon.Placeable;
import ru.vladrus13.RPG.core.utils.Drawing;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.PictureService;
import ru.vladrus13.RPG.core.utils.unit.UnitService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Person extends Placeable implements Drawing {
    protected Point realPlace;
    protected Map<Direction, BufferedImage> picture;
    protected Direction direction;
    protected final int speed = 2;
    protected String name;
    protected boolean pause = false;
    protected final Queue<Direction> directionQueue;

    public Direction getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public Person(int id, Point place, Direction direction) throws GameException {
        super(id, place);
        name = new UnitService().getNameFromId(id);
        this.picture = new PictureService().loadUnit(Path.of("assets/pictures/units/" + name.toLowerCase()));
        this.realPlace = new Point(place.getX() * 32, place.getY() * 32);
        this.direction = direction;
        directionQueue = new LinkedList<>();
    }

    public Person(int id, Point place, Direction direction, String name) {
        super(id, place);
        this.name = name;
        this.picture = new PictureService().loadUnit(Path.of("assets/pictures/units/" + name.toLowerCase()));
        this.realPlace = new Point(place.getX() * 32, place.getY() * 32);
        this.direction = direction;
        directionQueue = new LinkedList<>();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(picture.get(direction), realPlace.getX(), realPlace.getY(), 32, 32, null);
    }

    public void update(DungeonService dungeonService) {
        if (isWent()) {
            went(dungeonService);
        } else {
            if (!directionQueue.isEmpty()) {
                startWent(directionQueue.poll(), dungeonService);
            }
        }
    }

    public void went(DungeonService dungeonService) {
        switch (direction) {
            case UP: realPlace.incY(-speed); break;
            case DOWN: realPlace.incY(speed); break;
            case LEFT: realPlace.incX(-speed); break;
            case RIGHT: realPlace.incX(speed); break;
        }
        if (!isWent()) {
            dungeonService.getEventService().onTitleStep(dungeonService);
        }
    }

    public void startWent(Direction direction, DungeonService dungeonService) {
        if (isWent() || pause) return;
        this.direction = direction;
        Point future = new Point(place.getX(), place.getY());
        future = future.makePoint(direction);
        if (!dungeonService.getCurrentFloor().isCannotWalk(future)) {
            place = place.makePoint(direction);
            went(dungeonService);
        }
    }

    public boolean isWent() {
        return realPlace.getX() != place.getX() * 32 || realPlace.getY() != place.getY() * 32;
    }

    public void onPressEnter(DungeonService dungeonService) throws GameException {
        dungeonService.setDialog(new Dialog(new String[]{"Привет", "Это микро версия игры", "Я кассир (это так то неважно, но пусть будет, надо же как то диалоги писать)"}, new Person[]{this, this, this}, dungeonService));
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setPoint(Point place) {
        this.place = place;
        this.realPlace = new Point(this.place.getX() * 32, this.place.getY() * 32);
    }

    public void teleport(Point place, Direction direction) {
        setPoint(place);
        this.direction = direction;
    }
}

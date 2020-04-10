package ru.vladrus13.core.person;

import ru.vladrus13.core.main.dungeon.Placeable;
import ru.vladrus13.core.utils.Drawing;
import ru.vladrus13.core.utils.GameService;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.PictureService;
import ru.vladrus13.core.utils.unit.UnitService;
import ru.vladrus13.core.utils.ways.Direction;
import ru.vladrus13.core.utils.ways.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Map;

public class Person extends Placeable implements Drawing {
    protected Point realPlace;
    protected Map<Direction, BufferedImage> picture;
    protected Direction direction;
    protected final int speed = 2;
    protected String name;

    public Person(int id, Point place, Direction direction) throws GameException {
        super(id, place);
        name = new UnitService().getNameFromId(id);
        this.picture = new PictureService().loadUnit(Path.of("assets/pictures/unit/" + name.toLowerCase()));
        this.realPlace = new Point(place.getX() * 32, place.getY() * 32);
        this.direction = direction;

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(picture.get(direction), realPlace.getX(), realPlace.getY(), 32, 32, null);
    }

    public void update() {
        if (isWent()) {
            went();
        }
    }

    public void went() {
        switch (direction) {
            case UP: realPlace.incY(-speed); break;
            case DOWN: realPlace.incY(speed); break;
            case LEFT: realPlace.incX(-speed); break;
            case RIGHT: realPlace.incX(speed); break;
        }
    }

    public void startWent(Direction direction, GameService gameService) {
        if (isWent()) return;
        this.direction = direction;
        Point future = new Point(place.getX(), place.getY());
        updatePoint(direction, future);
        if (!gameService.getCurrentFloor().isCannotWalk(future)) {
            updatePoint(direction, place);
            went();
        }
    }

    private void updatePoint(Direction direction, Point place) {
        switch (direction) {
            case UP: place.incY(-1); break;
            case DOWN: place.incY(1); break;
            case LEFT: place.incX(-1); break;
            case RIGHT: place.incX(1); break;
        }
    }

    public boolean isWent() {
        return realPlace.getX() != place.getX() * 32 || realPlace.getY() != place.getY() * 32;
    }
}

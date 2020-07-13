package ru.vladrus13.core.person;

import ru.vladrus13.core.main.dialog.Dialog;
import ru.vladrus13.core.main.dungeon.Placeable;
import ru.vladrus13.core.utils.Drawing;
import ru.vladrus13.core.utils.DungeonService;
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

    public void startWent(Direction direction, DungeonService dungeonService) {
        if (isWent()) return;
        this.direction = direction;
        Point future = new Point(place.getX(), place.getY());
        future = future.makePoint(direction);
        if (!dungeonService.getCurrentFloor().isCannotWalk(future)) {
            place = place.makePoint(direction);
            went();
        }
    }

    public boolean isWent() {
        return realPlace.getX() != place.getX() * 32 || realPlace.getY() != place.getY() * 32;
    }

    public void onPressEnter(DungeonService dungeonService) throws GameException {
        dungeonService.setDialog(new Dialog(new String[]{"Привет", "Это микро версия игры", "Я кассир (это так то неважно, но пусть будет, надо же как то диалоги писать)"}, new Person[]{this, this, this}, dungeonService));
    }


}

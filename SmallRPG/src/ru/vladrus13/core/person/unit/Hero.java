package ru.vladrus13.core.person.unit;

import ru.vladrus13.core.person.Person;
import ru.vladrus13.core.person.Skills;
import ru.vladrus13.core.person.Stats;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.picture.PictureService;
import ru.vladrus13.core.utils.ways.Direction;
import ru.vladrus13.core.utils.ways.Point;

import java.nio.file.Path;

public class Hero extends Person {

    private int floor;
    protected final Stats stats;
    protected Skills skills;

    public Hero(int id, Point place, Direction direction) throws GameException {
        super(id, place, direction);
        realPlace = new Point(place.getX() * 32, place.getY() * 32);
        this.picture = new PictureService().loadUnit(Path.of("assets/pictures/unit/hero"));
        this.stats = new Stats();
        this.skills = new Skills();
    }
}

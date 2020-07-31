package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.person.Enemy;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.person.Stats;
import ru.vladrus13.RPG.core.person.enemies.Dummy;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

public class PersonService {
    private final Person[] persons;

    public PersonService() {
        persons = new Person[10];
        persons[2] = new Person(2, new Point(0, 0), Direction.UP, "Cashier");
        persons[3] = new Dummy(new Point(0, 0), Direction.RIGHT);
        persons[4] = new Enemy(4, new Point(0, 0), Direction.UP, "Goblin", new Stats(50, 100, 0, 5, 0), 2, 100);
    }

    public Person get(int id, Point teleport, Direction direction) {
        if (id < 0 || id >= persons.length) {
            throw new IllegalArgumentException("Id can't be < 0 or > length persons");
        }
        if (persons[id] == null) {
            throw new NullPointerException("Person with id " + id + " is null");
        }
        Person cloned = persons[id].clone();
        cloned.teleport(teleport, direction);
        return cloned;
    }
}

package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.main.dungeon.Floor;
import ru.vladrus13.RPG.core.main.dungeon.Title;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.main.dungeon.event.TypeActiveEvent;
import ru.vladrus13.RPG.core.main.dungeon.item.DungeonItem;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static ru.vladrus13.RPG.core.utils.ways.Direction.DOWN;
import static ru.vladrus13.RPG.core.utils.ways.Direction.UP;

public class FloorFactory {

    private final Floor[] floors;

    public FloorFactory() {
        floors = new Floor[2];
        try {
            floors[0] = floor1();
            floors[1] = floor2();
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<ArrayList<Title>> getTitles(int[][] nonParsedTitles) {
        int width = nonParsedTitles.length, height = 0;
        for (int[] nonParsedTitle : nonParsedTitles) {
            height = Math.max(height, nonParsedTitle.length);
        }
        ArrayList<ArrayList<Title>> titles = new ArrayList<>();
        Title[][] baseTitles = new Title[height][width];
        for (int i = 0; i < nonParsedTitles.length; i++) {
            for (int j = 0; j < nonParsedTitles[i].length; j++) {
                baseTitles[j][i] = new Title(nonParsedTitles[i][j], new Point(j, i));
            }
        }
        for (Title[] baseTitle : baseTitles) {
            ArrayList<Title> temp = new ArrayList<>(Arrays.asList(baseTitle));
            titles.add(temp);
        }
        return titles;
    }

    private Floor floor1() {
        String name = "First floor";
        ArrayList<ArrayList<Title>> titles;
        int[][] nonParsedTitles = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 2, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        titles = getTitles(nonParsedTitles);
        ArrayList<Person> actors = new ArrayList<>();
        ArrayList<DungeonItem> dungeonItems = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>(Collections.singletonList(new Event(0, new Point(3, 1), TypeActiveEvent.ON_STEP, dungeonService -> dungeonService.getEventService().teleport(1, new Point(1, 1), DOWN, dungeonService))));
        return new Floor(name, titles, actors, dungeonItems, events);
    }

    private Floor floor2() throws GameException {
        String name = "Pirate floor";
        ArrayList<ArrayList<Title>> titles;
        int[][] nonParsedTitles = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 3, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        titles = getTitles(nonParsedTitles);
        ArrayList<Person> actors = new ArrayList<>(Collections.singletonList(new Person(-2, new Point(5, 6), UP)));
        ArrayList<DungeonItem> dungeonItems = new ArrayList<>(Collections.singletonList(new DungeonItem(1, new Point(1, 9))));
        ArrayList<Event> events = new ArrayList<>(Collections.singletonList(new Event(0, new Point(1, 1), TypeActiveEvent.ON_STEP, dungeonService -> dungeonService.getEventService().teleport(0, new Point(3, 1), DOWN, dungeonService))));
        return new Floor(name, titles, actors, dungeonItems, events);
    }

    public Floor getCurrentFloor(int floor) throws GameException {
        if (floor < 0 || floors.length <= floor) {
            throw new GameException("Can't take " + floor + " floor from factory");
        }
        return floors[floor];
    }
}

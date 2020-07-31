package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.main.dungeon.floor.Floor;
import ru.vladrus13.RPG.core.main.dungeon.Tile;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.main.dungeon.event.TypeActiveEvent;
import ru.vladrus13.RPG.core.main.dungeon.floor.StepByStepArena;
import ru.vladrus13.RPG.core.main.dungeon.item.DungeonItem;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

import static ru.vladrus13.RPG.core.utils.ways.Direction.*;

public class FloorFactory {

    private final Floor[] floors;
    private final DungeonService dungeonService;

    public FloorFactory(DungeonService dungeonService) {
        this.dungeonService = dungeonService;
        floors = new Floor[3];
        floors[0] = floor1();
        floors[1] = floor2();
        floors[2] = floor3();
    }

    private ArrayList<ArrayList<Tile>> getTiles(int[][] nonParsedTiles) {
        int width = nonParsedTiles.length, height = 0;
        for (int[] nonParsedTile : nonParsedTiles) {
            height = Math.max(height, nonParsedTile.length);
        }
        ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
        Tile[][] baseTiles = new Tile[height][width];
        for (int i = 0; i < nonParsedTiles.length; i++) {
            for (int j = 0; j < nonParsedTiles[i].length; j++) {
                baseTiles[j][i] = new Tile(nonParsedTiles[i][j], new Point(j, i));
            }
        }
        for (Tile[] baseTile : baseTiles) {
            ArrayList<Tile> temp = new ArrayList<>(Arrays.asList(baseTile));
            tiles.add(temp);
        }
        return tiles;
    }

    private Floor floor1() {
        String name = "First floor";
        ArrayList<ArrayList<Tile>> tiles;
        int[][] nonParsedTiles = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 2, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        tiles = getTiles(nonParsedTiles);
        ArrayList<Person> actors = new ArrayList<>();
        ArrayList<DungeonItem> dungeonItems = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>(Collections.singletonList(new Event(0, new Point(3, 1), TypeActiveEvent.ON_STEP, dungeonService -> dungeonService.getEventService().teleport(1, new Point(1, 1), RIGHT, dungeonService))));
        return new Floor(name, tiles, actors, dungeonItems, events);
    }

    private Floor floor2() {
        String name = "Pirate floor";
        ArrayList<ArrayList<Tile>> tiles;
        int[][] nonParsedTiles = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 3, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 2, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        tiles = getTiles(nonParsedTiles);
        ArrayList<Person> actors = new ArrayList<>(Collections.singletonList(dungeonService.getPersonService().get(2, new Point(5, 6), UP)));
        ArrayList<DungeonItem> dungeonItems = new ArrayList<>(Collections.singletonList(new DungeonItem(1, new Point(1, 9))));
        ArrayList<Event> events = new ArrayList<>(Arrays.asList(
                new Event(0, new Point(1, 1), TypeActiveEvent.ON_STEP, dungeonService -> dungeonService.getEventService().teleport(0, new Point(3, 1), LEFT, dungeonService)),
                new Event(1, new Point(1, 5), TypeActiveEvent.ON_STEP, dungeonService -> dungeonService.getEventService().teleport(2, new Point(1, 1), DOWN, dungeonService))
        ));
        return new Floor(name, tiles, actors, dungeonItems, events);
    }

    private StepByStepArena floor3() {
        String name = "Training";
        int[][] nonParsedTitles = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 3, 0, 0, 4, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        ArrayList<ArrayList<Tile>> tiles = getTiles(nonParsedTitles);
        ArrayList<Person> actors = new ArrayList<>();
        ArrayList<DungeonItem> dungeonItems = new ArrayList<>();
        // никаких внешних связей не делать - иначе бан
        ArrayList<Event> events = new ArrayList<>(Arrays.asList(
                new Event(0, new Point(1, 1), TypeActiveEvent.ON_STEP, dungeonService -> dungeonService.getEventService().teleport(1, new Point(1, 5), RIGHT, dungeonService)),
                new Event(1, new Point(4, 1), TypeActiveEvent.ON_STEP, dungeonService -> {
                    Consumer<Point> make = point -> {
                        if (!dungeonService.getCurrentFloor().isPerson(point)) {
                            dungeonService.getCurrentFloor().getActors().add(dungeonService.getPersonService().get(3, point, UP));
                        }
                    };
                    make.accept(new Point(1, 5));
                    make.accept(new Point(3, 5));
                    make.accept(new Point(5, 5));
                    make.accept(new Point(7, 5));
                    if (!dungeonService.getCurrentFloor().isPerson(new Point(5, 7))) {
                        dungeonService.getCurrentFloor().getActors().add(dungeonService.getPersonService().get(4, new Point(5, 5), UP));
                    }
                })
        ));
        return new StepByStepArena(name, tiles, actors, dungeonItems, events, dungeonService);
    }

    public Floor getCurrentFloor(int floor) throws GameException {
        if (floor < 0 || floors.length <= floor) {
            throw new GameException("Can't take " + floor + " floor from factory");
        }
        return floors[floor];
    }
}

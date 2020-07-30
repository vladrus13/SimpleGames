package ru.vladrus13.RPG.core.utils.event;

import ru.vladrus13.RPG.core.main.dungeon.floor.Floor;
import ru.vladrus13.RPG.core.main.dungeon.event.TypeActiveEvent;
import ru.vladrus13.RPG.core.main.dungeon.floor.StepByStepArena;
import ru.vladrus13.RPG.core.person.unit.Hero;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

public class EventService {

    public void teleport(int floor, Point point, Direction direction, DungeonService dungeonService) {
        dungeonService.setCurrentFloor(floor);
        dungeonService.getHero().teleport(point, direction);
    }

    public void onTileStep(DungeonService dungeonService) {
        Hero hero = dungeonService.getHero();
        Floor floor = dungeonService.getCurrentFloor();
        Point to = hero.getPlace();
        if (floor.isEvent(to, element -> element.getTypeActiveEvent() == TypeActiveEvent.ON_STEP)) {
            floor.getEvent(to, element -> element.getTypeActiveEvent() == TypeActiveEvent.ON_STEP).run(dungeonService);
        }
        if (floor instanceof StepByStepArena) {
            ((StepByStepArena) floor).enemyTurn();
        }
    }


}

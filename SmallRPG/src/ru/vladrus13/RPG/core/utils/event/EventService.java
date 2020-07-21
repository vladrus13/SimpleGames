package ru.vladrus13.RPG.core.utils.event;

import ru.vladrus13.RPG.core.main.dialog.Dialog;
import ru.vladrus13.RPG.core.main.dungeon.Floor;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.person.unit.Hero;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Direction;
import ru.vladrus13.RPG.core.utils.ways.Point;

public class EventService {

    public void onPressEnter(DungeonService dungeonService) throws GameException {
        Hero hero = dungeonService.getHero();
        Floor floor = dungeonService.getCurrentFloor();
        Point to = hero.getPlace();
        to = to.makePoint(hero.getDirection());
        if (floor.isPerson(to)) {
            floor.getPerson(to).onPressEnter(dungeonService);
        } else {
            if (floor.isEvent(to)) {
                floor.getEvent(to).run(dungeonService);
            } else {
                if (floor.isDungeonItem(to)) {
                    dungeonService.getHero().getInventory().addItem(dungeonService.getItemFactory().get(floor.getDungeonItem(to).getId()));
                    floor.eraseItem(to, floor.getDungeonItem(to));
                }
            }
        }
    }

    public void onStart(DungeonService dungeonService) throws GameException {
        dungeonService.setDialog(new Dialog(new String[]{"Где я?"}, new Person[]{dungeonService.getHero()}, dungeonService));
    }

    public void move(Direction direction, DungeonService dungeonService) {
        if (dungeonService.getDialog() == null) {
            dungeonService.getHero().startWent(direction, dungeonService);
        }
    }

    public void teleport(int floor, Point point, Direction direction, DungeonService dungeonService) {
        dungeonService.setCurrentFloor(floor);
        dungeonService.getHero().teleport(point, direction);
    }
}

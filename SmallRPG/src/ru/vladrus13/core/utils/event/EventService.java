package ru.vladrus13.core.utils.event;

import ru.vladrus13.core.main.dialog.Dialog;
import ru.vladrus13.core.main.dungeon.Floor;
import ru.vladrus13.core.person.Person;
import ru.vladrus13.core.person.unit.Hero;
import ru.vladrus13.core.utils.DungeonService;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.ways.Direction;
import ru.vladrus13.core.utils.ways.Point;

public class EventService {

    public void onPressEnter(DungeonService dungeonService) throws GameException {
        Hero hero = dungeonService.getHero();
        Floor floor = dungeonService.getCurrentFloor();
        Point to = hero.getPlace();
        to = to.makePoint(hero.getDirection());
        if (floor.isPerson(to)) {
            floor.getPerson(to).onPressEnter(dungeonService);
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
}

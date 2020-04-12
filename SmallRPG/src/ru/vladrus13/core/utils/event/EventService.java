package ru.vladrus13.core.utils.event;

import ru.vladrus13.core.main.dungeon.Floor;
import ru.vladrus13.core.person.Person;
import ru.vladrus13.core.person.unit.Hero;
import ru.vladrus13.core.utils.GameService;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.ways.Point;

public class EventService {

    public void onEnter(GameService gameService) throws GameException {
        Hero hero = gameService.getHero();
        Floor floor = gameService.getCurrentFloor();
        Point to = hero.getPlace();
        to = to.makePoint(hero.getDirection());
        if (floor.isPerson(to)) {
            floor.getPerson(to).onEnter(gameService);
        }
    }
}

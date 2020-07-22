package ru.vladrus13.RPG.core.utils;

import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.game.EventFactory;
import ru.vladrus13.RPG.game.FloorFactory;
import ru.vladrus13.RPG.game.HeroService;
import ru.vladrus13.RPG.game.ItemFactory;
import ru.vladrus13.RPG.core.main.dialog.Dialog;
import ru.vladrus13.RPG.core.main.dungeon.Floor;
import ru.vladrus13.RPG.core.person.unit.Hero;
import ru.vladrus13.RPG.core.utils.event.EventService;

import ru.vladrus13.RPG.core.utils.picture.FontService;

public class DungeonService {
    private int currentFloor;
    private Dialog dialog;
    private final FontService fontService;
    private final EventFactory eventFactory;
    private final EventService eventService;
    private final ItemFactory itemFactory;
    private final FloorFactory floorFactory;
    private final HeroService heroService;

    public DungeonService() {
        System.out.println("Loading items factory...");
        itemFactory = new ItemFactory();
        System.out.println("Loading events factory...");
        eventFactory = new EventFactory();
        System.out.println("Loading floors factory...");
        floorFactory = new FloorFactory();
        System.out.println("Loading fonts service...");
        fontService = new FontService();
        System.out.println("Loading events service...");
        eventService = new EventService();
        System.out.println("Loading hero service...");
        heroService = new HeroService();
    }

    public EventService getEventService() {
        return eventService;
    }

    public FontService getFontService() {
        return fontService;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public Floor getCurrentFloor() {
        try {
            return floorFactory.getCurrentFloor(currentFloor);
        } catch (GameException e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Hero getHero() {
        return heroService.getHero();
    }

    public void setHero(Hero hero) {
        heroService.setHero(hero);
    }

    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    public EventFactory getEventFactory() {
        return eventFactory;
    }
}

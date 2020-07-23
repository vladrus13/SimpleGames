package ru.vladrus13.RPG.core.utils;

import ru.vladrus13.RPG.core.Dungeon;
import ru.vladrus13.RPG.core.ShortMenu;
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
    private final Dungeon dungeon;
    private final ShortMenu shortMenu;
    private int currentFloor;
    private Dialog dialog;
    private final FontService fontService;
    private final EventFactory eventFactory;
    private final EventService eventService;
    private final ItemFactory itemFactory;
    private final FloorFactory floorFactory;
    private final HeroService heroService;

    public DungeonService(Dungeon dungeon) throws GameException {
        this.dungeon = dungeon;
        shortMenu = new ShortMenu(dungeon);
        shortMenu.setDrawing(false);
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
        heroService = new HeroService(this);
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
        dungeon.addFocus(dialog);
        dungeon.addDrawing(dialog);
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
        try {
            dungeon.replaceDrawing(
                    floorFactory.getCurrentFloor(this.currentFloor),
                    floorFactory.getCurrentFloor(currentFloor));
            this.currentFloor = currentFloor;
        } catch (GameException e) {
            e.printStackTrace();
        }
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

    public Dungeon getDungeon() {
        return dungeon;
    }

    public ShortMenu getShortMenu() {
        return shortMenu;
    }
}

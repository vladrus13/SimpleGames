package ru.vladrus13.RPG.core.utils;

import ru.vladrus13.RPG.core.Dungeon;
import ru.vladrus13.RPG.core.ShortMenu;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.PictureService;
import ru.vladrus13.RPG.game.*;
import ru.vladrus13.RPG.core.main.dialog.Dialog;
import ru.vladrus13.RPG.core.main.dungeon.floor.Floor;
import ru.vladrus13.RPG.core.person.unit.Hero;
import ru.vladrus13.RPG.core.utils.event.EventService;

import ru.vladrus13.RPG.core.utils.picture.FontService;

import java.util.logging.Logger;

public class DungeonService {

    // TODO Logger
    private final static Logger logger = Logger.getLogger(DungeonService.class.getName());
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
    private final ShopFactory shopFactory;
    private final PersonService personService;
    private final PictureService pictureService;
    private final SoundFactory soundFactory;

    public DungeonService(Dungeon dungeon) throws GameException {
        this.dungeon = dungeon;
        shortMenu = new ShortMenu(dungeon);
        shortMenu.setDrawing(false);

        logger.info("Loading picture service");
        pictureService = new PictureService();

        logger.info("Loading fonts service...");
        fontService = new FontService();

        logger.info("Loading items factory...");
        itemFactory = new ItemFactory();

        logger.info("Loading person service...");
        personService = new PersonService();

        logger.info("Loading hero service...");
        heroService = new HeroService(this);

        logger.info("Loading events factory...");
        eventFactory = new EventFactory();

        logger.info("Loading floors factory...");
        floorFactory = new FloorFactory(this);

        logger.info("Loading events service...");
        eventService = new EventService();

        logger.info("Loading sound factory...");
        soundFactory = new SoundFactory();

        logger.info("Loading shop service...");
        shopFactory = new ShopFactory(this);
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

    public ShopFactory getShopFactory() {
        return shopFactory;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public PictureService getPictureService() {
        return pictureService;
    }

    public SoundFactory getSoundFactory() {
        return soundFactory;
    }
}

package ru.vladrus13.RPG.core.utils;

import ru.vladrus13.RPG.core.Dungeon;
import ru.vladrus13.RPG.core.ShortMenu;
import ru.vladrus13.RPG.core.main.dialog.Dialog;
import ru.vladrus13.RPG.core.main.dungeon.floor.Floor;
import ru.vladrus13.RPG.core.person.unit.Hero;
import ru.vladrus13.RPG.core.utils.event.EventService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.FontService;
import ru.vladrus13.RPG.core.utils.picture.PictureService;
import ru.vladrus13.RPG.game.*;

import java.util.logging.Logger;

/**
 * @author vladkuznetsov
 * Big class for ALL data in {@link Dungeon}
 */
public class DungeonService {

    /**
     * Logger for this class
     */
    private final static Logger logger = Logger.getLogger(DungeonService.class.getName());
    /**
     * {@link Dungeon} - dungeon
     */
    private final Dungeon dungeon;
    /**
     * {@link ShortMenu} - short menu
     */
    private final ShortMenu shortMenu;
    /**
     * Number of current floor
     */
    private int currentFloor;
    /**
     * {@link Dialog} - current dialog
     */
    private Dialog dialog;
    /**
     * {@link FontService} - font service
     */
    private final FontService fontService;
    /**
     * {@link EventFactory} - event factory
     */
    private final EventFactory eventFactory;
    /**
     * {@link EventService} - event service
     */
    private final EventService eventService;
    /**
     * {@link ItemFactory} - item factory
     */
    private final ItemFactory itemFactory;
    /**
     * {@link FloorFactory} - floor factory
     */
    private final FloorFactory floorFactory;
    /**
     * {@link HeroService} - hero service
     */
    private final HeroService heroService;
    /**
     * {@link ShopFactory} - shop factory
     */
    private final ShopFactory shopFactory;
    /**
     * {@link PersonService} - person service
     */
    private final PersonService personService;
    /**
     * {@link PictureService} - picture service
     */
    private final PictureService pictureService;
    /**
     * {@link SoundFactory} - sound factory
     */
    private final SoundFactory soundFactory;

    /**
     * Constructor for class
     *
     * @param dungeon {@link Dungeon} for which dungeon we get service
     * @throws GameException if short menu created is failed
     */
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

    /**
     * Setter for dialog
     *
     * @param dialog {@link Dialog}
     */
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
        dungeon.addFocus(dialog);
        dungeon.addDrawing(dialog);
    }

    /**
     * Get current floor
     *
     * @return current floor - {@link Floor}
     */
    public Floor getCurrentFloor() {
        try {
            return floorFactory.getFloor(currentFloor);
        } catch (GameException e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    /**
     * Set current floor
     *
     * @param currentFloor number of floor
     */
    public void setCurrentFloor(int currentFloor) {
        try {
            dungeon.replaceDrawing(
                    floorFactory.getFloor(this.currentFloor),
                    floorFactory.getFloor(currentFloor));
            this.currentFloor = currentFloor;
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for event service
     *
     * @return event service
     */
    public EventService getEventService() {
        return eventService;
    }

    /**
     * Getter for font service
     *
     * @return font service
     */
    public FontService getFontService() {
        return fontService;
    }

    /**
     * Getter for dialog
     *
     * @return dialog
     */
    public Dialog getDialog() {
        return dialog;
    }

    /**
     * Getter for hero
     *
     * @return hero
     */
    public Hero getHero() {
        return heroService.getHero();
    }

    /**
     * Getter for item factory
     *
     * @return item factory
     */
    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    /**
     * Getter for event factory
     *
     * @return event factory
     */
    public EventFactory getEventFactory() {
        return eventFactory;
    }

    /**
     * Getter for dungeon
     *
     * @return dungeon
     */
    public Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * Getter for short menu
     *
     * @return short menu
     */
    public ShortMenu getShortMenu() {
        return shortMenu;
    }

    /**
     * Getter for shop factory
     *
     * @return shop factory
     */
    public ShopFactory getShopFactory() {
        return shopFactory;
    }

    /**
     * Getter for person service
     *
     * @return person service
     */
    public PersonService getPersonService() {
        return personService;
    }

    /**
     * Getter for picture service
     *
     * @return picture service
     */
    public PictureService getPictureService() {
        return pictureService;
    }

    /**
     * Getter for sound factory
     *
     * @return sound factory
     */
    public SoundFactory getSoundFactory() {
        return soundFactory;
    }
}

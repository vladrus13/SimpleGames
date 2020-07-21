package ru.vladrus13.RPG.core.utils;

import ru.vladrus13.RPG.core.inventory.ItemFactory;
import ru.vladrus13.RPG.core.main.dialog.Dialog;
import ru.vladrus13.RPG.core.main.dungeon.Floor;
import ru.vladrus13.RPG.core.person.unit.Hero;
import ru.vladrus13.RPG.core.utils.event.EventService;

import ru.vladrus13.RPG.core.utils.picture.FontService;

public class DungeonService {
    Floor currentFloor;
    Hero hero;
    Dialog dialog;
    FontService fontService;
    EventService eventService;
    ItemFactory itemFactory;

    public DungeonService() {
        itemFactory = new ItemFactory();
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public FontService getFontService() {
        return fontService;
    }

    public void setFontService(FontService fontService) {
        this.fontService = fontService;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    public void setItemFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }
}

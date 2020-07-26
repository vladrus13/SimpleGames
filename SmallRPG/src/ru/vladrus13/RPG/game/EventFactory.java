package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.main.dialog.Choose;
import ru.vladrus13.RPG.core.main.dialog.Dialog;
import ru.vladrus13.RPG.core.main.dungeon.event.Event;
import ru.vladrus13.RPG.core.main.frame.shop.Shop;
import ru.vladrus13.RPG.core.person.Person;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

import static ru.vladrus13.RPG.core.main.dungeon.event.TypeActiveEvent.TRIGGERED;

public class EventFactory {

    private final HashMap<String, Event> events;

    public EventFactory() {
        events = new HashMap<>();
        events.put("onStart", new Event(0, new Point(0, 0), TRIGGERED, dungeonService -> {
            try {
                dungeonService.setDialog(new Dialog(new String[]{"Где я?"}, new Person[]{dungeonService.getHero()}, dungeonService));
            } catch (GameException e) {
                e.printStackTrace();
            }
        }));

        events.put("PirateDialog", new Event(1, new Point(0, 0), TRIGGERED, dungeonService -> {
            Consumer<DungeonService> onHistoryClick = dungeonService1 -> {
                try {
                    dungeonService.setDialog(new Dialog(new String[]{
                            "Привет",
                            "Это микро версия игры",
                            "Я кассир (это так то неважно, но пусть будет, надо же как то диалоги писать)"},
                            new Person[]{dungeonService.getHero(), dungeonService.getHero(), dungeonService.getHero()}, dungeonService));
                } catch (GameException e) {
                    e.printStackTrace();
                }
            };
            Consumer<DungeonService> onExitClick = dungeonService1 -> {
                try {
                    dungeonService.setDialog(new Dialog(new String[]{"Прощай"},
                            new Person[]{dungeonService.getHero()}, dungeonService));
                } catch (GameException e) {
                    e.printStackTrace();
                }
            };

            Consumer<DungeonService> pirateShop = dungeonService1 -> {
                Shop shop;
                try {
                    shop = dungeonService1.getShopFactory().get("PirateShop");
                } catch (GameException e) {
                    e.printStackTrace();
                    return;
                }
                dungeonService1.getDungeon().addFocus(shop);
                dungeonService1.getDungeon().addDrawing(shop);
            };

            Choose start;
            try {
                start = new Choose(new ArrayList<>(Arrays.asList("Грустная история", "Пока", "Shop")),
                        new ArrayList<>(Arrays.asList(onHistoryClick, onExitClick, pirateShop)), dungeonService);
            } catch (GameException e) {
                e.printStackTrace();
                return;
            }
            dungeonService.getDungeon().addDrawing(start);
            dungeonService.getDungeon().addFocus(start);
        }));
    }

    public Event get(String key) {
        return events.get(key);
    }
}

package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.inventory.Item;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.util.function.Consumer;

/**
 * @author vladkuznetsov
 * Item factory
 */
public class ItemFactory {

    /**
     * Item count
     */
    private final int itemCount = 500;

    /*
    ID for class items
    1 - 100 - weapons
    101 - 200 - armors
    201 - 250 - poisons
    251 - 350 - items with special effects
     */

    /**
     * Items
     */
    private final Item[] items = new Item[itemCount];

    /**
     * Get {@link ru.vladrus13.RPG.core.inventory.Inventory.Items} with count one
     *
     * @param id id
     * @return item
     * @throws GameException if we get item with wrong id or null item
     */
    public Inventory.Items getWithCount(int id) throws GameException {
        if (id >= itemCount) {
            throw new GameException("Get wrong id more than item's count");
        } else {
            if (items[id] == null) {
                throw new GameException("Item with id: " + id + " is null");
            } else {
                return new Inventory.Items(1, items[id]);
            }
        }
    }

    /**
     * Get {@link Item}
     *
     * @param id id
     * @return item
     * @throws GameException if we get item with wrong id or null item
     */
    public Item get(int id) throws GameException {
        if (id >= itemCount) {
            throw new GameException("Get wrong id more than item's count");
        } else {
            if (items[id] == null) {
                throw new GameException("Item with id: " + id + " is null");
            } else {
                return items[id];
            }
        }
    }

    /**
     * Return empty consumer
     *
     * @return empty consumer
     */
    private Consumer<DungeonService> returnEmptyConsumer() {
        return dungeonService -> {
        };
    }

    /**
     * Constructor for class
     */
    public ItemFactory() {
        items[1] = new Item(1, "Маленький оловянный кинжал без особенностей", "Оловянный кинжал",
                returnEmptyConsumer(), returnEmptyConsumer(), returnEmptyConsumer(), Item.ItemType.WEAPON);
        items[2] = new Item(2, "Самый сильный меч в игре", "Бан",
                returnEmptyConsumer(), returnEmptyConsumer(), returnEmptyConsumer(), Item.ItemType.WEAPON);
        items[101] = new Item(101, "Убийственно слабая броня", "Слабая броня",
                returnEmptyConsumer(), returnEmptyConsumer(), returnEmptyConsumer(), Item.ItemType.ARMOR);
    }
}

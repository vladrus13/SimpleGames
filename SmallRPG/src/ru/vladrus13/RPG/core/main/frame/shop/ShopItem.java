package ru.vladrus13.RPG.core.main.frame.shop;

import ru.vladrus13.RPG.core.inventory.Item;
import ru.vladrus13.RPG.core.utils.exception.GameException;

/**
 * Item in shop for buying
 */
public class ShopItem {
    /**
     * Item
     */
    private final Item item;
    /**
     * Count of item
     */
    private int count;
    /**
     * Cost of item
     */
    private final int cost;

    /**
     * Constructor of class
     *
     * @param item  {@link Item} - item
     * @param count count of item
     * @param cost  cost of item
     */
    public ShopItem(Item item, int count, int cost) {
        this.item = item;
        this.count = count;
        this.cost = cost;
    }

    /**
     * Getter for item
     *
     * @return item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Getter for count
     *
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter for cost
     *
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Decrease count of item
     *
     * @throws GameException if count was 0
     */
    public void decreaseCount() throws GameException {
        if (count == 0) throw new GameException("Can't take item with count 0");
        count--;
    }
}

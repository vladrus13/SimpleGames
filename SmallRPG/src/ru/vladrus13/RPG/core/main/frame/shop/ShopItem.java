package ru.vladrus13.RPG.core.main.frame.shop;

import ru.vladrus13.RPG.core.inventory.Item;
import ru.vladrus13.RPG.core.utils.exception.GameException;

public class ShopItem {
    private final Item item;
    private int count;
    private final int cost;

    public ShopItem(Item item, int count, int cost) {
        this.item = item;
        this.count = count;
        this.cost = cost;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public int getCost() {
        return cost;
    }

    public void decreaseCount() throws GameException {
        if (count == 0) throw new GameException("Can't take item with count 0");
        count--;
    }
}

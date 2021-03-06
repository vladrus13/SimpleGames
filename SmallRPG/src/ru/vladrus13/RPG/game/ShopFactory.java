package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.main.frame.shop.Shop;
import ru.vladrus13.RPG.core.main.frame.shop.ShopItem;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vladkuznetsov
 * Shop factory
 */
public class ShopFactory {
    /**
     * Shops
     */
    final Map<String, Shop> shops;

    /**
     * Constructor for class
     *
     * @param dungeonService {@link DungeonService}
     */
    public ShopFactory(DungeonService dungeonService) {
        shops = new HashMap<>();
        try {
            shops.put("PirateShop",
                    new Shop(new ArrayList<>(Arrays.asList(
                            new ShopItem(dungeonService.getItemFactory().get(1), 1, 0),
                            new ShopItem(dungeonService.getItemFactory().get(1), 1, 0),
                            new ShopItem(dungeonService.getItemFactory().get(1), 1, 0),
                            new ShopItem(dungeonService.getItemFactory().get(1), 1, 5)
                    )), dungeonService));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a shop by name
     *
     * @param name name of shop
     * @return return {@link Shop}
     * @throws GameException if we can't find whop with this name
     */
    public Shop get(String name) throws GameException {
        if (!shops.containsKey(name)) {
            throw new GameException("Don't have shop with name: " + name);
        } else {
            return shops.get(name);
        }
    }
}

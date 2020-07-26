package ru.vladrus13.RPG.game;

import ru.vladrus13.RPG.core.main.frame.shop.Shop;
import ru.vladrus13.RPG.core.main.frame.shop.ShopItem;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ShopFactory {
    final Map<String, Shop> shops;

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

    public Shop get(String name) throws GameException {
        if (!shops.containsKey(name)) {
            throw new GameException("Don't have shop with name: " + name);
        } else {
            return shops.get(name);
        }
    }
}

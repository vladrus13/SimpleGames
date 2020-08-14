package ru.vladrus13.RPG.core.main.frame.battle.menu;

import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.inventory.Item;
import ru.vladrus13.RPG.core.main.dialog.BigText;
import ru.vladrus13.RPG.core.main.dialog.KeyTakerReturner;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Item menu on battle
 */
public class BattleItemMenu extends KeyTakerReturner {

    /**
     * Items to show
     */
    private ArrayList<Inventory.Items> items;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * Height items window
     */
    private final int heightItems = 750;
    /**
     * Height description
     */
    private final int heightDescription = 50;
    /**
     * Width of items
     */
    private final int widthItems = 500;
    /**
     * Description of item
     */
    private BigText description;
    /**
     * Current item number
     */
    private int countItem;
    private final Font inventoryFont;
    private int showingFirstItem;
    private final BattleMenu battleMenu;

    public BattleItemMenu(DungeonService dungeonService, BattleMenu battleMenu) throws GameException {
        this.battleMenu = battleMenu;
        this.dungeonService = dungeonService;
        inventoryFont = dungeonService.getFontService().getFont("Inventory", 17);
        countItem = 0;
        showingFirstItem = 0;
        reload();
    }

    /**
     * Reload items
     */
    public void reload() {
        items = new ArrayList<>(dungeonService.getHero().getInventory().getItems().stream().
                filter(element -> element.getItem().getItemType() == Item.ItemType.POISON).
                collect(Collectors.toUnmodifiableList()));
        reloadDescription();
    }

    /**
     * Reload description of current item
     */
    private void reloadDescription() {
        if (items.size() > countItem) {
            description = new BigText(items.get(countItem).getItem().getDescription(), new ru.vladrus13.RPG.core.utils.ways.Point(widthItems + 20, heightDescription), new Point(770 - widthItems, 0), dungeonService, inventoryFont, Color.WHITE);
        }
    }

    @Override
    public Object returner() throws GameException {
        return null;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(inventoryFont);
        for (int position = showingFirstItem; position < Math.min(items.size(), heightItems / inventoryFont.getSize()); position++) {
            if (position == countItem) {
                graphics.setColor(Color.DARK_GRAY);
                graphics.fillRect(20, (position - 1) * inventoryFont.getSize() + 50, widthItems, inventoryFont.getSize());
                graphics.setColor(Color.WHITE);
            }
            graphics.drawString(items.get(position).getItem().getName() + " " + items.get(position).getCount(), 20, position * inventoryFont.getSize() + 50);
        }
        graphics.drawString(items.get(countItem).getItem().getName(), widthItems + 20, 30);
        description.draw(graphics);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN:
                if (countItem + 1 < items.size()) {
                    countItem++;
                    if (countItem - showingFirstItem > heightItems / inventoryFont.getSize() && showingFirstItem + 1 < items.size()) {
                        showingFirstItem++;
                    }
                    reloadDescription();
                }
                break;
            case KeyEvent.VK_UP:
                if (countItem > 0) {
                    countItem--;
                    if (countItem - 1 < showingFirstItem && showingFirstItem > 0) {
                        showingFirstItem--;
                    }
                    reloadDescription();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                battleMenu.returnToMenu();
                break;
        }
    }

    /**
     * Is inventory empty
     * @return is inventory empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}

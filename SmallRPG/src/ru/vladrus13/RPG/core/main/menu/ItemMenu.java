package ru.vladrus13.RPG.core.main.menu;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.inventory.Item;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Item menu
 */
@SuppressWarnings("FieldCanBeLocal")
public class ItemMenu extends Drawing implements KeyTaker {
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;

    /**
     * {@link ShortMenu}
     */
    private final ShortMenu shortMenu;
    /**
     * {@link Font} - inventory font
     */
    private final Font inventoryFont;
    /**
     * Weapons
     */
    private Set<Inventory.Items> weapons;
    /**
     * Armors
     */
    private Set<Inventory.Items> armors;
    /**
     * Poisons
     */
    private Set<Inventory.Items> poisons;
    /**
     * Special items
     */
    private Set<Inventory.Items> specials;
    /**
     * Current item type
     */
    private Item.ItemType itemType;
    /**
     * Position current item
     */
    private int countItem;
    /**
     * Function to make from item string with name and count
     */
    private final Function<Inventory.Items, String> fromItemToString = element -> element.getItem().getName() + " " + element.getCount();

    /**
     * Showing items
     */
    private ArrayList<Inventory.Items> showing;

    /**
     * Number of first showing item
     */
    private int showingFirstItem;

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
     * Constructor of class
     * @param dungeonService {@link DungeonService}
     * @param shortMenu {@link ShortMenu}
     * @throws GameException if font can't load
     */
    public ItemMenu(DungeonService dungeonService, ShortMenu shortMenu) throws GameException {
        this.dungeonService = dungeonService;
        inventoryFont = dungeonService.getFontService().getFont("Inventory", 17);
        weapons = new HashSet<>();
        armors = new HashSet<>();
        poisons = new HashSet<>();
        specials = new HashSet<>();
        itemType = Item.ItemType.WEAPON;
        countItem = 0;
        showingFirstItem = 0;
        reload();
        showing = toArray(weapons);
        this.shortMenu = shortMenu;
    }

    /**
     * Filter current hero inventory
     *
     * @param filter filter
     * @return unmodifiable set
     */
    private Set<Inventory.Items> filter(Predicate<Inventory.Items> filter) {
        return dungeonService.getHero().getInventory().getItems().stream().filter(filter).collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Reload items
     */
    public void reload() {
        weapons = filter(element -> element.getItem().getItemType() == Item.ItemType.WEAPON);
        armors = filter(element -> element.getItem().getItemType() == Item.ItemType.ARMOR);
        poisons = filter(element -> element.getItem().getItemType() == Item.ItemType.POISON);
        specials = filter(element -> element.getItem().getItemType() == Item.ItemType.SPECIAL);
        countItem = 0;
        fillShowing();
    }

    /**
     * Fill showing array
     */
    private void fillShowing() {
        switch (itemType) {
            case WEAPON:
                showing = toArray(weapons);
                break;
            case ARMOR:
                showing = toArray(armors);
                break;
            case POISON:
                showing = toArray(poisons);
                break;
            case SPECIAL:
                showing = toArray(specials);
                break;
        }
    }

    /**
     * Get a unmodifiable array from set
     * @param itemsSet set
     * @return unmodifiable array
     */
    private ArrayList<Inventory.Items> toArray(Set<Inventory.Items> itemsSet) {
        return new ArrayList<>(itemsSet.stream().collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(inventoryFont);
        graphics.drawString(itemType.toString(), 120, 30);
        if (showing.isEmpty()) {
            graphics.drawString("Пусто!!!", 120, 50);
            return;
        }
        for (int position = showingFirstItem; position < Math.min(showing.size(), heightItems / inventoryFont.getSize()); position++) {
            if (position == countItem) {
                graphics.setColor(Color.DARK_GRAY);
                graphics.fillRect(20, (position - 1) * inventoryFont.getSize() + 50, widthItems, inventoryFont.getSize());
                graphics.setColor(Color.WHITE);
            }
            graphics.drawString(fromItemToString.apply(showing.get(position)), 20, position * inventoryFont.getSize() + 50);
        }
        graphics.drawString(showing.get(countItem).getItem().getName(), widthItems + 20, 30);
        graphics.drawString(showing.get(countItem).getItem().getDescription(), widthItems + 20, heightDescription);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN:
                if (countItem + 1 < showing.size()) {
                    countItem++;
                    if (countItem - showingFirstItem > heightItems / inventoryFont.getSize() && showingFirstItem + 1 < showing.size()) {
                        showingFirstItem++;
                    }
                }
                break;
            case KeyEvent.VK_UP:
                if (countItem > 0) {
                    countItem--;
                    if (countItem - 1 < showingFirstItem && showingFirstItem > 0) {
                        showingFirstItem--;
                    }
                }
                break;
            case KeyEvent.VK_Q:
                itemType = itemType.previous();
                fillShowing();
                break;
            case KeyEvent.VK_E:
                itemType = itemType.next();
                fillShowing();
                break;
            case KeyEvent.VK_ESCAPE:
                shortMenu.returnToMenu();
                break;
        }
    }
}

package ru.vladrus13.RPG.core;

import ru.vladrus13.RPG.core.inventory.Book;
import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.inventory.Item;
import ru.vladrus13.RPG.core.utils.Drawing;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.FontService;
import ru.vladrus13.RPG.core.utils.picture.PictureService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShortMenu implements Drawing {

    public enum StatusShortMenu {
        MAIN, BOOK, INVENTORY
    }

    private final PictureService pictureService;
    private final Dungeon dungeon;
    private final Font menuFont, inventoryFont, writerFont, bigWriterFont;
    private StatusShortMenu statusShortMenu;
    private Set<Inventory.Items> weaponItem, armorItem, poisonItem, specialItem;
    private Item.ItemType itemType;
    private int countSkipItem;
    private ArrayList<Book> books;
    private int countSkipBook, pageBook;
    private final Function<Inventory.Items, String> fromItemToString = element -> element.getItem().getName() + " " + element.getCount();

    public ShortMenu(Dungeon dungeon) throws GameException {
        this.statusShortMenu = StatusShortMenu.MAIN;
        this.dungeon = dungeon;
        this.pictureService = new PictureService();
        this.menuFont = new FontService().getFont("MK-90", 30);
        this.inventoryFont = new FontService().getFont("Inventory", 17);
        this.writerFont = new FontService().getFont("WriterFont", 20);
        this.bigWriterFont = new FontService().getFont("WriterFont", 30);
        itemType = Item.ItemType.WEAPON;
        countSkipItem = 0;
        countSkipBook = 0;
        pageBook = 0;
    }

    private void reloadBooksAndItems() {
        weaponItem = dungeon.getDungeonService().getHero().getInventory().getItems().stream().filter(element -> element.getItem().getItemType() == Item.ItemType.WEAPON).collect(Collectors.toUnmodifiableSet());
        armorItem = dungeon.getDungeonService().getHero().getInventory().getItems().stream().filter(element -> element.getItem().getItemType() == Item.ItemType.ARMOR).collect(Collectors.toUnmodifiableSet());
        poisonItem = dungeon.getDungeonService().getHero().getInventory().getItems().stream().filter(element -> element.getItem().getItemType() == Item.ItemType.POISON).collect(Collectors.toUnmodifiableSet());
        specialItem = dungeon.getDungeonService().getHero().getInventory().getItems().stream().filter(element -> element.getItem().getItemType() == Item.ItemType.SPECIAL).collect(Collectors.toUnmodifiableSet());
        books = dungeon.getDungeonService().getHero().getInventory().getBooks();
        countSkipBook = 0;
        countSkipItem = 0;
        pageBook = 0;
    }

    public void drawArray(String[] array, int startHeight, int firstRow, int secondRow, int height, Graphics graphics) {
        if (array.length == 0) {
            graphics.drawString("Пусто!!!", firstRow, startHeight);
        } else {
            for (int it = 0; it < array.length; it += 2) {
                graphics.drawString(array[it], firstRow, startHeight + height * it / 2);
                if (it + 1 < array.length) {
                    graphics.drawString(array[it + 1], secondRow, startHeight + height * it / 2);
                }
            }
        }
    }

    public void drawArray(String[] array, int startHeight, int firstRow, int height, Graphics graphics) {
        if (array.length == 0) {
            graphics.drawString("Пусто!!!", firstRow, startHeight);
        } else {
            for (int it = 0; it < array.length && startHeight + height * it < 800; it++) {
                graphics.drawString(array[it], firstRow, startHeight + height * it);
            }
        }
    }

    public void drawBook(Book book, int startHeight, int firstRow, int secondRow, int height, Graphics graphics) {
        int it = 0;
        for (; it < book.getEntry().length && startHeight + height * it < 800; it++) {
            graphics.drawString(book.getEntry()[it], firstRow, startHeight + height * it);
        }
        for (; it < book.getEntry().length && startHeight + height * it < 800; it++) {
            graphics.drawString(book.getEntry()[it], secondRow, startHeight + height * it);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(pictureService.getBackgroundShortMenuColor());
        graphics.fillRect(0, 0, 810, 800);
        switch (statusShortMenu) {
            case MAIN:
                graphics.setFont(menuFont);
                graphics.setColor(Color.WHITE);
                drawArray(new String[]{"1     Продолжить", "2     Книги и записи", "3     Инвентарь", "4     В меню"}, 50, 50, 35, graphics);
                break;
            case INVENTORY:
                graphics.setFont(inventoryFont);
                graphics.setColor(Color.WHITE);
                switch (itemType) {
                    case ARMOR:
                        drawArray(armorItem.stream().map(fromItemToString).toArray(String[]::new), 50, 50, 430, 20, graphics);
                        break;
                    case POISON:
                        drawArray(poisonItem.stream().map(fromItemToString).toArray(String[]::new), 50, 50, 430, 20, graphics);
                        break;
                    case WEAPON:
                        drawArray(weaponItem.stream().map(fromItemToString).toArray(String[]::new), 50, 50, 430, 20, graphics);
                        break;
                    case SPECIAL:
                        drawArray(specialItem.stream().map(fromItemToString).toArray(String[]::new), 50, 50, 430, 20, graphics);
                        break;
                }
                break;
            case BOOK:
                graphics.setFont(bigWriterFont);
                graphics.setColor(Color.WHITE);
                if (books.isEmpty()) {
                    graphics.drawString("Пусто!", 120, 30);
                } else {
                    graphics.drawString(books.get(countSkipBook).getTitle(), 120, 30);
                    graphics.setFont(writerFont);
                    drawBook(books.get(countSkipBook), 80, 40, 420, 20, graphics);
                }
                break;
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        switch (statusShortMenu) {
            case MAIN:
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE:
                    case KeyEvent.VK_1:
                        dungeon.notShortMenu();
                        reloadBooksAndItems();
                        break;
                    case KeyEvent.VK_2:
                        reloadBooksAndItems();
                        statusShortMenu = StatusShortMenu.BOOK;
                        break;
                    case KeyEvent.VK_3:
                        reloadBooksAndItems();
                        statusShortMenu = StatusShortMenu.INVENTORY;
                        break;
                    case KeyEvent.VK_4:
                        dungeon.notShortMenu();
                        dungeon.exitToMenu();
                    default:
                        break;
                }
                break;
            case BOOK:
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE:
                        statusShortMenu = StatusShortMenu.MAIN;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (pageBook != 0) pageBook--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (pageBook != 0) pageBook++;
                        break;
                    case KeyEvent.VK_Q:
                        if (countSkipBook != 0) countSkipBook--;
                        pageBook = 0;
                        break;
                    case KeyEvent.VK_E:
                        if (countSkipBook + 1 < books.size()) countSkipBook++;
                        pageBook = 0;
                        break;
                }
                break;
            case INVENTORY:
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE:
                        statusShortMenu = StatusShortMenu.MAIN;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (countSkipItem % 2 != 0) countSkipItem--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (countSkipItem % 2 == 0 && countSkipItem + 1 < dungeon.getDungeonService().getHero().getInventory().getItems().size()) {
                            countSkipItem++;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (countSkipItem > 1) {
                            countSkipItem -= 2;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (countSkipItem + 2 < dungeon.getDungeonService().getHero().getInventory().getItems().size()) {
                            countSkipItem += 2;
                        }
                        break;
                    case KeyEvent.VK_Q:
                        if (itemType == Item.ItemType.WEAPON) itemType = Item.ItemType.ARMOR;
                        if (itemType == Item.ItemType.ARMOR) itemType = Item.ItemType.POISON;
                        if (itemType == Item.ItemType.POISON) itemType = Item.ItemType.SPECIAL;
                        break;
                    case KeyEvent.VK_E:
                        if (itemType == Item.ItemType.ARMOR) itemType = Item.ItemType.WEAPON;
                        if (itemType == Item.ItemType.POISON) itemType = Item.ItemType.ARMOR;
                        if (itemType == Item.ItemType.SPECIAL) itemType = Item.ItemType.POISON;
                        break;
                }
                break;
        }
    }
}

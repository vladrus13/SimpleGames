package ru.vladrus13.RPG.core.main.menu;

import ru.vladrus13.RPG.core.Dungeon;
import ru.vladrus13.RPG.core.graphics.ColorService;
import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.FontService;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.inventory.Item;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author vladkuznetsov
 * Short menu class
 */
public class ShortMenu extends Drawing implements KeyTaker {

    /**
     * Status of short menu
     */
    public enum StatusShortMenu {
        /**
         * In main menu
         */
        MAIN,
        /**
         * In book menu
         */
        BOOK,
        /**
         * In inventory
         */
        INVENTORY
    }

    /**
     * {@link Dungeon}
     */
    private final Dungeon dungeon;

    /**
     * {@link BookMenu}
     */
    private final BookMenu bookMenu;

    /**
     * {@link ItemMenu}
     */
    private final ItemMenu itemMenu;
    /**
     * {@link Font} - menu font
     */
    private final Font menuFont;
    /**
     * {@link StatusShortMenu}
     */
    private StatusShortMenu statusShortMenu;
    /**
     * Return to main menu
     */
    public void returnToMenu() {
        this.statusShortMenu = StatusShortMenu.MAIN;
    }

    /**
     * Constructor for class
     *
     * @param dungeonService {@link DungeonService}
     * @throws GameException if get font is incorrect
     */
    public ShortMenu(DungeonService dungeonService) throws GameException {
        this.statusShortMenu = StatusShortMenu.MAIN;
        this.dungeon = dungeonService.getDungeon();
        this.menuFont = new FontService().getFont("MK-90", 30);
        bookMenu = new BookMenu(dungeonService, this);
        itemMenu = new ItemMenu(dungeonService, this);
    }

    /**
     * Draw strings
     *
     * @param array       drawing strings
     * @param startHeight start height - Y-axis
     * @param firstRow    first row X-axis
     * @param height      height of string
     * @param graphics    {@link Graphics}
     */
    public void drawArray(String[] array, int startHeight, int firstRow, int height, Graphics graphics) {
        if (array.length == 0) {
            graphics.drawString("Пусто!!!", firstRow, startHeight);
        } else {
            for (int it = 0; it < array.length && startHeight + height * it < 800; it++) {
                graphics.drawString(array[it], firstRow, startHeight + height * it);
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(ColorService.backgroundShortMenuColor);
        graphics.fillRect(0, 0, 810, 800);
        switch (statusShortMenu) {
            case MAIN:
                graphics.setFont(menuFont);
                graphics.setColor(Color.WHITE);
                drawArray(new String[]{"1     Продолжить", "2     Книги и записи", "3     Инвентарь", "4     В меню"}, 50, 50, 35, graphics);
                break;
            case INVENTORY:
                itemMenu.draw(graphics);
                break;
            case BOOK:
                bookMenu.draw(graphics);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        switch (statusShortMenu) {
            case MAIN:
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE:
                    case KeyEvent.VK_1:
                        try {
                            dungeon.notShortMenu();
                        } catch (GameException e) {
                            e.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_2:
                        bookMenu.reload();
                        statusShortMenu = StatusShortMenu.BOOK;
                        break;
                    case KeyEvent.VK_3:
                        itemMenu.reload();
                        statusShortMenu = StatusShortMenu.INVENTORY;
                        break;
                    case KeyEvent.VK_4:
                        try {
                            dungeon.notShortMenu();
                        } catch (GameException e) {
                            e.printStackTrace();
                        }
                        dungeon.exitToMenu();
                    default:
                        break;
                }
                break;
            case BOOK:
                bookMenu.keyPressed(keyEvent);
                break;
            case INVENTORY:
                itemMenu.keyPressed(keyEvent);
                break;
        }
    }
}

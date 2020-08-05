package ru.vladrus13.RPG.core.main.menu;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.inventory.Book;
import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author vladkuznetsov
 * Book menu
 */
public class BookMenu extends Drawing implements KeyTaker {

    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;

    /**
     * {@link ShortMenu}
     */
    private final ShortMenu shortMenu;

    /**
     * Font for title
     */
    private final Font titleFont;

    /**
     * Font for text
     */
    private final Font writerFont;
    /**
     * Current book number
     */
    private int countBook = 0;
    /**
     * Current page number
     */
    private int countPage = 0;

    /**
     * Book height
     */
    private final int bookHeight = 750;

    /**
     * Constructor for class
     *
     * @param dungeonService {@link DungeonService}
     * @param shortMenu      {@link ShortMenu}
     * @throws GameException if font can't load
     */
    public BookMenu(DungeonService dungeonService, ShortMenu shortMenu) throws GameException {
        this.dungeonService = dungeonService;
        titleFont = dungeonService.getFontService().getFont("WriterFont", 30);
        writerFont = dungeonService.getFontService().getFont("WriterFont", 20);
        this.shortMenu = shortMenu;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setFont(titleFont);
        graphics.setColor(Color.WHITE);
        if (dungeonService.getHero().getInventory().getBooks().isEmpty()) {
            graphics.drawString("Пусто!!!", 120, 30);
        } else {
            Book book = dungeonService.getHero().getInventory().getBooks().get(countBook);
            graphics.drawString(book.getTitle(), 120, 30);
            int currentRowNumber = (bookHeight / writerFont.getSize() * 2) * countPage, row;
            for (row = currentRowNumber; row < Math.min(book.getEntry().length, currentRowNumber + bookHeight / writerFont.getSize()); row++) {
                graphics.drawString(book.getEntry()[row], 10, (row - currentRowNumber) * writerFont.getSize() + 50);
            }
            for (; row < Math.min(book.getEntry().length, currentRowNumber + bookHeight / writerFont.getSize() * 2); row++) {
                graphics.drawString(book.getEntry()[row], 410, (row - currentRowNumber - bookHeight / writerFont.getSize()) * writerFont.getSize() + 50);
            }
        }
    }

    /**
     * Reload counters. If we think, what books can removed or added, use it
     */
    public void reload() {
        countPage = 0;
        countBook = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Inventory inventory = dungeonService.getHero().getInventory();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                if (inventory.getBooks().size() != 0 && inventory.getBooks().get(countBook).getTitle().length() > countPage * 2 * (bookHeight / writerFont.getSize())) {
                    countPage++;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (inventory.getBooks().size() != 0 && countPage > 0) {
                    countPage--;
                }
                break;
            case KeyEvent.VK_Q:
                if (countBook > 0) {
                    countBook--;
                    countPage = 0;
                }
                break;
            case KeyEvent.VK_E:
                if (countBook + 1 < inventory.getBooks().size()) {
                    countBook++;
                    countPage = 0;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                shortMenu.returnToMenu();
                break;
        }
    }
}

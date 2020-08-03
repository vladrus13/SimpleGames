package ru.vladrus13.RPG.core.main.frame.shop;

import ru.vladrus13.RPG.core.inventory.Inventory;
import ru.vladrus13.RPG.core.main.dialog.Text;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author vladkuznetsov
 * Shop frame class
 */
public class Shop extends Drawing implements KeyTaker {

    /**
     * What we draw array
     */
    private final ArrayList<Text> texts;
    /**
     * Items
     */
    private final ArrayList<ShopItem> items;
    /**
     * {@link DungeonService}
     */
    private final DungeonService dungeonService;
    /**
     * Which item chooses
     */
    private int chooses = 0;
    /**
     * Text showing "not enough money"
     */
    private Text notEnoughMoney = null;

    /**
     * Adding empty item for normal draw
     */
    private void addEmptyItem() {
        try {
            texts.add(new Text("         ", new Point(20, 100), dungeonService.getFontService().getFont("Inventory", 18), 20, dungeonService));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for class
     *
     * @param items          items
     * @param dungeonService {@link DungeonService}
     * @throws GameException if we get font failed
     */
    public Shop(ArrayList<ShopItem> items, DungeonService dungeonService) throws GameException {
        this.items = items;
        this.dungeonService = dungeonService;
        Font font = dungeonService.getFontService().getFont("Inventory", 18);
        texts = new ArrayList<>();
        int leftY = 100;
        if (items.isEmpty()) {
            addEmptyItem();
        }
        for (ShopItem item : items) {
            texts.add(new Text(item.getItem().getName(), new Point(20, leftY), font, 20, dungeonService));
            leftY += 20;
        }
        texts.get(0).setChooses(true);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(20, 20, 255, 100));
        graphics.fillRect(0, 0, 800, 800);
        if (notEnoughMoney != null) {
            notEnoughMoney.draw(graphics);
        }
        for (Text text : texts) {
            text.draw(graphics);
        }
    }

    /**
     * Recalculate positions of items
     */
    public void recalculatePosition() {
        int leftY = 100;
        for (Text text : texts) {
            text.setPosition(new Point(20, leftY));
            leftY += 20;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        notEnoughMoney = null;
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN:
                texts.get(chooses).setChooses(false);
                chooses = (chooses + 1) % texts.size();
                texts.get(chooses).setChooses(true);
                break;
            case KeyEvent.VK_UP:
                texts.get(chooses).setChooses(false);
                chooses = (chooses - 1 + texts.size()) % texts.size();
                texts.get(chooses).setChooses(true);
                break;
            case KeyEvent.VK_ESCAPE:
                dungeonService.getDungeon().removeDrawing(this);
                try {
                    dungeonService.getDungeon().removeFocus(this);
                } catch (GameException gameException) {
                    gameException.printStackTrace();
                }
                break;
            case KeyEvent.VK_ENTER:
                if (!items.isEmpty()) {
                    if (dungeonService.getHero().getInventory().subtract(items.get(chooses).getCost())) {
                        try {
                            items.get(chooses).decreaseCount();
                        } catch (GameException gameException) {
                            gameException.printStackTrace();
                        }
                        dungeonService.getHero().getInventory().addItem(new Inventory.Items(items.get(chooses).getItem()));
                        if (items.get(chooses).getCount() == 0) {
                            remove(chooses);
                        }
                    } else {
                        try {
                            notEnoughMoney = new Text("Не хватает денег", new Point(400, 700), dungeonService.getFontService().getFont("Inventory", 25), 27, dungeonService);
                        } catch (GameException gameException) {
                            gameException.printStackTrace();
                        }
                    }
                }
        }
    }

    /**
     * Remove item from items and texts
     *
     * @param position position of removed item
     */
    public void remove(int position) {
        texts.get(chooses).setChooses(false);
        items.remove(position);
        texts.remove(position);
        if (texts.isEmpty()) {
            addEmptyItem();
        }
        if (chooses == texts.size()) chooses--;
        texts.get(chooses).setChooses(true);
        recalculatePosition();
    }

    /**
     * Add item to end
     *
     * @param item {@link ShopItem}
     */
    public void add(ShopItem item) {
        if (items.isEmpty()) {
            texts.remove(0);
        }
        int leftY = 100 + items.size() * 20;
        items.add(item);
        try {
            texts.add(new Text(item.getItem().getName(), new Point(20, leftY), dungeonService.getFontService().getFont("Inventory", 18), 20, dungeonService));
        } catch (GameException e) {
            e.printStackTrace();
        }
    }
}

package ru.vladrus13.RPG.core.inventory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author vladkuznetsov
 * Inventory class
 */
public class Inventory {
    /**
     * @author vladkuznetsov
     * Item with count class
     */
    public static class Items {
        /**
         * Count of item
         */
        private int count;
        /**
         * {@link Item} item
         */
        private Item item;

        /**
         * Constructor of class
         *
         * @param count count of item
         * @param item  item
         */
        public Items(int count, Item item) {
            this.count = count;
            this.item = item;
        }

        /**
         * Constructor of class with one item
         *
         * @param item item
         */
        public Items(Item item) {
            this.item = item;
            this.count = 1;
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
         * Setter for count
         *
         * @param count count
         */
        public void setCount(int count) {
            this.count = count;
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
         * Setter for item
         *
         * @param item item
         */
        public void setItem(Item item) {
            this.item = item;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Items items = (Items) o;
            return count == items.count &&
                    item.equals(items.item);
        }

        @Override
        public int hashCode() {
            return Objects.hash(count, item);
        }
    }

    /**
     * Current amount
     */
    private int money = 0;
    /**
     * Items {@link Items}
     */
    private final Set<Items> items;
    /**
     * Books {@link Book}
     */
    private final ArrayList<Book> books;

    /**
     * Constructor with empty inventory
     */
    public Inventory() {
        books = new ArrayList<>();
        items = new HashSet<>();
    }

    /**
     * Add item to inventory
     *
     * @param item {@link Items} item.
     */
    public void addItem(Items item) {
        AtomicBoolean isAdded = new AtomicBoolean(false);
        items.stream().filter(element -> element.getItem().equals(item.getItem())).forEachOrdered(element -> {
            isAdded.set(true);
            element.setCount(element.getCount() + item.getCount());
        });
        if (!isAdded.get()) {
            items.add(item);
        }
    }

    /**
     * Add book to inventory
     *
     * @param book {@link Book} item.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Getter for items
     *
     * @return items
     */
    public Set<Items> getItems() {
        return items;
    }

    /**
     * Getter for books
     *
     * @return books
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Getter for money
     *
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Setter for money
     *
     * @param money money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Subtract from money
     *
     * @param money subtrahend
     * @return if we can (total money more of equals 0), subtract and return true, else just return false;
     */
    public boolean subtract(int money) {
        if (this.money < money) return false;
        this.money -= money;
        return true;
    }
}

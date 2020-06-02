package ru.vladrus13.core.inventory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Inventory {
    public static class Items {
        private int count;
        private Item item;

        public Items(int count, Item item) {
            this.count = count;
            this.item = item;
        }

        public Items(Item item) {
            this.item = item;
            this.count = 1;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Item getItem() {
            return item;
        }

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

    private final Set<Items> items;
    private final ArrayList <Book> books;

    public Inventory() {
        books = new ArrayList<>();
        items = new HashSet<>();
    }

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

    public void addBook(Book book) {
        books.add(book);
    }

    public Set<Items> getItems() {
        return items;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}

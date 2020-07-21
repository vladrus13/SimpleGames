package ru.vladrus13.RPG.core.inventory;

public class Book {

    private final int id;
    private final String title;
    private final String[] entry;

    public Book(int id, String title, String[] entry) {
        this.id = id;
        this.title = title;
        this.entry = entry;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getEntry() {
        return entry;
    }
}

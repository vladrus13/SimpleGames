package ru.vladrus13.RPG.core.inventory;

public class Book {

    private final int id;
    private final String tile;
    private final String[] entry;

    public Book(int id, String tile, String[] entry) {
        this.id = id;
        this.tile = tile;
        this.entry = entry;
    }

    public int getId() {
        return id;
    }

    public String getTile() {
        return tile;
    }

    public String[] getEntry() {
        return entry;
    }
}

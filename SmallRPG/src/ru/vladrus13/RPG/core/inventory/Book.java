package ru.vladrus13.RPG.core.inventory;

/**
 * @author vladkuznetsov
 * Book in inventory class
 */
public class Book {

    /**
     * Id of book
     */
    private final int id;
    /**
     * Title of book
     */
    private final String title;
    /**
     * Books lines
     */
    private final String[] entry;

    /**
     * Constructor of class
     *
     * @param id    id of book
     * @param title title of book
     * @param entry book lines
     */
    public Book(int id, String title, String[] entry) {
        this.id = id;
        this.title = title;
        this.entry = entry;
    }

    /**
     * Getter for id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for book lines
     *
     * @return lines book
     */
    public String[] getEntry() {
        return entry;
    }
}

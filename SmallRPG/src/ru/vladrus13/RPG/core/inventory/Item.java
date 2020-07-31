package ru.vladrus13.RPG.core.inventory;

import ru.vladrus13.RPG.core.utils.DungeonService;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author vladkuznetsov
 * Item-class for {@link ru.vladrus13.RPG.core.person.Person}
 */
public class Item {

    /**
     * Enum for type of item
     */
    public enum ItemType {
        /**
         * Types of items
         */
        WEAPON, ARMOR, POISON, SPECIAL
    }

    /**
     * Item id
     */
    private final int id;
    /**
     * Description of item
     */
    private final String description;
    /**
     * Name of item
     */
    private final String name;
    /**
     * {@link Consumer} change on {@link DungeonService} when we use item
     */
    private final Consumer<DungeonService> usedOn;
    /**
     * {@link Consumer} change on {@link DungeonService} when we attack with this item
     */
    private final Consumer<DungeonService> attackOn;
    /**
     * {@link Consumer} change on {@link DungeonService} when we defending with this item
     */
    private final Consumer<DungeonService> attackingOn;
    /**
     * {@link ItemType} - type of item
     */
    private final ItemType itemType;

    /**
     * Constructor
     *
     * @param id          id of item
     * @param description description of item
     * @param name        name of item
     * @param usedOn      {@link Consumer} change on {@link DungeonService} when we use item
     * @param attackOn    {@link Consumer} change on {@link DungeonService} when we attack with this item
     * @param attackingOn {@link Consumer} change on {@link DungeonService} when we defending with this item
     * @param itemType    {@link ItemType} - type of item
     */
    public Item(int id, String description, String name, Consumer<DungeonService> usedOn, Consumer<DungeonService> attackOn, Consumer<DungeonService> attackingOn, ItemType itemType) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.usedOn = usedOn;
        this.attackOn = attackOn;
        this.attackingOn = attackingOn;
        this.itemType = itemType;
    }

    /**
     * Calls, if we use item
     *
     * @param dungeonService {@link DungeonService}
     */
    public void usedOn(DungeonService dungeonService) {
        usedOn.accept(dungeonService);
    }

    /**
     * Calls, if we attack with this item
     *
     * @param dungeonService {@link DungeonService}
     */
    public void attackOn(DungeonService dungeonService) {
        attackOn.accept(dungeonService);
    }

    /**
     * Calls, if we defending with this item
     *
     * @param dungeonService {@link DungeonService}
     */
    public void attackingOn(DungeonService dungeonService) {
        attackingOn.accept(dungeonService);
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
     * Getter for description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for item type - {@link ItemType}
     *
     * @return item type
     */
    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Getter for name
     *
     * @return name
     */
    public String getName() {
        return name;
    }
}

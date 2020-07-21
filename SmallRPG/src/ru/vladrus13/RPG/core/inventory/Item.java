package ru.vladrus13.RPG.core.inventory;

import ru.vladrus13.RPG.core.utils.DungeonService;

import java.util.Objects;
import java.util.function.Consumer;

public class Item {

    public enum ItemType {
        WEAPON, ARMOR, POISON, SPECIAL
    }

    private final int id;
    private final String description, name;
    private final Consumer<DungeonService> usedOn;
    private final Consumer<DungeonService> attackOn;
    private final Consumer<DungeonService> attackingOn;
    private final ItemType itemType;

    Item(int id, String description, String name, Consumer<DungeonService> usedOn, Consumer<DungeonService> attackOn, Consumer<DungeonService> attackingOn, ItemType itemType) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.usedOn = usedOn;
        this.attackOn = attackOn;
        this.attackingOn = attackingOn;
        this.itemType = itemType;
    }

    public void usedOn(DungeonService dungeonService) {
        usedOn.accept(dungeonService);
    }

    public void attackOn(DungeonService dungeonService) {
        attackOn.accept(dungeonService);
    }

    public void attackingOn(DungeonService dungeonService) { attackingOn.accept(dungeonService); }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

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

    public String getName() {
        return name;
    }
}

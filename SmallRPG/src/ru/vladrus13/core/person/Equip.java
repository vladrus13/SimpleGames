package ru.vladrus13.core.person;

import ru.vladrus13.core.main.dungeon.item.DungeonItem;

import java.util.ArrayList;

public class Equip {
    private final ArrayList<DungeonItem> dungeonItems;


    public Equip(ArrayList<DungeonItem> dungeonItems) {
        this.dungeonItems = dungeonItems;
    }

    public ArrayList<DungeonItem> getDungeonItems() {
        return dungeonItems;
    }
}

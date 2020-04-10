package ru.vladrus13.core.person;

import ru.vladrus13.core.item.Item;

import java.util.ArrayList;

public class Equip {
    private final ArrayList<Item> items;


    public Equip(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}

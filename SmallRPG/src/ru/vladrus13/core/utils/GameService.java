package ru.vladrus13.core.utils;

import ru.vladrus13.core.main.dialog.Dialog;
import ru.vladrus13.core.main.dungeon.Floor;
import ru.vladrus13.core.person.unit.Hero;

public class GameService {
    Floor currentFloor;
    Hero hero;
    Dialog dialog;

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}

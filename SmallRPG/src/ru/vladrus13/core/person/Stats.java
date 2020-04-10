package ru.vladrus13.core.person;

public class Stats {
    private final int hp;
    private final int armor;
    private final int attack;
    private final int mp;

    public Stats() {
        this.hp = 100;
        this.armor = 0;
        this.attack = 10;
        this.mp = 50;
    }

    public Stats(int hp, int armor, int attack, int mp) {
        this.hp = hp;
        this.armor = armor;
        this.attack = attack;
        this.mp = mp;
    }

    public int getHp() {
        return hp;
    }

    public int getArmor() {
        return armor;
    }

    public int getAttack() {
        return attack;
    }

    public int getMp() {
        return mp;
    }
}

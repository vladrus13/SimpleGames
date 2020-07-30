package ru.vladrus13.RPG.core.person;

public class Stats implements Cloneable {
    private int hp;
    private int maxHp;
    private int armor;
    private int attack;
    private int mp;

    public Stats() {
        this.hp = 100;
        this.maxHp = 100;
        this.armor = 0;
        this.attack = 10;
        this.mp = 50;
    }

    public Stats(int hp, int maxHp, int armor, int attack, int mp) {
        this.hp = hp;
        this.maxHp = maxHp;
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

    public int getMaxHp() {
        return maxHp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    @Override
    protected Stats clone() {
        return new Stats(hp, maxHp, armor, attack, mp);
    }
}

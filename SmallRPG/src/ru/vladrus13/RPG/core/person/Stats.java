package ru.vladrus13.RPG.core.person;

/**
 * @author vladkuznetsov
 * Stats class for {@link Person}
 */
public class Stats implements Cloneable {
    /**
     * Health Points
     *
     * @see <a href="https://en.wikipedia.org/wiki/Health_(game_terminology)#:~:text=Health%20is%20usually%20measured%20in,barred%20from%20taking%20further%20action.">Wikipedia</a>
     */
    private int hp;
    /**
     * Maximum heath points
     */
    private int maxHp;
    /**
     * Armor points
     */
    private int armor;
    /**
     * Attack
     */
    private int attack;
    /**
     * Mana points
     */
    private int mp;

    /**
     * Empty constructor for class
     */
    public Stats() {
        this.hp = 100;
        this.maxHp = 100;
        this.armor = 0;
        this.attack = 10;
        this.mp = 50;
    }

    /**
     * Constructor class
     *
     * @param hp     health point
     * @param maxHp  maximum health points
     * @param armor  armor
     * @param attack attack
     * @param mp     mana points
     */
    public Stats(int hp, int maxHp, int armor, int attack, int mp) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.armor = armor;
        this.attack = attack;
        this.mp = mp;
    }

    /**
     * Getter for health points
     *
     * @return health points
     */
    public int getHp() {
        return hp;
    }

    /**
     * Getter for armor
     *
     * @return armor
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Getter for attack
     *
     * @return attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Getter for mana points
     *
     * @return mana points
     */
    public int getMp() {
        return mp;
    }

    /**
     * Getter for maximum health points
     *
     * @return maximum health points
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Set health points
     *
     * @param hp health points
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Set maximum health points
     *
     * @param maxHp maximum health points
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Set armor
     *
     * @param armor armor
     */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * Set attack
     *
     * @param attack attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Set mana points
     *
     * @param mp mana points
     */
    public void setMp(int mp) {
        this.mp = mp;
    }

    @Override
    public Stats clone() {
        return new Stats(hp, maxHp, armor, attack, mp);
    }
}

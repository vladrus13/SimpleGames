package ru.vladrus13.RPG.core.main.dungeon.event;

/**
 * @author vladkuznetsov
 * Type of {@link Event}
 */
public enum TypeActiveEvent {
    /**
     * Event triggered, if you step on tile
     */
    ON_STEP,
    /**
     * Event triggered, if you press enter on tile
     */
    ENTER_SIDE,
    /**
     * Event triggered, if you press enter on tile, adjacent to this tile
     */
    ENTER_ONE_TILE,
    /**
     * Event triggered, if you call him on code
     */
    TRIGGERED
}

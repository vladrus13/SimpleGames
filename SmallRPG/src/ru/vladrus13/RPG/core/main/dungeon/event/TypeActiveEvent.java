package ru.vladrus13.RPG.core.main.dungeon.event;

/**
 * @author vladkuznetsov
 * Type of {@link Event}
 */
public enum TypeActiveEvent {
    /**
     * ON_STEP - event triggered, if you step on tile
     * ENTER_SIDE - event triggered, if you press enter on tile
     * ENTER_ONE_tile - event triggered, if you press enter on tile, adjacent to this tile
     * TRIGGERED - event triggered, if you call him on code
     */
    ON_STEP, ENTER_SIDE, ENTER_ONE_TILE, TRIGGERED
}

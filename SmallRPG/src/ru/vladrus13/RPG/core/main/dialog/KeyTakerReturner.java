package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;
import ru.vladrus13.RPG.core.utils.exception.GameException;

/**
 * Abstract class for {@link KeyTaker}, which we wait
 */
public abstract class KeyTakerReturner extends Drawing implements KeyTaker {
    /**
     * Is this end
     */
    protected boolean isEnd;

    /**
     * Is this end
     *
     * @return is KeyTaker end
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * @return return object
     * @throws GameException if this not end
     */
    public abstract Object returner() throws GameException;
}

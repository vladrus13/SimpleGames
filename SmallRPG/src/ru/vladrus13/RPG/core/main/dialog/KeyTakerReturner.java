package ru.vladrus13.RPG.core.main.dialog;

import ru.vladrus13.RPG.core.graphics.Drawing;
import ru.vladrus13.RPG.core.graphics.KeyTaker;

/**
 * Abstract class for {@link KeyTaker}, which we wait
 */
public abstract class KeyTakerReturner extends Drawing implements KeyTaker {
    /**
     * Is this end
     */
    protected boolean isEnd;

    /**
     * Is this accepted
     */
    protected boolean isAccepted;

    /**
     * Is this end
     *
     * @return is KeyTaker end
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * Is this accepted
     *
     * @return is KeyTaker accepted
     */
    public boolean isAccepted() {
        return isAccepted;
    }
}

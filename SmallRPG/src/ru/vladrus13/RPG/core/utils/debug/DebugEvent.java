package ru.vladrus13.RPG.core.utils.debug;

import ru.vladrus13.RPG.core.utils.picture.KeyTaker;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_H;

public class DebugEvent implements KeyTaker {
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case VK_H:
                // TODO RUN
                break;
        }
    }
}

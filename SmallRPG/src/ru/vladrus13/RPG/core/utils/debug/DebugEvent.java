package ru.vladrus13.RPG.core.utils.debug;

import ru.vladrus13.RPG.core.graphics.Animation;
import ru.vladrus13.RPG.core.utils.DungeonService;
import ru.vladrus13.RPG.core.utils.exception.GameException;
import ru.vladrus13.RPG.core.utils.picture.KeyTaker;
import ru.vladrus13.RPG.core.utils.ways.Point;

import java.awt.event.KeyEvent;
import java.nio.file.Path;

import static java.awt.event.KeyEvent.VK_H;

public class DebugEvent implements KeyTaker {

    private final DungeonService dungeonService;

    public DebugEvent(DungeonService dungeonService) {
        this.dungeonService = dungeonService;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == VK_H) {
            try {
                dungeonService.getDungeon().addDrawing(new Animation(
                        dungeonService.getPictureService().loadImage(Path.of("assets/pictures/animations/temp.png")),
                        100, new Point(1, 1), new Point(192, 192)));
            } catch (GameException gameException) {
                gameException.printStackTrace();
            }
        }
    }
}

package ru.vladrus13.RPG.core;

import ru.vladrus13.RPG.core.utils.exception.GameException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game  extends JPanel implements ActionListener, MouseListener, KeyListener {

    public enum STATUS_GAME {
        DUNGEON, MENU
    }

    private final Dungeon dungeon;
    private final Menu menu;
    final Timer timer = new Timer(20, this);
    JFrame frame;

    private STATUS_GAME statusGame = STATUS_GAME.MENU;

    public Game() throws GameException {
        int width = 800;
        int height = 800;
        dungeon = new Dungeon(this);
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Insets insets = frame.getInsets();
        int frameLeftBorder = insets.left;
        int frameRightBorder = insets.right;
        int frameTopBorder = insets.top;
        int frameBottomBorder = insets.bottom;
        frame.setPreferredSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
        frame.setMaximumSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
        frame.setMinimumSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        menu = new Menu(this, frame);
        frame.add(this);
        repaint();
    }

    public void setStatusGame(STATUS_GAME statusGame) {
        this.statusGame = statusGame;
        switch (statusGame) {
            case DUNGEON:
                menu.setVisible(false);
                timer.start();
                frame.addMouseListener(this);
                frame.addKeyListener(this);
                frame.requestFocus();
                break;
            case MENU:
                frame.removeMouseListener(this);
                frame.removeKeyListener(this);
                timer.stop();
                menu.setVisible(true);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        super.paintComponents(graphics);
        if (statusGame == STATUS_GAME.DUNGEON) {
            dungeon.draw(graphics);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (statusGame == STATUS_GAME.DUNGEON) {
            dungeon.update(dungeon.getDungeonService());
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (statusGame == STATUS_GAME.DUNGEON) {
            dungeon.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}

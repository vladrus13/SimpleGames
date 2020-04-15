package ru.vladrus13.core;

import ru.vladrus13.core.utils.exception.GameException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game  extends JPanel implements ActionListener, MouseListener, KeyListener {

    public enum STATUSGAME {
        DUNGEON, MENU, BATTLE
    }

    private final Dungeon dungeon;
    private final Menu menu;
    Timer timer = new Timer(20, this);
    JFrame frame;

    private STATUSGAME statusGame = STATUSGAME.MENU;

    public Game() throws GameException {
        int width = 800;
        int height = 800;
        dungeon = new Dungeon();
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

    public void setStatusGame(STATUSGAME statusGame) {
        this.statusGame = statusGame;
        timer.start();
        frame.addMouseListener(this);
        frame.addKeyListener(this);
        frame.requestFocus();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        super.paintComponents(graphics);
        if (statusGame == STATUSGAME.DUNGEON) {
            dungeon.draw(graphics);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (statusGame == STATUSGAME.DUNGEON) {
            dungeon.update();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (statusGame == STATUSGAME.DUNGEON) {
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

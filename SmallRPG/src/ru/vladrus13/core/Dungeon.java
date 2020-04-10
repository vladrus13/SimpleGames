package ru.vladrus13.core;

import ru.vladrus13.core.main.dialog.Dialog;
import ru.vladrus13.core.main.dungeon.Floor;
import ru.vladrus13.core.person.unit.Hero;
import ru.vladrus13.core.utils.GameService;
import ru.vladrus13.core.utils.exception.GameException;
import ru.vladrus13.core.utils.ways.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static ru.vladrus13.core.utils.ways.Direction.*;

public class Dungeon extends JPanel implements ActionListener, MouseListener, KeyListener {

    private final Floor floor;
    private final Hero hero;
    private final GameService gameService;

    public Dungeon() throws GameException {
        int width = 800;
        int height = 800;
        Timer timer = new Timer(20, this);
        floor = new Floor(2);
        hero = new Hero(0, new Point(1, 1), UP);
        gameService = new GameService();
        gameService.setCurrentFloor(floor);
        gameService.setHero(hero);
        JFrame frame = new JFrame();
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
        frame.addMouseListener(this);
        frame.addKeyListener(this);
        frame.requestFocus();
        frame.setVisible(true);
        frame.add(this);
        repaint();
        timer.start();
    }

    public void paint(Graphics graphics) {
        super.paintComponent(graphics);
        floor.draw(graphics);
        hero.draw(graphics);
        if (gameService.getDialog() != null) {
            gameService.getDialog().draw(graphics);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hero.update();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP: hero.startWent(UP, gameService); break;
            case KeyEvent.VK_DOWN: hero.startWent(DOWN, gameService); break;
            case KeyEvent.VK_LEFT: hero.startWent(LEFT, gameService); break;
            case KeyEvent.VK_RIGHT: hero.startWent(RIGHT, gameService); break;
            case KeyEvent.VK_ENTER:
                if (gameService.getDialog() != null) {
                    if (gameService.getDialog().hasNext()) {
                        gameService.getDialog().next();
                    } else {
                        gameService.setDialog(null);
                    }
                } else {
                    gameService.setDialog(new Dialog(new String[]{"hello", "hello. hello", "hello. hello. hello"}));
                }
                break;
            default: break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}

package ru.vladrus13.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board extends JPanel implements ActionListener, MouseListener {

    Chessman[][] board = new Chessman[8][8];

    public Board() {

    }

    public void window(Board game) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setBackground(Color.cyan);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Insets insets = frame.getInsets();
        int frameLeftBorder = insets.left;
        int frameRightBorder = insets.right;
        int frameTopBorder = insets.top;
        int frameBottomBorder = insets.bottom;
        frame.setPreferredSize(new Dimension(800 + frameLeftBorder + frameRightBorder, 800 + frameBottomBorder + frameTopBorder));
        frame.setMaximumSize(new Dimension(800 + frameLeftBorder + frameRightBorder, 800 + frameBottomBorder + frameTopBorder));
        frame.setMinimumSize(new Dimension(800 + frameLeftBorder + frameRightBorder, 800 + frameBottomBorder + frameTopBorder));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(this);
        frame.requestFocus();
        frame.setVisible(true);
        frame.add(game);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

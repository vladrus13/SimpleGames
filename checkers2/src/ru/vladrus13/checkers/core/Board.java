package ru.vladrus13.checkers.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.ref.WeakReference;

import static ru.vladrus13.checkers.core.Board.PLACE.*;

public class Board extends JPanel implements ActionListener, MouseListener {

    private PLACE[][] board = new PLACE[8][8];
    private final int width, height;
    private final int tileSize;
    private PLACE turn;
    private int mindRow = -1, mindCol = -1;

    public Board() {
        width = 800;
        height = 800;
        tileSize = width / 8;
        turn = WHITE;
        window(width, height, this);
        generateStartBoard();
        repaint();
    }

    public void window(int width, int height, Board game) {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setBackground(Color.cyan);
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
        frame.requestFocus();
        frame.setVisible(true);
        frame.add(game);
    }

    public void generateStartBoard() {
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                board[col][row] = EMPTY;
            }
        }
        for (int col = 0; col < 8; col += 2) {
            board[col][5] = PLACE.BLACK;
            board[col][7] = PLACE.BLACK;
            board[col][1] = PLACE.WHITE;
        }
        for (int col = 1; col < 8; col += 2) {
            board[col][0] = PLACE.WHITE;
            board[col][2] = PLACE.WHITE;
            board[col][6] = PLACE.BLACK;
        }
    }

    public void paint(Graphics graphics) {
        super.paintComponent(graphics);
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if ((row + col) % 2 == 0) {
                    graphics.setColor(Color.GRAY);
                } else {
                    graphics.setColor(Color.DARK_GRAY.darker());
                }
                graphics.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
                if (board[col][row] != EMPTY) {
                    Color checkerColor;
                    if (board[col][row] == WHITE) {
                        checkerColor = Color.WHITE;
                    } else {
                        checkerColor = Color.BLACK;
                    }
                    graphics.setColor(checkerColor);
                    graphics.fillOval((col * tileSize) + 2, (row * tileSize) + 2, tileSize - 4, tileSize - 4);
                }
            }
        }
        if (mindCol != -1 && mindRow != -1) {
            graphics.setColor(Color.GREEN);
            graphics.fillOval((mindCol * tileSize) + 4, (mindRow * tileSize) + 4, tileSize - 8, tileSize - 8);
        }
    }

    private PLACE switchPlace(PLACE place) {
        if (place == WHITE) return BLACK;
        if (place == BLACK) return WHITE;
        return place;
    }

    private void switchTurn() {
        turn = switchPlace(turn);
    }

    private PLACE getStatus(int col, int row) {
        return board[col][row];
    }

    private void beat(int oldC, int oldR, int c, int r) {
        if (getStatus(oldC, oldR) == turn && getStatus(c, r) == EMPTY && getStatus((oldC + c) / 2, (oldR + r) / 2) == switchPlace(turn)) {
            board[oldC][oldR] = EMPTY;
            board[c][r] = turn;
            board[(oldC + c) / 2][(oldR + r) / 2] = EMPTY;
            switchTurn();
        }
    }

    private void step(int oldC, int oldR, int c, int r) {
        if (getStatus(oldC, oldR) == turn && getStatus(c, r) == EMPTY) {
            board[oldC][oldR] = EMPTY;
            board[c][r] = turn;
            switchTurn();
        }
    }

    private void click(int col, int row) {
        if (!(row > 7 || row < 0 || col < 0 || col > 7)) {
            if (mindRow == -1 && mindCol == -1) {
                // new turn
                if (board[col][row] == turn) {
                    mindRow = row;
                    mindCol = col;
                }
            } else {
                if (Math.abs(mindCol - col) == 1 && Math.abs(mindRow - row) == 1) {
                    step(mindCol, mindRow, col, row);
                }
                if (Math.abs(mindCol - col) == 2 && Math.abs(mindRow - row) == 2) {
                    beat(mindCol, mindRow, col, row);
                }
                mindCol = -1;
                mindRow = -1;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = (e.getX() - 8) / tileSize;
        int row = (e.getY() - 30) / tileSize;
        click(col, row);
        repaint();
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

    enum PLACE {
        WHITE, BLACK, EMPTY
    }
}

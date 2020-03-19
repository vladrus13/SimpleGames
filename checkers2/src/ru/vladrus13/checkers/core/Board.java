package ru.vladrus13.checkers.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static ru.vladrus13.checkers.core.Board.PLACE.*;

public class Board extends JPanel implements ActionListener, MouseListener {

    private PLACE[][] board;
    private final int width, height;
    private final int tileSize, titles;
    private PLACE turn;
    private int mindRow = -1, mindCol = -1;

    public Board(int titles) {
        width = 800;
        height = 800;
        board = new PLACE[titles][titles];
        tileSize = width / titles;
        this.titles = titles;
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
        for (int col = 0; col < titles; col++) {
            for (int row = 0; row < titles; row++) {
                board[col][row] = EMPTY;
            }
        }
        for (int col = 0; col < titles; col += 2) {
            board[col][titles - 3] = PLACE.BLACK;
            board[col][titles - 1] = PLACE.BLACK;
            board[col][1] = PLACE.WHITE;
        }
        for (int col = 1; col < titles; col += 2) {
            board[col][0] = PLACE.WHITE;
            board[col][2] = PLACE.WHITE;
            board[col][titles - 2] = PLACE.BLACK;
        }
    }

    public void paint(Graphics graphics) {
        super.paintComponent(graphics);
        for (int col = 0; col < titles; col++) {
            for (int row = 0; row < titles; row++) {
                if ((row + col) % 2 == 0) {
                    graphics.setColor(Color.GRAY);
                } else {
                    graphics.setColor(Color.DARK_GRAY.darker());
                }
                graphics.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
                if (board[col][row] != EMPTY) {
                    Color checkerColor;
                    if (board[col][row] == WHITE || board[col][row] == WHITE_KING) {
                        checkerColor = Color.WHITE;
                    } else {
                        checkerColor = Color.BLACK;
                    }
                    graphics.setColor(checkerColor);
                    graphics.fillOval((col * tileSize) + 2, (row * tileSize) + 2, tileSize - 4, tileSize - 4);
                    if (board[col][row] == WHITE_KING || board[col][row] == BLACK_KING) {
                        graphics.setColor(Color.RED);
                        graphics.fillRect((col * tileSize) + 20, (row * tileSize) + 20, tileSize - 40, tileSize - 40);
                    }
                }
            }
        }
        if (mindCol != -1 && mindRow != -1) {
            graphics.setColor(Color.GREEN);
            graphics.fillOval((mindCol * tileSize) + 24, (mindRow * tileSize) + 24, tileSize - 48, tileSize - 48);
        }
    }

    private boolean isCorrectPlace(int col, int row) {
        return (col > -1 && row > -1 && col < titles && row < titles);
    }

    private PLACE switchPlace(PLACE place) {
        if (place == WHITE) return BLACK;
        if (place == BLACK) return WHITE;
        return place;
    }

    private void setKing(int col, int row) {
        if (board[col][row] == BLACK && row == 0) {
            board[col][row] = BLACK_KING;
        }
        if (board[col][row] == WHITE && row == titles - 1) {
            board[col][row] = WHITE_KING;
        }
    }

    private boolean isKing(int col, int row) {
        return (board[col][row] == BLACK_KING || board[col][row] == WHITE_KING);
    }

    private void switchTurn() {
        turn = switchPlace(turn);
    }

    private PLACE getStatus(int col, int row) {
        return board[col][row];
    }

    private int getColor(int col, int row) {
        return (getStatus(col, row) == WHITE_KING || getStatus(col, row) == WHITE ? 1 : (getStatus(col, row) == BLACK_KING || getStatus(col, row) == BLACK? 0 : -1));
    }

    private int getColor(PLACE a) {
        return (a == WHITE_KING || a == WHITE ? 1 : (a == BLACK_KING || a == BLACK ? 0 : -1));
    }

    private boolean isOneColor(int col1, int row1, int col2, int row2) {
        int color1 = getColor(col1, row1);
        int color2 = getColor(col2, row2);
        return color1 == color2 && color1 != -1;
    }

    private boolean isOneColor(PLACE a, PLACE b) {
        int color1 = getColor(a);
        int color2 = getColor(b);
        return color1 == color2 && color1 != -1;
    }

    private boolean isOppositeColor(int col1, int row1, int col2, int row2) {
        int color1 = getColor(col1, row1);
        int color2 = getColor(col2, row2);
        return color1 != color2 && color1 != -1 && color2 != -1;
    }

    private int beatUsual(int oldC, int oldR, int c, int r) {
        if (getStatus(oldC, oldR) == turn && getStatus(c, r) == EMPTY && isOppositeColor((oldC + c) / 2, (oldR + r) / 2, oldC, oldR)) {
            board[oldC][oldR] = EMPTY;
            board[c][r] = turn;
            board[(oldC + c) / 2][(oldR + r) / 2] = EMPTY;
            setKing(c, r);
            return 1;
        }
        return -1;
    }

    private int stepUsual(int oldC, int oldR, int c, int r) {
        if (getStatus(oldC, oldR) == turn && getStatus(c, r) == EMPTY && ((turn == BLACK && r - oldR == -1) || (turn == WHITE && r - oldR == 1))) {
            board[oldC][oldR] = EMPTY;
            board[c][r] = turn;
            setKing(c, r);
            return 0;
        }
        return -1;
    }

    private int superStep(int oldC, int oldR, int c, int r) {
        if (isKing(oldC, oldR) && getStatus(c, r) == EMPTY) {
            int columnDirection = (c - oldC) / Math.abs(c - oldC);
            int rowDirection = (r - oldR) / Math.abs(r - oldR);
            boolean canGo = true;
            for (int go = 1; oldC + go * columnDirection != c; go++) {
                if (isOneColor(oldC, oldR, oldC + go * columnDirection, oldR + go * rowDirection) ||
                        isOneColor(oldC + go * columnDirection, oldR + go * rowDirection, oldC + (go - 1) * columnDirection, oldR + (go - 1) * rowDirection)) canGo = false;
            }
            int countBeaten = 0;
            if (canGo) {
                for (int go = 1; oldC + go * columnDirection != c; go++) {
                    if (board[oldC + go * columnDirection][oldR + go * rowDirection] != EMPTY) {
                        countBeaten++;
                        board[oldC + go * columnDirection][oldR + go * rowDirection] = EMPTY;
                    }
                }
                board[c][r] = board[oldC][oldR];
                board[oldC][oldR] = EMPTY;
                return (countBeaten == 0 ? 0 : 1);
            }
        }
        return -1;
    }

    private boolean canBeat(int col, int row) {
        if (getStatus(col, row) != EMPTY) {
            if (isKing(col, row)) {
                for (int columnDirection = -1; columnDirection < 2; columnDirection += 2) {
                    for (int rowDirection = -1; rowDirection < 2; rowDirection += 2) {
                        boolean flag = true;
                        // TODO
                        return false;
                    }
                }
            } else {
                boolean can = false;
                for (int columnDirection = -1; columnDirection < 2; columnDirection += 2) {
                    for (int rowDirection = -1; rowDirection < 2; rowDirection += 2) {
                        int futureBeatColumn = col + columnDirection, futureBeatRow = row + rowDirection;
                        int futurePlaceColumn = futureBeatColumn + columnDirection, futurePlaceRow = futureBeatRow + rowDirection;
                        if (isCorrectPlace(futurePlaceColumn, futurePlaceRow)) {
                            if (isOppositeColor(col, row, futureBeatColumn, futureBeatRow) && getStatus(futurePlaceColumn, futurePlaceRow) == EMPTY) {
                                can = true;
                            }
                        }
                    }
                }
                return can;
            }
        }
        return false;
    }

    private void click(int col, int row) {
        if (isCorrectPlace(col, row)) {
            if (mindRow == -1 && mindCol == -1) {
                // new turn
                if (isOneColor(board[col][row], turn)) {
                    mindRow = row;
                    mindCol = col;
                }
            } else {
                int isGo = -1;
                if (Math.abs(mindCol - col) == 1 && Math.abs(mindRow - row) == 1 && getStatus(mindCol, mindRow) != WHITE_KING && getStatus(mindCol, mindRow) != BLACK_KING) {
                    isGo = stepUsual(mindCol, mindRow, col, row);
                }
                if (Math.abs(mindCol - col) == 2 && Math.abs(mindRow - row) == 2 && getStatus(mindCol, mindRow) != WHITE_KING && getStatus(mindCol, mindRow) != BLACK_KING) {
                    isGo = beatUsual(mindCol, mindRow, col, row);
                }
                if (getStatus(mindCol, mindRow) == WHITE_KING || getStatus(mindCol, mindRow) == BLACK_KING) {
                    isGo = superStep(mindCol, mindRow, col, row);
                }
                if (isGo != -1) {
                    if (canBeat(col, row) && isGo == 1) {
                        mindRow = row;
                        mindCol = col;
                    } else {
                        switchTurn();
                        mindCol = -1;
                        mindRow = -1;
                    }
                } else {
                    mindCol = -1;
                    mindRow = -1;
                }
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
        WHITE, BLACK, EMPTY, WHITE_KING, BLACK_KING
    }
}

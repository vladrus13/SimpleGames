package ru.vladrus13.core;

public class Chessman {
    private int color = 0; // 1 - white, -1 - black
    private TYPE type;

    public Chessman(int color, TYPE type) {
        this.color = color;
        this.type = type;
    }

    enum TYPE {
        PAWS, ROOK, KNIGHT, BISHOP, QUEEN, KING
        // пешка, ладья, конь, слон, королева, король
    }
}

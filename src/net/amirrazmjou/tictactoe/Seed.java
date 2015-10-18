package net.amirrazmjou.tictactoe;

/**
 * Created by Amir Razmjou on 10/12/15.
 */


enum Seed {
    EMPTY(" "), NOUGHT("O"), CROSS("X");
    private final String symbol;
    private Seed(String s) {
        symbol = s;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public Seed opponent() {
        if (this == NOUGHT) return CROSS;
        else if (this == CROSS) return NOUGHT;
        else return EMPTY;
    }
}

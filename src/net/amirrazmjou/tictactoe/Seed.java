package net.amirrazmjou.tictactoe;

/**
 * Created by Amir Razmjou on 10/12/15.
 */


public enum Seed {
    EMPTY(" ", 0), NOUGHT("O", 1), CROSS("X", 2);
    private final String symbol;
    private final int i;
    private Seed(String s, int i) {
        symbol = s;
        this.i = i;
    }

    public int intValue() { return i; }

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

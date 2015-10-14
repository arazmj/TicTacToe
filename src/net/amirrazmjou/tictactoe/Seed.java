package net.amirrazmjou.tictactoe;

/**
 * Created by Amir Razmjou on 10/12/15.
 */


enum Seed {
    NOUGHT, CROSS, EMPTY, NONE;
    public Seed opponent() {
        if (this == NOUGHT) return CROSS;
        else if (this == CROSS) return NOUGHT;
        else return EMPTY;
    }
}

package net.amirrazmjou.tictactoe;

import java.util.List;

/**
 * Created by Amir Razmjou on 10/13/15.
 */
public interface Board {
    void setCell(Point move, Seed c);
    Seed getCell(Point move);

    Seed winner();

    List<Point> getAvailableMoves();

    int getMaxMoves();

    @Override
    String toString();



    boolean isFull();
}

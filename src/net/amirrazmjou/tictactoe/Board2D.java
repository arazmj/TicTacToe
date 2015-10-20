package net.amirrazmjou.tictactoe;

import java.util.LinkedList;
import java.util.List;

public class Board2D implements Board {

    protected Seed cells[][];

    public Board2D(int size) {
        cells = new Seed[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j] = Seed.EMPTY;
    }

    @Override
    public void setCell(Point p, Seed c) {
        cells[p.r][p.c] = c;
    }

    public Seed getCell(Point p) {
        return cells[p.r][p.c];
    }

    @Override
    public Seed winner() {
        for (int x = 0; x < cells.length; x++) {
            Seed r = cells[x][0];
            Seed c = cells[0][x];
            boolean rb = r != Seed.EMPTY;
            boolean cb = c != Seed.EMPTY;
            for (int y = 0; y < cells.length; y++) {
                rb = rb && r == cells[x][y];
                cb = cb && c == cells[y][x];
            }
            if (rb) return r;
            if (cb) return c;
        }

        Seed d1 = cells[0][0];
        Seed d2 = cells[0][cells.length - 1];
        boolean d1b = d1 != Seed.EMPTY;
        boolean d2b = d2 != Seed.EMPTY;;
        for (int c = 0; c < cells.length; c++) {
            d1b = d1b && d1 == cells[c][c];
            d2b = d2b && d2 == cells[cells.length - 1 - c][c];
        }
        if (d1b) return d1;
        if (d2b) return d2;

        return null;
    }

    public List<Point> getAvailableMoves() {
        List<Point> moves = new LinkedList<>();
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells.length; c++) {
                if (cells[r][c] == Seed.EMPTY)
                    moves.add(new Point(r, c));
            }
        }
        return moves;
    }

    @Override
    public int getMaxMoves() {
        return cells.length * cells.length;
    }

    @Override
    public String toString() {
        String s = "";
        for (int r = 0; r < cells.length; r++)
            for (int c = 0; c < cells.length; c++)
                s += String.format("%s%c", cells[r][c],
                        c < cells.length  - 1 ? '|' : '\n');
        return s;
    }

    public boolean isFull() {
        boolean b = true;
        for (int r = 0; r < cells.length; r++)
            for (int c = 0; c < cells.length; c++)
                if (cells[c][r] == Seed.EMPTY)
                    b = false;
        return b;
    }
}

package net.amirrazmjou.tictactoe;

import java.util.LinkedList;
import java.util.List;

class Board2D implements Board {

    protected Seed cells[][];

    public Board2D(int size) {
        cells = new Seed[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j] = Seed.EMPTY;
    }

    @Override
    public void setCell(Point p, Seed c) {
        cells[p.y][p.x] = c;
    }

    public Seed getCell(Point p) {
        return cells[p.y][p.x];
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
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells.length; y++) {
                if (cells[y][x] == Seed.EMPTY)
                    moves.add(new Point(x, y));
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        String s = "";
        for (int x = 0; x < cells.length; x++)
            for (int y = 0; y < cells.length; y++)
                s += String.format("%s%c", getCell(new Point(x, y)),
                        y < cells.length  - 1 ? '|' : '\n');
        return s;
    }

    public boolean isFull() {
        boolean b = true;
        for (int x = 0; x < cells.length; x++)
            for (int y = 0; y < cells.length; y++)
                if (cells[y][x] == Seed.EMPTY)
                    b = false;
        return b;
    }
}

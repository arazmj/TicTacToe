package net.amirrazmjou.tictactoe;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Amir Razmjou on 10/17/15.
 */
public class Board3D implements Board {

    protected Seed cells[][][];

    public Board3D(int size) {
        this.cells = new Seed[size][size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                for (int k = 0; k < size; k++)
                    cells[i][j][k] = Seed.EMPTY;
    }

    @Override
    public void setCell(Point m, Seed c) {
        cells[m.z][m.y][m.x] = c;
    }

    @Override
    public Seed getCell(Point m) {
        return cells[m.z][m.y][m.x];
    }

    @Override
    public Seed winner() {
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++) {
                Seed r = cells[j][0][i];
                Seed c = cells[0][j][i];
                boolean rb = r != Seed.EMPTY;
                boolean cb = c != Seed.EMPTY;
                for (int k = 0; k < cells.length; k++) {
                    rb = rb && r == cells[j][k][i];
                    cb = cb && c == cells[k][j][i];
                }
                if (rb) return r;
                if (cb) return c;
            }
        return null;
    }

    @Override
    public List<Point> getAvailableMoves() {
        List<Point> moves = new LinkedList<>();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells.length; y++) {
                for (int z = 0; z < cells.length; z++) {
                    if (cells[z][y][x] == Seed.EMPTY)
                        moves.add(new Point(x, y, z));
                }
            }
        }
        return moves;
    }

    @Override
    public boolean isFull() {
        boolean b = true;
        for (int x = 0; x < cells.length; x++)
            for (int y = 0; y < cells.length; y++)
                for (int z = 0; z < cells.length; z++)
                    if (cells[z][y][x] == Seed.EMPTY)
                        b = false;
        return b;
    }

    @Override
    public String toString() {
        String s = "";
        for (int z = 0; z < cells.length; z++) {
            for (int x = 0; x < cells.length; x++)
                for (int y = 0; y < cells.length; y++)
                    s += String.format("%s%c", getCell(new Point(x, y, z)).toString(),
                            y < cells.length - 1 ? '|' : '\t');
            s += "\n";
        }

        return s;
    }
}

package net.amirrazmjou.tictactoe;

import java.util.Arrays;
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
    public void setCell(Point move, Seed c) {
    }

    @Override
    public Seed getCell(Point m) {
        return cells[m.x][m.y][m.z];
    }

    @Override
    public Seed winner() {
        return null;
    }

    @Override
    public List<Point> getAvailableMoves() {
        return null;
    }

    @Override
    public boolean isFull() {
        return false;
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

package net.amirrazmjou.tictactoe;

import java.util.*;
import java.util.stream.Stream;

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
        cells[m.l][m.r][m.c] = c;
    }

    @Override
    public Seed getCell(Point m) {
        return cells[m.l][m.r][m.c];
    }


    public boolean immediateWinner() {
        List<Point> availableMoves = getAvailableMoves();
        for (Seed thisSeed : new Seed[]{Seed.CROSS, Seed.NOUGHT}) {
            for (Point move : availableMoves) {
                setCell(move, thisSeed);
                Seed winner = winner();
                if (winner != null) {
                    return true;
                }
                setCell(move, Seed.EMPTY);
            }
        }
        return false;
    }

    @SuppressWarnings("Duplicates")
    public int evaluate(Seed seed) {
        // TODO: Can you write a general purpose winner function for a
        // TODO: hypercube with arbitrary dimension number
        LinkedList<LinkedList<Seed>> lines = new LinkedList<>();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                LinkedList<Seed> line1 = new LinkedList<>();
                LinkedList<Seed> line2 = new LinkedList<>();
                LinkedList<Seed> line3 = new LinkedList<>();
                for (int k = 0; k < cells.length; k++) {
                    line1.add(cells[j][k][i]);
                    line2.add(cells[k][j][i]);
                    line3.add(cells[j][i][k]);
                }
                lines.add(line1);
                lines.add(line2);
                lines.add(line3);
            }

            LinkedList<Seed> line1 = new LinkedList<>();
            LinkedList<Seed> line2 = new LinkedList<>();
            LinkedList<Seed> line3 = new LinkedList<>();
            LinkedList<Seed> line4 = new LinkedList<>();
            LinkedList<Seed> line5 = new LinkedList<>();
            LinkedList<Seed> line6 = new LinkedList<>();
            for (int c = 0; c < cells.length; c++) {
                line1.add(cells[i][c][c]);
                line2.add(cells[i][cells.length - 1 - c][c]);
                line3.add(cells[c][i][c]);
                line4.add(cells[cells.length - 1 - c][i][c]);
                line5.add(cells[c][c][i]);
                line6.add(cells[cells.length - 1 - c][c][i]);
            }
            lines.add(line1);
            lines.add(line2);
            lines.add(line3);
            lines.add(line4);
            lines.add(line5);
            lines.add(line6);
        }

        LinkedList<Seed> line1 = new LinkedList<>();
        LinkedList<Seed> line2 = new LinkedList<>();
        LinkedList<Seed> line3 = new LinkedList<>();
        LinkedList<Seed> line4 = new LinkedList<>();

        for (int c = 0; c < cells.length; c++) {
            line1.add(cells[c][c][c]);
            line2.add(cells[cells.length - 1 - c][c][c]);
            line3.add(cells[c][cells.length - 1 - c][c]);
            line4.add(cells[cells.length - 1 - c][cells.length - 1 - c][c]);
        }

        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);

        int crossScore = 0, noughtScore = 0;
        for (LinkedList<Seed> l : lines) {
//            for (Seed seed : l) {
//                if (seed == Seed.EMPTY)
//                    System.out.print("_");
//                else
//                    System.out.print(seed);
//            }
//            System.out.print(" : ");
            if (l.contains(Seed.CROSS) && l.contains(Seed.NOUGHT))
            {}
            else if (l.contains(Seed.CROSS))
            {
//                System.out.print(getMaxMoves() * Collections.frequency(l, Seed.CROSS) / cells.length);
                crossScore += Math.pow(Collections.frequency(l, Seed.CROSS), 2);
            }
            else if (l.contains(Seed.NOUGHT)) {
//                System.out.print(getMaxMoves() * Collections.frequency(l, Seed.NOUGHT) / cells.length);
                noughtScore += Math.pow(Collections.frequency(l, Seed.NOUGHT), 2);
            }
//            System.out.println();
        }
//        System.out.println(String.format("Cross %d Nought %d size %d", crossScore, noughtScore, lines.size()));


        Seed winner;
        if (crossScore > noughtScore)
            winner = Seed.CROSS;
        else
            winner = Seed.NOUGHT;

        if (winner == seed)
            return Math.abs(crossScore - noughtScore);
        else
            return Math.abs(crossScore - noughtScore) * -1;
    }

    @Override
    public Seed winner() {
        // TODO: Can you write a general purpose winner function for a
        // TODO: hypercube with arbitrary dimension number
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                Seed r = cells[j][0][i];
                Seed c = cells[0][j][i];
                Seed l = cells[j][i][0];
                boolean rb = r != Seed.EMPTY;
                boolean cb = c != Seed.EMPTY;
                boolean lb = l != Seed.EMPTY;

                for (int k = 0; k < cells.length; k++) {
                    rb = rb && r == cells[j][k][i];
                    cb = cb && c == cells[k][j][i];
                    lb = lb && l == cells[j][i][k];
                }
                if (rb) return r;
                if (cb) return c;
                if (lb) return l;
            }

            Seed d1 = cells[i][0][0];
            Seed d2 = cells[i][0][cells.length - 1];
            Seed d3 = cells[0][i][0];
            Seed d4 = cells[0][i][cells.length - 1];
            Seed d5 = cells[0][0][i];
            Seed d6 = cells[0][cells.length - 1][i];
            boolean d1b = d1 != Seed.EMPTY;
            boolean d2b = d2 != Seed.EMPTY;
            boolean d3b = d3 != Seed.EMPTY;
            boolean d4b = d4 != Seed.EMPTY;
            boolean d5b = d5 != Seed.EMPTY;
            boolean d6b = d6 != Seed.EMPTY;

            for (int c = 0; c < cells.length; c++) {
                d1b = d1b && d1 == cells[i][c][c];
                d2b = d2b && d2 == cells[i][cells.length - 1 - c][c];
                d3b = d3b && d3 == cells[c][i][c];
                d4b = d4b && d4 == cells[cells.length - 1 - c][i][c];
                d5b = d5b && d5 == cells[c][c][i];
                d6b = d6b && d6 == cells[cells.length - 1 - c][c][i];
            }
            if (d1b) return d1;
            if (d2b) return d2;
            if (d3b) return d3;
            if (d4b) return d4;
            if (d5b) return d5;
            if (d6b) return d6;
        }

        Seed d1 = cells[0][0][0];
        Seed d2 = cells[cells.length - 1][0][0];
        Seed d3 = cells[0][cells.length - 1][0];
        Seed d4 = cells[cells.length - 1][cells.length - 1][0];

        boolean d1b = d1 != Seed.EMPTY;
        boolean d2b = d2 != Seed.EMPTY;
        boolean d3b = d3 != Seed.EMPTY;
        boolean d4b = d4 != Seed.EMPTY;

        for (int c = 0; c < cells.length; c++) {
            d1b = d1b && d1 == cells[c][c][c];
            d2b = d2b && d2 == cells[cells.length - 1 - c][c][c];
            d3b = d3b && d3 == cells[c][cells.length - 1 - c][c];
            d4b = d4b && d4 == cells[cells.length - 1 - c][cells.length - 1 - c][c];
        }

        if (d1b) return d1;
        if (d2b) return d2;
        if (d3b) return d3;
        if (d4b) return d4;

        return null;
    }

    @Override
    public List<Point> getAvailableMoves() {
        List<Point> moves = new LinkedList<>();
        for (int l = 0; l < cells.length; l++) {
            for (int r = 0; r < cells.length; r++) {
                for (int c = 0; c < cells.length; c++) {
                    if (cells[l][r][c] == Seed.EMPTY)
                        moves.add(new Point(l, r, c));
                }
            }
        }
        return moves;
    }

    @Override
    public int getMaxMoves() {
        return  cells.length * cells.length * cells.length;
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
        for (int r = 0; r < cells.length; r++) {
            for (int l = 0; l < cells.length; l++)
                for (int c = 0; c < cells.length; c++)
                    s += String.format("%s%c", getCell(new Point(l, r, c)).toString(),
                            c < cells.length - 1 ? '|' : '\t');
            s += "\n";
        }

        return s;
    }
}

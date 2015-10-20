package net.amirrazmjou.tictactoe;

/**
 * Created by Amir Razmjou on 10/13/15.
 */

public class Point {
    public int l, c, r;

    public Point(int l, int r, int c) {
        this.l = l;
        this.c = c;
        this.r = r;
    }

    public Point(int r, int c) {
        this.c = c;
        this.r = r;
    }

    public void set(Point p) {
        l = p.l;
        c = p.c;
        r = p.r;
    }

    public Point() {
    }
}

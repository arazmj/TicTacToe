package net.amirrazmjou.tictactoe;

/**
 * Created by Amir Razmjou on 10/13/15.
 */

public class Point {
    public int x, y, z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point p) {
        x = p.x;
        y = p.y;
        z = p.z;
    }

    public Point() {
    }
}

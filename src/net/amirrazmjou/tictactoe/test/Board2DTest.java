package net.amirrazmjou.tictactoe.test;

import junit.framework.TestCase;
import net.amirrazmjou.tictactoe.Board2D;
import net.amirrazmjou.tictactoe.Point;
import net.amirrazmjou.tictactoe.Seed;

/**
 * Created by Amir Razmjou on 10/20/15.
 */
public class Board2DTest extends TestCase {

    public void testWinner() throws Exception {
        int size = 4;
        Board2D board1 =  new Board2D(size);
        Board2D board2 =  new Board2D(size);
        Board2D board3 =  new Board2D(size);
        Board2D board4 =  new Board2D(size);

        for (int i = 0; i < size; i++) {
            board1.setCell(new Point(i, i), Seed.CROSS);
            board2.setCell(new Point(i, size - i - 1), Seed.NOUGHT);
            board3.setCell(new Point(0, i), Seed.CROSS);
            board4.setCell(new Point(i, 0), Seed.NOUGHT);
        }

        if (board1.winner() != Seed.CROSS && board2.winner() != Seed.NOUGHT)
            throw new Exception("Simple diagonal on Board2D.");

        if (board3.winner() != Seed.CROSS && board4.winner() != Seed.NOUGHT)
            throw new Exception("Simple line on Board2D.");

    }
}
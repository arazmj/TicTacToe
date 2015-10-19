package net.amirrazmjou.tictactoe;

import junit.extensions.ExceptionTestCase;
import junit.framework.TestCase;

/**
 * Created by Amir Razmjou on 10/17/15.
 */
public class Board3DTest extends TestCase {

    public void testWinner() throws Exception {
        Board3D board = new Board3D(3);
        if (board.winner() != null)
            throw new Exception("No winner should exists ");

        board.setCell(new Point(0, 0, 0), Seed.CROSS);
        board.setCell(new Point(0, 0, 1), Seed.CROSS);
        board.setCell(new Point(0, 0, 2), Seed.CROSS);

        if (board.winner() != Seed.CROSS)
            throw new Exception("No winner on simple column row arrangement.");


            System.out.println(board);

    }
}
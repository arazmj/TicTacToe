package net.amirrazmjou.tictactoe.test;

import junit.extensions.ExceptionTestCase;
import junit.framework.TestCase;
import net.amirrazmjou.tictactoe.*;

/**
 * Created by Amir Razmjou on 10/17/15.
 */
public class Board3DTest extends TestCase {

    public void testWinner() throws Exception {
        Board3D board1 = new Board3D(3);
        if (board1.winner() != null)
            throw new Exception("No winner should exists ");

        board1.setCell(new Point(0, 0, 0), Seed.CROSS);
        board1.setCell(new Point(0, 0, 1), Seed.CROSS);
        board1.setCell(new Point(0, 0, 2), Seed.CROSS);

        Board3D board2 = new Board3D(3);

        board2.setCell(new Point(0, 0, 0), Seed.CROSS);
        board2.setCell(new Point(0, 1, 0), Seed.CROSS);
        board2.setCell(new Point(0, 2, 0), Seed.CROSS);

        if (board2.winner() != Seed.CROSS)
            throw new Exception("No winner on simple column row arrangement.");

        Board3D board3 = new Board3D(3);

        board3.setCell(new Point(2, 0, 0), Seed.CROSS);
        board3.setCell(new Point(2, 1, 0), Seed.CROSS);
        board3.setCell(new Point(2, 2, 0), Seed.CROSS);

        if (board3.winner() != Seed.CROSS)
            throw new Exception("No winner on simple column row arrangement.");


        Board3D board4 = new Board3D(3);

        board4.setCell(new Point(0, 0, 0), Seed.CROSS);
        board4.setCell(new Point(1, 0, 0), Seed.CROSS);
        board4.setCell(new Point(2, 0, 0), Seed.CROSS);

        if (board4.winner() != Seed.CROSS)
            throw new Exception("No winner on simple level arrangement.");


        Board3D board5 = new Board3D(3);

        board5.setCell(new Point(0, 1, 1), Seed.CROSS);
        board5.setCell(new Point(1, 1, 1), Seed.CROSS);
        board5.setCell(new Point(2, 1, 1), Seed.CROSS);

        if (board5.winner() != Seed.CROSS)
            throw new Exception("No winner on simple level arrangement.");


        Board3D board6 = new Board3D(3);
        board6.setCell(new Point(0, 0, 0), Seed.CROSS);
        board6.setCell(new Point(0, 1, 1), Seed.CROSS);
        board6.setCell(new Point(0, 2, 2), Seed.CROSS);

        if (board6.winner() != Seed.CROSS)
            throw new Exception("No winner on simple diagonal arrangement.");


        Board3D board7 = new Board3D(3);
        board7.setCell(new Point(2, 0, 0), Seed.CROSS);
        board7.setCell(new Point(2, 1, 1), Seed.CROSS);
        board7.setCell(new Point(2, 2, 2), Seed.CROSS);

        if (board7.winner() != Seed.CROSS)
            throw new Exception("No winner on simple diagonal arrangement.");


        Board3D board8 = new Board3D(3);
        board8.setCell(new Point(0, 0, 0), Seed.NOUGHT);
        board8.setCell(new Point(1, 0, 1), Seed.NOUGHT);
        board8.setCell(new Point(2, 0, 2), Seed.NOUGHT);

        if (board8.winner() != Seed.NOUGHT)
            throw new Exception("No winner on simple diagonal arrangement.");


        Board3D board9 = new Board3D(3);
        board9.setCell(new Point(0, 0, 0), Seed.NOUGHT);
        board9.setCell(new Point(1, 1, 1), Seed.NOUGHT);
        board9.setCell(new Point(2, 2, 2), Seed.NOUGHT);

        if (board9.winner() != Seed.NOUGHT)
            throw new Exception("No winner on simple diagonal arrangement.");


        Board3D board10 = new Board3D(3);
        board10.setCell(new Point(0, 0, 2), Seed.NOUGHT);
        board10.setCell(new Point(1, 1, 1), Seed.NOUGHT);
        board10.setCell(new Point(2, 2, 0), Seed.NOUGHT);

        if (board10.winner() != Seed.NOUGHT)
            throw new Exception("No winner on simple diagonal arrangement.");


        Board3D board11 = new Board3D(3);
        board11.setCell(new Point(0, 2, 2), Seed.NOUGHT);
        board11.setCell(new Point(1, 1, 1), Seed.NOUGHT);
        board11.setCell(new Point(2, 0, 0), Seed.NOUGHT);

        if (board11.winner() != Seed.NOUGHT)
            throw new Exception("No winner on simple diagonal arrangement.");

        Board3D board12 = new Board3D(3);
        board12.setCell(new Point(0, 2, 0), Seed.NOUGHT);
        board12.setCell(new Point(1, 1, 1), Seed.NOUGHT);
        board12.setCell(new Point(2, 0, 2), Seed.NOUGHT);

        if (board12.winner() != Seed.NOUGHT)
            throw new Exception("No winner on simple diagonal arrangement.");

        Board3D board13 = new Board3D(4);
        board13.setCell(new Point(0, 1, 0), Seed.CROSS);
        board13.setCell(new Point(1, 1, 1), Seed.CROSS);
        board13.setCell(new Point(2, 1, 2), Seed.CROSS);
        board13.setCell(new Point(3, 1, 3), Seed.CROSS);
        if (board13.winner() != Seed.CROSS) {
            throw new Exception("Cheap diagonal on 4x4x4");
        }



    }

    public void testEvaluate() throws Exception {
        Board3D board = new Board3D(4);
        TicTacToe.putRandomMoves(board, 16, 0);


//        System.out.println(board);
//        board.evaluate();

    }
}
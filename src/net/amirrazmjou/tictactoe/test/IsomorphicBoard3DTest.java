package net.amirrazmjou.tictactoe.test;

import junit.framework.TestCase;
import net.amirrazmjou.tictactoe.*;

import java.util.Arrays;

/**
 * Created by Amir Razmjou on 10/19/15.
 */
public class IsomorphicBoard3DTest extends TestCase {

    public void testGetIsoPosition() throws Exception {
        IsomorphicBoard3D board = new IsomorphicBoard3D(3);

        board.setCell(new Point(0, 0, 0), Seed.CROSS);
        board.setCell(new Point(0, 0, 1), Seed.NOUGHT);
        board.setCell(new Point(1, 2, 2), Seed.NOUGHT);
        board.setCell(new Point(1, 1, 1), Seed.CROSS);
        board.setCell(new Point(2, 1, 0), Seed.NOUGHT);

        if (!board.getIsoPosition().equals("x3o13x17o19o"))
            throw new Exception("getIsoPosition wrong result.");

    }


    public void testGenerateIsos() throws Exception {

        int size = 4;
        IsomorphicBoard3D board = new IsomorphicBoard3D(size);

        board.setCell(new Point(1, 2, 2), Seed.CROSS);
        board.setCell(new Point(0, 0, 1), Seed.NOUGHT);
//        board.setCell(new Point(1, 2, 2), Seed.NOUGHT);
//        board.setCell(new Point(1, 1, 1), Seed.CROSS);
//        board.setCell(new Point(2, 1, 0), Seed.NOUGHT);
//        board.setCell(new Point(1, 1, 1), Seed.CROSS);
//        board.setCell(new Point(3, 3, 3), Seed.CROSS);

        IsomorphicBoard3D.Isomorphism isomorphism[] = board.generateIsos();

//        for (IsomorphicBoard3D.Isomorphism iso: isomorphism) {
//            for (int i = 0; i < size; i++) {
//                for (int j = 0; j < size; j++) {
//                    for (int k = 0; k < size; k++) {
//                        Point p = iso.map[i][j][k];
//                        int i1 = p.x * size * size + p.y * size + p.z;
//                        System.out.print(String.format("%3d", i1));
//                    }
//                }
//            }
//            System.out.println();
//        }
        System.out.println(board);
        int i = board.qb_find_canonical_iso();
        System.out.println(i);
        for ( i = 0; i < 193; i++) {
            String s = board.getBoard(i).getIsoPosition();
            if (s.startsWith("x"))
                System.out.println(i + ":" + s);
        }

    }
}
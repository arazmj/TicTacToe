package net.amirrazmjou.tictactoe.test;

import junit.framework.TestCase;
import net.amirrazmjou.tictactoe.*;

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
//        System.out.println(board);
//        int i = board.getCanonicalId();
//        System.out.println(i);
//        for ( i = 0; i < 193; i++) {
//            String s = board.getCanonicalPosition2();
//            if (s.startsWith("x"))
//                System.out.println(i + ":" + s);
//        }

    }

    public void testGetCanonicalPosition2() throws Exception {
        IsomorphicBoard3D board = new IsomorphicBoard3D(4);
        board.generateIsos();
        board.setCell(new Point(1, 0, 0), Seed.NOUGHT);
        board.setCell(new Point(1, 0, 1), Seed.NOUGHT);
        board.setCell(new Point(1, 2, 2), Seed.NOUGHT);
        board.setCell(new Point(1, 2, 3), Seed.NOUGHT);
        board.setCell(new Point(2, 1, 1), Seed.CROSS);
        board.setCell(new Point(2, 3, 2), Seed.CROSS);
        board.setCell(new Point(3, 0, 2), Seed.NOUGHT);
        board.setCell(new Point(3, 1, 1), Seed.CROSS);
        board.setCell(new Point(3, 1, 3), Seed.CROSS);
        board.setCell(new Point(3, 3, 3), Seed.CROSS);

        String s = board.getCanonicalPosition2();


        IsomorphicBoard3D board2 = new IsomorphicBoard3D(4);
        board2.generateIsos();

        board2.setCell(new Point(0, 0, 0), Seed.CROSS);
        board2.setCell(new Point(0, 2, 0), Seed.CROSS);
        board2.setCell(new Point(0, 2, 2), Seed.CROSS);
        board2.setCell(new Point(0, 3, 1), Seed.NOUGHT);
        board2.setCell(new Point(1, 0, 1), Seed.CROSS);
        board2.setCell(new Point(1, 2, 2), Seed.CROSS);
        board2.setCell(new Point(2, 1, 0), Seed.NOUGHT);
        board2.setCell(new Point(2, 1, 1), Seed.NOUGHT);
        board2.setCell(new Point(2, 3, 2), Seed.NOUGHT);
        board2.setCell(new Point(2, 3, 3), Seed.NOUGHT);

        String s2 = board2.getCanonicalPosition2();

        if (!s.equals(s2) || s.isEmpty() || s2.isEmpty())
            throw new Exception("Mirrored isos are not equal");


    }
}
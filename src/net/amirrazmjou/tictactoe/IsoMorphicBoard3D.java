package net.amirrazmjou.tictactoe;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Amir Razmjou on 10/19/15.
 */
public class IsomorphicBoard3D extends Board3D {
    public IsomorphicBoard3D(int size) {
        super(size);
    }

    private String symbol;

    Integer getNext(int start) {
        for (int i = start; i < getMaxMoves(); i++) {
            int r = i / cells.length;
            int l = r / cells.length;
            r %= cells.length;
            int c = i % cells.length;
//            System.out.print(l + ":" + r + ":" + c);
//            System.out.println(cells[r][c][l]);
            if (cells[r][c][l] != Seed.EMPTY) {
                symbol = cells[r][c][l].toString();
                return i;
            }
        }
        return null;
    }

    public String getIsoPosition() {
        StringBuilder result = new StringBuilder();
        Integer next = 0, prev = 0;
        while ((next = getNext(next)) != null) {

            if (next == 1)
                result.append(1);
            else if (next - prev > 1)
                result.append(next - prev - 1);

            prev = next;
            result.append(symbol.toLowerCase());
            next++;
        }
        return result.toString();
    }

    public class Isomorphism {
        int inverse = 0;
        public Point[][][] map =
            new Point[cells.length][cells.length][cells.length];        /**< The mapping. */
    }

    Isomorphism isos[] = new Isomorphism[193];

    public Isomorphism[] generateIsos() throws Exception {
        for (int i = 0; i < isos.length; i++)
            isos[i] = new Isomorphism();

        // TODO: Invalid for 3x3x3
        int evert[] = {1, 0, 3, 2};
        int scramble[] = {0, 2, 1, 3};
        int i, j, k;

        int isonum = 0;

        /* Begin with the identity isomorphism */
        isos[isonum].inverse = isonum;  /* self-inverse */
        for (i = 0; i < cells.length; i++)
            for (j = 0; j <  cells.length; j++)
                for (k = 0; k <  cells.length; k++) {
                    isos[isonum].map[i][j][k] = new Point(i, j, k);
                }
        isonum++;

        /* A reflection of the identity (any one will do) */
        isos[isonum].inverse = isonum;  /* self-inverse */
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[isonum].map[i][j][k] = isos[0].map[i][j][cells.length - 1 - k];
                }
            }
        }
        isonum++;

        /* rotate the identity around the i-axis */
        isos[isonum].inverse = -1; /* not known yet */
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[isonum].map[i][j][k] = isos[0].map[i][k][cells.length - 1 - j];
                }
            }
        }
        isonum++;

        /* rotate the identity around the j-axis */
        isos[isonum].inverse = -1; /* not known yet */
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[isonum].map[i][j][k] = isos[0].map[k][j][cells.length - 1 - i];
                }
            }
        }
        isonum++;

        /* The "eversion" isomorphism turns the cube inside-out. */
        isos[isonum].inverse = isonum;  /* self-inverse */
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[isonum].map[i][j][k] = isos[0].map[evert[i]][evert[j]][evert[k]];
                }
            }
        }
        isonum++;

        /* The "scramble" isomorphism swaps the inner coordinates. */
        isos[isonum].inverse = isonum;  /* self-inverse */
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[isonum].map[i][j][k] = isos[0].map[scramble[i]][scramble[j]][scramble[k]];
                }
            }
        }
        isonum++;


        /*
        * Now do all possible compositions. This is brute-force, and it takes a few seconds on
        */

        int newiso = 6;
        int thisiso;

        for (i = 1; i < 6; i++) {
            for (j = 2; j < newiso; j++) {
                thisiso = newiso++;
                isos[thisiso].inverse = -1;
                if (newiso > 193) {
                    throw new Exception(String.format("There are too many (%d) isomorphisms", newiso));
                }
                qb_iso_compose(isos[i], isos[j], isos[thisiso]);
                for (k = 0; k + 1 < newiso; k++) {
                    if (qb_iso_compare(isos[thisiso], isos[k])) {
                        newiso--;
                        break;
                    }
                }
            }
        }

        assert (newiso == 192);
        /* Next, we find the inverses of all the isomorphisms.  They should all be here
         * already; we just have to find them.
         */


//        for (i=0; i<newiso; i++) {
//            // Skip those whose inverse is already known
//            if (isos[i].inverse == -1) {
//                for (j = i; j < newiso; j++) {
//                    if (isos[j].inverse == -1) {
//                        for (k = 0; k < 64; k++) {
//                            if (k != isos[j].map[isos[i].map[k]]) {
//                                break; /* not the inverse */
//                            }
//                        }
//                        if (k < 64) {
//                            continue; /* not the inverse */
//                        }
//                        isos[i].inverse = j;
//                        isos[j].inverse = i;
//                        break;
//                    }
//                }
//                assert (j < newiso);
//            }
//        }
        return isos;
    }




    public int qb_find_canonical_iso()
    {
        int bestiso = 0;
        for (int trialiso = 0; trialiso < 192; trialiso++ ) {
            if (qb_compare_views(cells, trialiso, bestiso) > 0) {
                bestiso = trialiso;
            }
        }
        return bestiso;
    }


    private boolean qb_iso_compare(Isomorphism left, Isomorphism right) {
        for (int i = 0; i <  cells.length; i++)
            for (int j = 0; j <  cells.length; j++)
                for (int k = 0; k <  cells.length; k++)
                    if (left.map[i][j][k] != right.map[i][j][k])
                        return false;
        return true;
    }

    private void qb_iso_compose(Isomorphism left, Isomorphism right, Isomorphism result) {
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++)
                for (int k = 0; k < cells.length; k++) {
                    Point r = right.map[i][j][k];
                    result.map[i][j][k] = left.map[r.l][r.r][r.c];
                }
    }

    int qb_compare_views(Seed board[][][], int iso1, int iso2)
    {
        Isomorphism map1;
        Isomorphism map2;
        Seed state1, state2;

        if (iso1 == iso2) return 0;
        map1 = isos[iso1];
        map2 = isos [iso2];
        for (int i = 0; i <  cells.length; i++)
            for (int j = 0; j <  cells.length; j++)
                for (int k = 0; k <  cells.length; k++) {
                    Point p1 = map1.map[i][j][k];
                    Point p2 = map2.map[i][j][k];

                    // TODO: POTENTIAL PROBLEM x y z
                    state1 = board[p1.l][p1.r][p1.c];
                    state2 = board[p2.l][p2.r][p2.c];

                    if (state1.intValue() < state2.intValue()) {
                        return -1;
                    } else if (state1 == state2) {
                        continue;
                    } else {
                        return 1;
                    }
                }
        return 0;
    }

    public IsomorphicBoard3D getBoard(int iso) {
        IsomorphicBoard3D board = new IsomorphicBoard3D(cells.length);
        Isomorphism isomorphism = isos[iso];
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++)
                for (int k = 0; k < cells.length; k++) {
                    Point p = isomorphism.map[i][j][k];
                    board.cells[i][j][k] = cells[p.l][p.r][p.c];
                }
        return board;
    }
}

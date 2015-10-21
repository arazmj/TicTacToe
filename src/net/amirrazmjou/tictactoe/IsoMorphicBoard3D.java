package net.amirrazmjou.tictactoe;

/**
 * Created by Amir Razmjou on 10/19/15.
 */
public class IsomorphicBoard3D extends Board3D {
    public IsomorphicBoard3D(int size)  {
        super(size);
    }

    private String symbol;

    Integer getNext(int start, Seed cells[][][]) {
        for (int i = start; i < getMaxMoves(); i++) {
            int r = i / cells.length;
            int l = r / cells.length;
            r %= cells.length;
            int c = i % cells.length;
//            System.out.println(l + ":" + r + ":" + c);
//            System.out.print(cells[l][r][c]);
            if (cells[l][r][c] != Seed.EMPTY) {
                symbol = cells[l][r][c].toString();
                return i;
            }
        }
        return null;
    }


    public String  getIsoPosition() {
        return getIsoPosition(this.cells);
    }


    public String getIsoPosition(Seed cells[][][]) {
        StringBuilder result = new StringBuilder();
        Integer next = 0, prev = -1;
        while ((next = getNext(next, cells)) != null) {

            int distance = next - prev - 1;
            if (distance > 0)
                result.append(distance);
            prev = next;
            result.append(symbol.toLowerCase());
            next++;
        }
        return result.toString();
    }

    /// T(n) = 192 * 64 + 64 + 64
//    public String getCanonicalPosition()  {
//        int i = getCanonicalId();
//        Seed board[][][] = getIsoBoard(i);
//        String s = getIsoPosition(board);
//        return s;
//    }

    public String getCanonicalPosition2()  {
        int canonicalId = 0;
        for (int i = 0; i < 192; i++) {
            // worst case compareIso is 64 average case much less than 64/2
            if (compareIso(cells, i, canonicalId) > 0) {
                canonicalId = i;
            }
        }

        //getIsoBoard(i)
        Seed[][][] board1 = new Seed[cells.length][cells.length][cells.length];
        Isomorphism iso = isos[canonicalId];
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++)
                for (int k = 0; k < cells.length; k++) {
                    Point p = iso.map[i][j][k];
                    board1[i][j][k] = cells[p.l][p.r][p.c];
                }
        String s = getIsoPosition(board1);
        return s;
    }

    public class Isomorphism {
        public Point[][][] map =
            new Point[cells.length][cells.length][cells.length];   /*The mapping. */
    }

    Isomorphism isos[] = new Isomorphism[193];

    public Isomorphism[] generateIsos() {
        for (int i = 0; i < isos.length; i++)
            isos[i] = new Isomorphism();

        // TODO: Invalid for 3x3x3
        int evt[] = {1, 0, 3, 2};
        int srm[] = {0, 2, 1, 3};
        int i, j, k;

        int nextIso = 0;

        // identity isomorphism
        for (i = 0; i < cells.length; i++)
            for (j = 0; j <  cells.length; j++)
                for (k = 0; k <  cells.length; k++) {
                    isos[nextIso].map[i][j][k] = new Point(i, j, k);
                }
        nextIso++;

        // reflection
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[nextIso].map[i][j][k] = isos[0].map[i][j][cells.length - 1 - k];
                }
            }
        }
        nextIso++;

        // rotate the identity around the i axis
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[nextIso].map[i][j][k] = isos[0].map[i][k][cells.length - 1 - j];
                }
            }
        }
        nextIso++;

        // rotate the identity around the j axis
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[nextIso].map[i][j][k] = isos[0].map[k][j][cells.length - 1 - i];
                }
            }
        }
        nextIso++;

        //  eversion
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[nextIso].map[i][j][k] = isos[0].map[evt[i]][evt[j]][evt[k]];
                }
            }
        }
        nextIso++;

        // srm
        for (i = 0; i <  cells.length; i++) {
            for (j = 0; j <  cells.length; j++) {
                for (k = 0; k <  cells.length; k++) {
                    isos[nextIso].map[i][j][k] = isos[0].map[srm[i]][srm[j]][srm[k]];
                }
            }
        }
        nextIso++;

        int newIso = 6;
        int thisIso;

        for (i = 1; i < 6; i++) {
            for (j = 2; j < newIso; j++) {
                thisIso = newIso++;
                if (newIso > 193) {
                    System.out.println(String.format("There are too many (%d) isomorphisms", newIso));
                }
                composeIso(isos[i], isos[j], isos[thisIso]);
                for (k = 0; k + 1 < newIso; k++) {
                    if (compareIsoMaps(isos[thisIso], isos[k])) {
                        newIso--;
                        break;
                    }
                }
            }
        }
        assert (newIso == 192);
        //dumpAllIsos();
        return isos;
    }

    private void dumpAllIsos() {
        int i;
        for (i = 0; i < 192; i++) {
            for (int l = 0; l < cells.length; l++)
                for (int r = 0; r < cells.length; r++)
                    for (int c = 0; c < cells.length; c++) {
                        Point p = isos[i].map[l][r][c];
                        int i1 = (p.l * cells.length * cells.length) + (p.r * cells.length) + p.c;
                        System.out.print(String.format("%3d", i1));
                    }
            System.out.println();
        }
    }


//    public int getCanonicalId()
//    {
//        int i = 0;
//        for (int t = 0; t < 192; t++) {
//            if (compareIso(cells, t, i) > 0) {
//                i = t;
//            }
//        }
//        return i;
//    }


    private boolean compareIsoMaps(Isomorphism left, Isomorphism right) {
        for (int i = 0; i <  cells.length; i++)
            for (int j = 0; j <  cells.length; j++)
                for (int k = 0; k <  cells.length; k++)
                    if (left.map[i][j][k] != right.map[i][j][k])
                        return false;
        return true;
    }

    private void composeIso(Isomorphism left, Isomorphism right, Isomorphism result) {
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++)
                for (int k = 0; k < cells.length; k++) {
                    Point r = right.map[i][j][k];
                    result.map[i][j][k] = left.map[r.l][r.r][r.c];
                }
    }

    private int compareIso(Seed board[][][], int iso1, int iso2)
    {
        Isomorphism map1;
        Isomorphism map2;
        Seed s1, s2;

        if (iso1 == iso2) return 0;
        map1 = isos[iso1];
        map2 = isos [iso2];
        for (int i = 0; i <  cells.length; i++)
            for (int j = 0; j <  cells.length; j++)
                for (int k = 0; k <  cells.length; k++) {
                    Point p1 = map1.map[i][j][k];
                    Point p2 = map2.map[i][j][k];
                    s1 = board[p1.l][p1.r][p1.c];
                    s2 = board[p2.l][p2.r][p2.c];
                    if (s1.intValue() < s2.intValue()) {
                        return -1;
                    } else if (s1 == s2) {
                        continue;
                    } else {
                        return 1;
                    }
                }
        return 0;
    }

//    public Seed[][][] getIsoBoard(int iso)  {
//        Seed[][][] board = new Seed[cells.length][cells.length][cells.length];
//        Isomorphism isomorphism = isos[iso];
//        for (int i = 0; i < cells.length; i++)
//            for (int j = 0; j < cells.length; j++)
//                for (int k = 0; k < cells.length; k++) {
//                    Point p = isomorphism.map[i][j][k];
//                    board[i][j][k] = cells[p.l][p.r][p.c];
//                }
//        return board;
//    }
}

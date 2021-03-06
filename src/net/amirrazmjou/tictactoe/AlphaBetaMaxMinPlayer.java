package net.amirrazmjou.tictactoe;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Created by Amir Razmjou on 10/13/15.
 */

public class AlphaBetaMaxMinPlayer implements Player {
    private Seed seed;
    private Board board;
    public AlphaBetaMaxMinPlayer(Board board, Seed seed) {
        this.seed = seed;
        this.board = board;
    }

    long s;
    @Override
    public Point move() {
        long startTime = System.currentTimeMillis();

        Point nextMove = new Point();
        s = 0;
        move(seed, nextMove, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("States visited: " + s + " Cache Hits: " + cacheHit);
        board.setCell(nextMove, seed);
        cacheHit = 0;

        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("Execution time: " + duration / 1000);
        return nextMove;
    }

    private HashMap<String, Integer> cache = new HashMap<>();
    int cacheHit = 0;

    private int move(Seed currentSeed, Point nextMove, Integer alpha, Integer beta) {
        /*********************************************************/
        IsomorphicBoard3D isoBoard = null;
        String cannonicalPosition;
        if (IsomorphicBoard3D.class.isInstance(board)) {
            isoBoard = (IsomorphicBoard3D)board;
        }
         /*********************************************************/

        List<Point> availableMoves = board.getAvailableMoves();
        // TODO: Immediate win block / check for availability of at least 2 cells
        //////////////////////////////////////////////////////
        for (Seed thisSeed : new Seed[]{Seed.CROSS, Seed.NOUGHT}) {
            for (Point move : availableMoves) {
                board.setCell(move, thisSeed);
                Seed winner = board.winner();
                if (winner != null) {
                    // no matter we win or we lose
                    if (nextMove != null)
                        nextMove.set(move);
                    board.setCell(move, Seed.EMPTY);
                    if (winner == seed) {return board.getMaxMoves() + 1; }
                    if (winner == seed.opponent()) { return (board.getMaxMoves() + 1) * -1; }
                }
                board.setCell(move, Seed.EMPTY);
            }
        }
        //////////////////////////////////////////////////////

        Seed winner = board.winner();
        boolean maximizer = currentSeed == seed;
        int score = maximizer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        BiPredicate<Integer, Integer> predicate = (i, j) -> maximizer ? i > j : i < j;

        if (winner == seed) return board.getMaxMoves() + 1;
        if (winner == seed.opponent()) return (board.getMaxMoves() + 1) * -1;
        if (winner == null && availableMoves.isEmpty()) return 0;
        assert (winner == null);

        for (Point move : availableMoves) {
            s++;
            if (s % 10000000 == 0) {
                //System.out.print(s / 10000000 + " ");
                System.out.print(cacheHit + " ");
            }
            board.setCell(move, currentSeed);
            // T(n)= 128
            cannonicalPosition = isoBoard.getCanonicalPosition2();

            int newScore;
            if (cache.keySet().contains(cannonicalPosition)) {
                newScore = cache.get(cannonicalPosition);
                cacheHit++;
            }
            else {
                newScore = move(currentSeed.opponent(), null,
                        new Integer(alpha), new Integer(beta)); // we need a copy of alpha-beta
                if (availableMoves.size() > 10)
                    cache.put(cannonicalPosition, newScore);
            }
            if (predicate.test(newScore, score)) {
                if (nextMove != null)
                    nextMove.set(move);
                score = newScore;
                if (maximizer)
                    alpha = score;
                else beta = score;
                if (alpha >= beta) {
                    board.setCell(move, Seed.EMPTY);
                    return score;
                }
            }
            board.setCell(move, Seed.EMPTY);
        }
        return score;
    }
}

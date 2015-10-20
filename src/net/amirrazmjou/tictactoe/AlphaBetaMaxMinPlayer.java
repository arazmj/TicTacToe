package net.amirrazmjou.tictactoe;

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
        Point nextMove = new Point();
        s = 0;
        move(seed, nextMove, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("States visited: " + s);
        board.setCell(nextMove, seed);
        return nextMove;
    }

    private int move(Seed currentSeed, Point nextMove, Integer alpha, Integer beta) {
        List<Point> availableMoves = board.getAvailableMoves();
        ////////////////////////////
        Seed winner = board.winner();
//        for (Point availableMove : availableMoves) {
//            board.setCell(availableMove, Seed.CROSS);
//            if (board.winner()
//        }

        ////////////////////////////
        boolean maximizer = currentSeed == seed;
        int score = maximizer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        BiPredicate<Integer, Integer> predicate = (i, j) -> maximizer ? i > j : i < j;

        if (winner == seed) return board.getMaxMoves() + 1;
        if (winner == seed.opponent()) return (board.getMaxMoves() + 1) * -1;
        if (winner == null && availableMoves.isEmpty()) return 0;
        assert (winner == null);

        for (Point move : availableMoves) {
            s++;
            if (s % 10000000 == 0)
                System.out.print(s / 10000000 + " ");
            board.setCell(move, currentSeed);
            int newScore = move(currentSeed.opponent(), null,
                    new Integer(alpha), new Integer(beta));
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

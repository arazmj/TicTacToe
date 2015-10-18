package net.amirrazmjou.tictactoe;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * Created by Amir Razmjou on 10/12/15.
 */

public class SimpleMaxMinPlayer implements Player {
    private Seed seed;
    private Board board;
    public SimpleMaxMinPlayer(Board board, Seed seed) {
        this.seed = seed;
        this.board = board;
    }

    int s;
    @Override
    public Point move() {
        Point nextMove = new Point();
        s = 0;
        move(seed, nextMove);
        System.out.println("States visited: " + s);
        board.setCell(nextMove, seed);
        return nextMove;
    }

    private int move(Seed currentSeed, Point nextMove) {
        Seed winner = board.winner();
        List<Point> availableMoves = board.getAvailableMoves();
        boolean maximizer = currentSeed == seed;
        int score = maximizer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        BiPredicate<Integer, Integer> predicate = (i, j) -> maximizer ? i > j : i < j;

        if (winner == seed) return 10;
        if (winner == seed.opponent()) return -10;
        if (winner == null && availableMoves.isEmpty()) return 0;
        assert (winner == null);

        for (Point move : availableMoves) {
            s++;
            board.setCell(move, currentSeed);
            int newScore = move(currentSeed.opponent(), null);
            if (predicate.test(newScore, score)) {
                if (nextMove != null)
                    nextMove.set(move);
                score = newScore;
            }
            board.setCell(move, Seed.EMPTY);
        }
        return score;
    }
}

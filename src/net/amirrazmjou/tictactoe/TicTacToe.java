
package net.amirrazmjou.tictactoe;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) throws Exception {
        IsomorphicBoard3D board = new IsomorphicBoard3D(4);
        board.generateIsos();


//        putRandomMoves(board, 18);

        Scanner scanner = new Scanner(System.in);

        System.out.println(board);
        // (a) From keyboard input, assign your program to be player X or
        // player O (the opponent respectively be- comes player O or X).
        Player[] players = new Player[2];
        System.out.print("Which player do you like the AI become O/X (O)?");
        if (scanner.next().toUpperCase().trim().contains("X")) {
            System.out.println("AI player assigned to be X");
            players[0] = new AlphaBetaMaxMinPlayer(board, Seed.CROSS);
            players[1] = new HumanPlayer(board, Seed.NOUGHT);
        }
        else {
            System.out.println("AI player assigned to be O");
            players[0] = new HumanPlayer(board, Seed.CROSS);
            players[1] = new AlphaBetaMaxMinPlayer(board, Seed.NOUGHT);
        }

        System.out.println(board);

        int i = 0;
        do {
            players[i % 2].move();
            System.out.println(board);
            if (board.winner() != null || board.isFull())
                break;
            i++;
        } while (true);

        System.out.println("The winner is " + board.winner());

    }

    private static void putRandomMoves(Board3D board, int n) {
        Random random = new Random();
        random.setSeed(0);
        boolean b = true;
        for (int x = 0; x < n; x++) {
            List<Point> availableMoves = board.getAvailableMoves();
            int size = availableMoves.size();
            int r = random. nextInt(size);
            board.setCell(availableMoves.get(r), b ? Seed.NOUGHT : Seed.CROSS);
            if (board.winner() != null) {
                board.setCell(availableMoves.get(r), Seed.EMPTY);
            }
            else {
                b = !b;
            }
        }
    }
}


package net.amirrazmjou.tictactoe;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        IsomorphicBoard3D board = new IsomorphicBoard3D(4);

        board.setCell(new Point(0, 0, 0), Seed.CROSS);
        board.setCell(new Point(0, 0, 1), Seed.NOUGHT);
        board.setCell(new Point(0, 2, 0), Seed.CROSS);
        board.setCell(new Point(0, 1, 0), Seed.NOUGHT);
        board.setCell(new Point(0, 2, 3), Seed.CROSS);
        board.setCell(new Point(0, 2, 2), Seed.NOUGHT);
        board.setCell(new Point(0, 0, 2), Seed.CROSS);
        board.setCell(new Point(0, 0, 3), Seed.CROSS);
        board.setCell(new Point(0, 0, 3), Seed.NOUGHT);
        board.setCell(new Point(0, 3, 0), Seed.NOUGHT);
        board.setCell(new Point(1, 1, 1), Seed.CROSS);
        board.setCell(new Point(1, 1, 0), Seed.NOUGHT);
        board.setCell(new Point(1, 2, 2), Seed.CROSS);
        board.setCell(new Point(1, 2, 2), Seed.NOUGHT);
        board.setCell(new Point(1, 2, 3), Seed.CROSS);

        board.setCell(new Point(1, 0, 0), Seed.NOUGHT);
        board.setCell(new Point(1, 0, 2), Seed.CROSS);
        board.setCell(new Point(1, 0, 2), Seed.NOUGHT);
        board.setCell(new Point(1, 0, 3), Seed.CROSS);

        board.setCell(new Point(1, 2, 3), Seed.NOUGHT);
        board.setCell(new Point(1, 3, 2), Seed.CROSS);
        board.setCell(new Point(1, 3, 2), Seed.NOUGHT);
        board.setCell(new Point(2, 1, 1), Seed.CROSS);
        board.setCell(new Point(2, 1, 0), Seed.NOUGHT);
        board.setCell(new Point(2, 2, 2), Seed.CROSS);
        board.setCell(new Point(2, 1, 3), Seed.NOUGHT);
        board.setCell(new Point(2, 2, 1), Seed.CROSS);
        board.setCell(new Point(2, 2, 0), Seed.NOUGHT);
        board.setCell(new Point(2, 3, 1), Seed.CROSS);
        board.setCell(new Point(2, 3, 0), Seed.NOUGHT);
        board.setCell(new Point(2, 3, 1), Seed.CROSS);
        board.setCell(new Point(2, 3, 0), Seed.NOUGHT);
        board.setCell(new Point(3, 1, 1), Seed.CROSS);
        board.setCell(new Point(3, 3, 0), Seed.NOUGHT);
        board.setCell(new Point(3, 2, 2), Seed.CROSS);
        board.setCell(new Point(3, 2, 2), Seed.NOUGHT);
        board.setCell(new Point(3, 2, 3), Seed.CROSS);
        board.setCell(new Point(3, 2, 3), Seed.NOUGHT);
        board.setCell(new Point(3, 0, 0), Seed.CROSS);
        board.setCell(new Point(3, 0, 1), Seed.NOUGHT);
        board.setCell(new Point(3, 0, 2), Seed.CROSS);
        board.setCell(new Point(3, 0, 3), Seed.NOUGHT);
        board.setCell(new Point(3, 1, 2), Seed.CROSS);
        board.setCell(new Point(3, 1, 3), Seed.NOUGHT);

        Random random = new Random();
        for (int x = 0; x < 10; x++) {
            List<Point> availableMoves = board.getAvailableMoves();
            int size = availableMoves.size();
            int r = random. nextInt(size);
            board.setCell(availableMoves.get(r), Math.random() < 0.5 ? Seed.NOUGHT : Seed.CROSS);
            if (board.winner() != null) {
                board.setCell(availableMoves.get(r), Seed.EMPTY);
            }
        }

        System.out.println(board.getAvailableMoves().size());

        Scanner scanner = new Scanner(System.in);

//        System.out.println(board);
//
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
}

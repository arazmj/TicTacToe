
package net.amirrazmjou.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Board2D board = new Board2D(3);

        Scanner scanner = new Scanner(System.in);

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
            if (board.winner() != Seed.NONE || board.isFull())
                break;
            i++;
        } while (true);

        System.out.println("The winner is " + board.winner());
    }
}

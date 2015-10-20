
package net.amirrazmjou.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) throws Exception {
//        IsomorphicBoard3D board = new IsomorphicBoard3D(4);
        Board3D board = new Board3D(3);

//        board.generateIsos();
//
//        Random random = new Random();
//        random.setSeed(0);
//        boolean b = false;
//        for (int x = 0; x < 18; x++) {
//            List<Point> availableMoves = board.getAvailableMoves();
//            int size = availableMoves.size();
//            int r = random. nextInt(size);
//            board.setCell(availableMoves.get(r), b ? Seed.NOUGHT : Seed.CROSS);
//            if (board.winner() != null) {
//                board.setCell(availableMoves.get(r), Seed.EMPTY);
//
//            }
//            else {
//                b = !b;
//                int i = board.qb_find_canonical_iso();
//                System.out.println();
//                System.out.println();
//                System.out.println(board);
//                for (int j = 0; j < 192; j++)
//                System.out.println(i + ":" + board.getBoard(j).getIsoPosition());
//            }
//        }

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

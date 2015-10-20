package net.amirrazmjou.tictactoe;

import java.util.Scanner;

/**
 * Created by Amir Razmjou on 10/13/15.
 */
public class HumanPlayer implements Player {
    Board board;
    Seed seed;
    public HumanPlayer(Board board, Seed seed) {
        this.board = board;
        this.seed = seed;
    }

    @Override
    public Point move() {
        Scanner scanner = new Scanner(System.in);
        Point move;

        do {
            if (Board3D.class.isInstance(board)) {
                System.out.print("Enter level, row, and column your move: ");
                int l = scanner.nextInt();
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                move = new Point(l, r, c);
            }
            else {
                System.out.print("Enter row, and column of your move: ");
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                move = new Point(r, c);
            }
            if (board.getCell(move) == Seed.EMPTY)
                break;
            else
                System.out.println("Bad input the cell is already taken...");
        }  while (true);
        board.setCell(move, seed);
        return null;
    }
}

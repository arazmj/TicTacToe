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
                System.out.print("Enter x, y, z of your move: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int z = scanner.nextInt();
                move = new Point(x, y, z);
            }
            else {
                System.out.print("Enter x, y of your move: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                move = new Point(x, y);
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

package service;

import model.COLOR;
import model.Game;
import model.Pawn;

import java.util.Scanner;

public class Controller {
    static COLOR currentPlayer;
    static Game game;

    public static void main(String[] argv) {
        currentPlayer = COLOR.WHITE;
        game = new Game();

        System.out.println("Welcome to Everchess");
        game.board.printBoard();
        while(!game.isOver) {
            playMove();
        }
    }

    private static void playMove() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nCurrent Player: " + currentPlayer );
        System.out.println("Chose row:");
        int row  = scan.nextInt();
        System.out.println("Chose column:");
        char column = scan.next().charAt(0);
        boolean result = game.move(currentPlayer, row, column);
        if (result) {
            if (currentPlayer == COLOR.WHITE) {
                currentPlayer = COLOR.BLACK;
            } else {
                currentPlayer = COLOR.WHITE;
            }
            game.board.printBoard();
        }
    }
}

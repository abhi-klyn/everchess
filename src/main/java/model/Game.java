package model;

import Exceptions.InvalidMove;

public class Game {
    public Board board;
    public boolean isOver;

    public Game() {
        board = new Board();
        isOver = false;
    }

    public boolean move(COLOR player, int row, char column) {
        if (row <= 0 || row > 8 || column < 'a' || column > 'h') {
            System.out.println("Error: Invalid location");
            return false;
        }

        Space space = board.spaces.get(row).get(column);

        if (space.isEmpty) {
            System.out.println("Error: No pawn present");
            return false;
        }

        if (space.pawn.color != player) {
            System.out.println("Error: Wrong player");
            return false;
        }

        int destinationRow = (player == COLOR.WHITE)? row + 1 : row - 1;
        Space destinationSpace = board.spaces.get(destinationRow).get(column);

        if (!destinationSpace.isEmpty) {
            System.out.println("Error: Destination move Blocked");
            return false;
        }

        destinationSpace.assignPawn(space.pawn);
        space.pawn = null;
        space.isEmpty = true;

        if ((destinationRow == 1 && player == COLOR.BLACK) || (destinationRow == 8 && player == COLOR.WHITE)) {
            isOver = true;
            System.out.println(player + " WON!!");
            return true;
        }
        checkKills(player, destinationRow, column);
        return true;
    }

    private void checkKills(COLOR player, int row, char column) {
        int increment = (player == COLOR.WHITE) ? 1 : -1;
        int newRow = row + increment;
        if (newRow < 1 || newRow > 8) {
            return;
        }
        char diagonalRight = column, diagonalLeft = column;
        diagonalRight++;
        diagonalLeft--;

        if (diagonalLeft >= 'a' && diagonalLeft <= 'h') {
            replacePawn(player, newRow, diagonalLeft, row, column);
        } else if (diagonalRight >= 'a' && diagonalRight <= 'h') {
            replacePawn(player, newRow, diagonalRight, row, column);
        }

    }

    private void replacePawn(COLOR player, int fromRow, char fromColumn, int toRow, char toColumn) {
        Space attackerSpace = board.spaces.get(fromRow).get(fromColumn);
        if (attackerSpace.isEmpty) {
            return;
        }

        Space victimSpace = board.spaces.get(toRow).get(toColumn);
        victimSpace.pawn = null;
        victimSpace.pawn = attackerSpace.pawn;
        victimSpace.isEmpty = false;
        victimSpace.pawn.color = (player == COLOR.WHITE) ?COLOR.BLACK : COLOR.WHITE;
        attackerSpace.isEmpty = true;
        attackerSpace.pawn = null;
    }
}

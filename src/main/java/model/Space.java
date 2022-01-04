package model;

public class Space {
    public Pawn pawn;
    public boolean isEmpty;
    public int row;
    public char column;

    public Space(int row, char column) {
        this.row = row;
        this.column = column;
        pawn = null;
        isEmpty = true;
    }

    public Space() {
        pawn = null;
        isEmpty = true;
    }

    public boolean assignPawn(Pawn pawn) {
        if (!this.isEmpty) {
            return false;
        }
        this.pawn = pawn;
        this.isEmpty = false;
        return true;
    }

}

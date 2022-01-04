package model;

import java.util.HashMap;
import java.util.Map;

public class Board {

    final int SIZE = 8;

    public Map<Integer, Map<Character, Space>> spaces;

    public Board() {
        this.spaces = new HashMap<>();

        for(int i = 1; i <= SIZE; i++) {
            spaces.put(i, new HashMap<>());
            for(char c = 'a'; c <= 'h'; c++) {
                spaces.get(i).put(c, new Space(i, c));
            }
        }

        for(char c = 'a'; c <= 'h'; c++) {
            Space s = spaces.get(2).get(c);
            s.assignPawn(new Pawn(COLOR.WHITE));
            s = spaces.get(7).get(c);
            s.assignPawn(new Pawn(COLOR.BLACK));
        }
    }

    public void printBoard() {
        for(int i = SIZE; i >= 1; i--) {
            System.out.print(i + "\t");
            for(char c = 'a'; c <= 'h'; c++) {
                Space s = spaces.get(i).get(c);
                if (s.isEmpty) {
                    System.out.print("-");
                } else if (s.pawn.color == COLOR.WHITE) {
                     System.out.print("W");
                } else {
                     System.out.print("B");
                }
                System.out.print("\t");
            }
            System.out.println("");
        }
        System.out.print(" \t");
        for(char c = 'a'; c <= 'h'; c++) {
            System.out.print(c + "\t");
        }
    }


}

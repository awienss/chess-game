// written by Aidan Wiens, wiens056
// the Piece class creates a chess piece, using Unicode characters to classify piece type

import java.util.Scanner;

public class Piece {

    // variables

     public char character;
     private int row;
     private int col;
     private boolean isBlack;

     // Piece constructor

     public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

//    TODO:
//     Determines if moving this piece is legal.
//     param board     The current state of the board.
//     param endRow    The destination row of the move.
//     param endCol    The destination column of the move.
//     return if the piece can legally move to the provided destination on the board.

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

//    TODO:
//     Sets the position of the piece.
//     param row   The row to move the piece to.
//     param col   The column to move the piece to.
//
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

//    TODO:
//     Return the color of the piece.

    public boolean getIsBlack() {
        if (isBlack == true) {
            return true;
        }
        return false;
    }

    //    TODO:
    //     Return the row of the piece.
    public int getRow() {
         return row;
    }

//    TODO:
//     Handle promotion of a pawn.
//     param row Current row of the pawn
//     param isBlack Color of the pawn

    public void promotePawn(int row, boolean isBlack) {
        Scanner pawnScan = new Scanner(System.in);
        System.out.println("What would you like to upgrade to? Enter the name of the piece with no spaces.");
        String input = pawnScan.nextLine().toLowerCase();
        if (input.equals("queen")) {
            if (isBlack == true) {
                this.character = '\u265B';
            }
            else {
                this.character = '\u2655';
            }
        }
        else if (input.equals("rook")) {
            if (isBlack == true) {
                this.character = '\u265C';
            }
            else {
                this.character = '\u2656';
            }
        }
        else if (input.equals("bishop")) {
            if (isBlack == true) {
                this.character = '\u265D';
            }
            else {
                this.character = '\u2657';
            }
        }
        else if (input.equals("knight")) {
            if (isBlack == true) {
                this.character = '\u265E';
            }
            else {
                this.character = '\u2658';
            }
        }
    }

//    TODO:
//     * Returns a string representation of the piece.

    public String toString() {
        return String.valueOf(character);
    }
}

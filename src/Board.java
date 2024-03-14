// written by Aidan Wiens, wiens056
// the Board class implements a chess board (2D array of type Piece) and several methods to enhance gameplay
// original comments in file were kept in place

import java.lang.Math;

public class Board {

    // variables

    public Piece[][] board;
    public static int turnTracker = 2;

    //TODO:
    // Construct an object of type Board using given arguments.

    public Board() {
        this.board = new Piece[8][8];
    }

    // accessor methods

    //TODO:
    // Return the Piece object stored at a given row and column.

    public Piece getPiece(int row, int col) {
        return this.board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.

    public void setPiece(int row, int col, Piece piece) {
        this.board[row][col] = piece;
    }

    // game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is siezable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // Your Game class should not directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.

    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (startRow > 7 || startCol > 7 || startRow < 0 || startCol < 0) {
            return false;
        }
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol) == true) {
            Piece temp = board[startRow][startCol];
            board[startRow][startCol] = null;
            board[endRow][endCol] = temp;
            temp.setPosition(endRow, endCol);
            if (Board.turnTracker % 2 == 0) {
                if (board[endRow][endCol].getRow() == 0 && board[endRow][endCol].character == '\u2659') {
                    board[endRow][endCol].promotePawn(0, false);
                }
            }
            else {
                if (board[endRow][endCol].getRow() == 7 && board[endRow][endCol].character == '\u265F') {
                    board[endRow][endCol].promotePawn(7, true);
                }
            }
            turnTracker++;
            return true;
        }
        return false;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.

    public boolean isGameOver() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].character == '\u2654') {
                        count++;
                    }
                    if (board[i][j].character == '\u265a') {
                        count++;
                    }
                }
            }
        }
        if (count == 2) {
            return false;
        }
        return true;
    }

    //TODO:
    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.

    public void clear() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = null;
            }
        }
    }

    // movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)

    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 0 || startRow >= 8 || startCol < 0 || startCol >= 8 ||
                endRow < 0 || endRow >= 8 || endCol < 0 || endCol >= 8) {
            return false;
        }
        if (board[startRow][startCol] == null) {
            return false;
        }
        if (board[startRow][startCol].getIsBlack() != isBlack) {
            return false;
        }
        if (board[endRow][endCol] != null && board[endRow][endCol].getIsBlack() == isBlack) {
            return false;
        }
        return true;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other.

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return true;
        }
        if (endRow == startRow + 1 && endCol == startCol || endRow == startRow - 1 && endCol == startCol ||
                endRow == startRow && endCol == startCol + 1 || endRow == startRow && endCol == startCol - 1 ||
                endRow == startRow + 1 && endCol == startCol + 1 || endRow == startRow - 1 && endCol == startCol - 1 ||
                endRow == startRow - 1 && endCol == startCol + 1 || endRow == startRow + 1 && endCol == startCol - 1) {
            return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }
        for (int i = 1; i < Math.abs(startCol - endCol); i++) {
            if (startCol > endCol) {
                if (board[startRow][startCol - i] != null) {
                    return false;
                }
            }
            if (startCol < endCol) {
                if (board[startRow][startCol + i] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.

    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }
        for (int i = 1; i < Math.abs(startRow - endRow); i++) {
            if (startRow > endRow) {
                if (board[startRow - i][startCol] != null) {
                    return false;
                }
            }
            if (startRow < endRow) {
                if (board[startRow + i][startCol] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return true;
        }
        if (startRow == endRow || startCol == endCol) {
            return false;
        }
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        if (rowDiff != colDiff) {
            return false;
        }
        int rowStep;
        int colStep;
        if (endRow > startRow) { rowStep = 1; } else { rowStep = -1; }
        if (endCol > startCol) { colStep = 1; } else { colStep = -1; }
        int row = startRow + rowStep;
        int col = startCol + colStep;
        while (row != endRow && col != endCol) {
            if (board[row][col] != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }
}

// written by Aidan Wiens, wiens056
// the Queen class implements the queen piece and uses Board class methods to verify piece movement

public class Queen {

    // variables

    private int row;
    private int col;
    private boolean isBlack;

    // Queen constructor

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // isMoveLegal for queen piece

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack) == true &&
                board.verifyVertical(this.row, this.col, endRow, endCol) == true ||
                board.verifyHorizontal(this.row, this.col, endRow, endCol) == true ||
                board.verifyDiagonal(this.row, this.col, endRow, endCol) == true) {
            return true;
        }
        System.out.println("Illegal move, try again.");
        return false;
    }
}

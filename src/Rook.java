// written by Aidan Wiens, wiens056
// the Rook class implements the rook piece and uses Board class methods to verify piece movement

public class Rook {

    // variables

    private int row;
    private int col;
    private boolean isBlack;

    // Rook constructor

    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // isMoveLegal for rook piece

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack) &&
                board.verifyVertical(this.row, this.col, endRow, endCol) == true ||
                board.verifyHorizontal(this.row, this.col, endRow, endCol) == true) {
            return true;
        }
        System.out.println("Illegal move, try again.");
        return false;
    }
}

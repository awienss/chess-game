// written by Aidan Wiens, wiens056
// the Bishop class implements the bishop piece and uses Board class methods to verify piece movement

public class Bishop {

    // variables

    private int row;
    private int col;
    private boolean isBlack;

    // Bishop constructor

    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // isMoveLegal for bishop piece

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        System.out.println(this.row);
        System.out.println(this.col);
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack) &&
                board.verifyDiagonal(this.row, this.col, endRow, endCol) == true) {
            return true;
        }
        System.out.println("Illegal move, try again.");
        return false;
    }
}

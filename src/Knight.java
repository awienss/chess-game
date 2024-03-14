// written by Aidan Wiens, wiens056
// the Kinght class implements the knight piece and uses Board class methods to verify piece movement

public class Knight {

    // variables

    private int row;
    private int col;
    private boolean isBlack;

    // Knight constructor

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // isMoveLegal for knight piece

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            int rowDiff = Math.abs(this.row - endRow);
            int colDiff = Math.abs(this.col - endCol);
            if (rowDiff == 2 && colDiff == 1 || rowDiff == 1 && colDiff == 2) {
                return true;
            }
        }
        System.out.println("Illegal move, try again.");
        return false;
    }
}

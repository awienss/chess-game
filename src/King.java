// written by Aidan Wiens, wiens056
// the King class implements the king piece and uses Board class methods to verify piece movement

public class King {

    // variables

    private int row;
    private int col;
    private boolean isBlack;

    // King constructor

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // isMoveLegal for king piece

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack) &&
                board.verifyAdjacent(this.row, this.col, endRow, endCol) == true) {
            return true;
        }
        System.out.println("Illegal move, try again.");
        return false;
    }
}

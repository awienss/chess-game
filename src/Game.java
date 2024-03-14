// written by Aidan Wiens, wiens056
// the Game class contains the main method, creating an interactive chess game for the player

import java.util.Scanner;

public class Game {
    public static void main(String[]args) {

        // board creation

        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);

        // interactive terminal

        Scanner scan = new Scanner(System.in);
        while (myBoard.isGameOver() == false) {
            if (Board.turnTracker % 2 == 0) {
                System.out.println("Board:\n" + myBoard);
                System.out.println("It is white's turn.\nWhat is your move? " +
                        "\nInput must be a four digit number in the format [start row][start col][end row][end col] with no spaces.\nEnter q to quit.");
            }
            else {
                System.out.println("Board:\n" + myBoard);
                System.out.println("It is black's turn.\nWhat is your move? " +
                        "\nInput must be a four digit number in the format [start row][start col][end row][end col] with no spaces.\nEnter q to quit.");
            }
            int[] playerMove = new int[4];
            String move = scan.nextLine();
            if (move.equals("q")) {
                break;
            }
            else {
                int convertedMove = Integer.parseInt(move);
                for (int i = playerMove.length - 1; i >= 0; i--) {
                    playerMove[i] = convertedMove % 10;
                    convertedMove /= 10;
                }
                int sR = playerMove[0];
                int sC = playerMove[1];
                int eR = playerMove[2];
                int eC = playerMove[3];
                myBoard.movePiece(sR, sC, eR, eC);
            }
        }
        if (myBoard.isGameOver() == false) {
            if (Board.turnTracker % 2 == 0) {
                System.out.println("White quit the match. Black wins!");
            }
            else {
                System.out.println("Black quit the match. White wins!");
            }
        }
        else {
            if (Board.turnTracker % 2 == 0) {
                System.out.println("White's king has been captured. Black wins!\nThanks for playing!");
            }
            else {
                System.out.println("Black's king has been captured. White wins!\nThanks for playing!");
            }
        }
    }
}

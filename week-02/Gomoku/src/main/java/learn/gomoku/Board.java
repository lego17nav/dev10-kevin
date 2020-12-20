package learn.gomoku;
import java.util.Arrays;

public class Board {

    private int width = 15;
    private char[][] board = new char[15][15];


    public void displayBoard(){
        for(int i = 0; i < board.length; i++) {
            System.out.print(i + " || ");
        }
        for(int row = 0; row < board.length; row++) {
            System.out.println(row);
            for(int col = 0; col < board[row].length; col ++) {
                System.out.println(board[row][col]);
            }
        }
    }

}

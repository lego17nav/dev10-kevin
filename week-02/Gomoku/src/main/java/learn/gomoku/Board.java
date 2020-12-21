package learn.gomoku;
import java.util.Arrays;

public class Board {

    private int width = 15;
    private char[][] board = new char[15][15];


    public void displayBoard(){
        Reading reader = new Reading();
        String rowFormat = "%-3s";
        System.out.print("   ");
        for(int x = 0; x < board.length; x++) {
            System.out.printf(rowFormat,reader.spitOut(x));
        }
        System.out.println();
        for(int row = 0; row < board.length; row++) {
            if(reader.spitOut(row) > 9){
                System.out.printf("%d ",reader.spitOut(row));
            } else {
                System.out.printf("0%d ", reader.spitOut(row));
            }
            for(int col = 0; col < board[row].length; col ++) {
                if(board[row][col] == '\u0000') {
                    System.out.printf(rowFormat,"-");
                }else {
                    System.out.printf(rowFormat,board[row][col]);
                }
            } System.out.println();
        }
    }

    public void fillBoard(int col,int row, boolean isBlack) {
        if(isBlack) {
            board[row][col] = 'X';
        } else {
            board[row][col] = 'O';
        }
    }

}

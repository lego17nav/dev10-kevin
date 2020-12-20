package learn.gomoku.game;

import learn.gomoku.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Gomoku {

 // Basic Game setup
    public static final int WIDTH = 15;

    private final Player playerOne;
    private final Player playerTwo;

    // 2D Array of width * width
    private final char[][] board = new char[WIDTH][WIDTH];

    private ArrayList<Stone> stones = new ArrayList<>();

    // ?Boolean which might end a turn or game?
    private boolean over;
    private Player current;
    private Player winner;
    private boolean blacksTurn = true;

    public List<Stone> getStones() {
        return new ArrayList<>(stones);
    }

    public boolean isOver() {
        return over;
    }

    public Player getCurrent() {
        return current;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isBlacksTurn() {
        return blacksTurn;
    }

    //Constructor class
    public Gomoku(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        if (Math.random() < 0.5) {
            current = playerOne;
        } else {
            current = playerTwo;
        }
    }

    public Result place(Stone stone) {
        //If is Over games ends how?
        if (isOver()) {
            return new Result("Game is over.");
        }
        // If move not valid reloop
        if (!isValid(stone)) {
            return new Result("Stone is off the board.");
        }
        //
        if (blacksTurn != stone.isBlack()) {
            return new Result("Wrong player.");
        }
        // CHeck if index is equal zero if not it's not a viable move
        if (board[stone.getRow()][stone.getColumn()] != 0) {
            return new Result("Duplicate move.");
        }
        // If blacks turn make b
        // Add stone object to list with ? Possibly with data ? Row/Column or Type.
        board[stone.getRow()][stone.getColumn()] = blacksTurn ? 'B' : 'W';
        stones.add(stone);
        // ? Not sure how the winning is dicated basd on the win isWin method.
        if (isWin(stone)) {
            over = true;
            winner = current;
            return new Result(current.getName() + " wins.", true);
        }

        if (stones.size() == WIDTH * WIDTH) {
            over = true;
            return new Result("Game ends in a draw.", true);
        }

        blacksTurn = !blacksTurn;
        swap();
        return new Result(null, true);
    }

    public void swap() {
        current = current == playerOne ? playerTwo : playerOne;
    }

    private boolean isValid(Stone stone) {
        return stone != null
                && stone.getRow() >= 0 && stone.getRow() < WIDTH
                && stone.getColumn() >= 0 && stone.getColumn() < WIDTH;
    }

    private boolean isWin(Stone stone) {
        char symbol = board[stone.getRow()][stone.getColumn()];
        return isHorizontalWin(stone.getRow(), stone.getColumn(), symbol)
                || isVerticalWin(stone.getRow(), stone.getColumn(), symbol)
                || isDiagonalDownWin(stone.getRow(), stone.getColumn(), symbol)
                || isDiagonalUpWin(stone.getRow(), stone.getColumn(), symbol);
    }

    private boolean isHorizontalWin(int row, int column, char symbol) {
        return count(row, column, 1, 0, symbol)
                + count(row, column, -1, 0, symbol) == 4;
    }

    private boolean isVerticalWin(int row, int column, char symbol) {
        return count(row, column, 0, 1, symbol)
                + count(row, column, 0, -1, symbol) == 4;
    }

    private boolean isDiagonalDownWin(int row, int column, char symbol) {
        return count(row, column, 1, 1, symbol)
                + count(row, column, -1, -1, symbol) == 4;
    }

    private boolean isDiagonalUpWin(int row, int column, char symbol) {
        return count(row, column, -1, 1, symbol)
                + count(row, column, 1, -1, symbol) == 4;
    }

    // Go through each index 1 index at a time
    // deltaRow & deltaCol if the step is horizontal/vertical/diagonal.

    private int count(int row, int col, int deltaRow, int deltaCol, char symbol) {

        int result = 0;
        int r = row + deltaRow;
        int c = col + deltaCol;

        while (r >= 0 && r < WIDTH && c >= 0 && c < WIDTH && board[r][c] == symbol) {
            result++;
            r += deltaRow;
            c += deltaCol;
        }

        return result;
    }

}

package learn.gomoku;

import learn.gomoku.Prompt;

import learn.gomoku.game.Stone;
import learn.gomoku.players.Player;
import learn.gomoku.game.Gomoku;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;


public class App {

    public static void main(String[] args) {

        boolean gameExit = false;

        while (!gameExit) {
            Player[] players = new Player[2];
            Prompt gameStart = new Prompt();

            Board board = new Board();
            gameStart.printWelcome();
            gameStart.playerPrompt(players);

            Gomoku game = new Gomoku(players[0], players[1]);

            while (!game.isOver()) {
                board.displayBoard();
                Stone stone = gameStart.movePrompt(game.isBlacksTurn(), game.getCurrent(), game.getStones());
                System.out.printf("%s has placed a move in Row:%d and Col:%d\n", game.getCurrent().getName(),
                        stone.getRow() + 1, stone.getColumn() + 1);
                if (!game.place(stone).isSuccess()) {
                    System.out.println(game.place(stone).getMessage());
                } else {
                    board.fillBoard(stone.getColumn(), stone.getRow(), stone.isBlack());
                    board.displayBoard();
                }
            }

            System.out.println("The winner of the game is " + game.getWinner().getName());
            System.out.println("Want to play again?[y/n]");
            Scanner console = new Scanner(System.in);
            String yesNo = console.nextLine();

            if (yesNo.equalsIgnoreCase("n"))
            {
                System.out.println("Goodbye");
                gameExit = true;
            }
        }

    }

    }


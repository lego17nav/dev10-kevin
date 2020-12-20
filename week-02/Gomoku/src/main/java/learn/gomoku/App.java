package learn.gomoku;

import learn.gomoku.Prompt;

import learn.gomoku.game.Stone;
import learn.gomoku.players.Player;
import learn.gomoku.game.Gomoku;


public class App {

    public static void main(String[] args) {

        Player[] players = new Player[2];
        Prompt gameStart = new Prompt();

        Board board = new Board();
        board.displayBoard();

        gameStart.printWelcome();
        gameStart.playerPrompt(players);

        Gomoku game = new Gomoku(players[0],players[1]);

        while(!game.isOver()) {

            System.out.printf("It's %s's turn \n", game.getCurrent().getName());
            Stone stone = gameStart.movePrompt(game.isBlacksTurn());
            if(!game.place(stone).isSuccess()) {
                System.out.println(game.place(stone).getMessage());
            } else {
                for(Stone row: game.getStones()){
                    System.out.println(row);
                }
            }

        }



    }
}

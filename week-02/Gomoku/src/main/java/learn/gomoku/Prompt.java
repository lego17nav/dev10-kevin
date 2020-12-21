package learn.gomoku;
import learn.gomoku.game.Stone;
import learn.gomoku.players.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prompt {

    java.util.Scanner console = new Scanner(System.in);
    Reading reader = new Reading();


    public void printWelcome() {

        System.out.println("Welcome to Gomoku\n");
        System.out.println("====================");

    }

    public void playerPrompt(Player[] players) {

        for (int i = 1; i != 3; i++) {

            System.out.printf("Player %d is:\n1. Human\n2. Random\nSelect [1-2]", i);

            int choice = console.nextInt();
            console.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Please enter your name");
                    String name = console.nextLine();
                    Player player = new HumanPlayer(name);
                    players[i - 1] = player;
                    break;
                case 2:
                    System.out.println("AI Player is joining....");
                    Player randomPlayer = new RandomPlayer();
                    System.out.printf("%s has joined\n", randomPlayer.getName());
                    players[i - 1] = randomPlayer;
                    break;

            }

        }

    }

    public Stone movePrompt(boolean isBlack, Player player, List<Stone> stones) {

        System.out.printf("It's %s's turn\n", player.getName());
        if (player instanceof HumanPlayer) {
            System.out.println("Please enter a row");
            int row = reader.readIn(console.nextInt());
            console.nextLine();
            System.out.println("Please enter a column");
            int column = reader.readIn(console.nextInt());
            console.nextLine();
            Stone stone = new Stone(row, column, isBlack);
            return stone;
        }

        return player.generateMove(stones);
    }





}
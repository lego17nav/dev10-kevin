import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise06 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Use a loop to find the game in `games` that can be played by the most players.
        // 2. Print the game. (Expected: "Ultimate Werewolf...")
        int highestInt = 0;
        String gameName = "";
        for(BoardGame game: games) {
            if(game.getMaxPlayers() > highestInt) {
                highestInt = game.getMaxPlayers();
                gameName = game.getName();
            }
        }
        System.out.printf("%s:%d",gameName,highestInt);
    }
}


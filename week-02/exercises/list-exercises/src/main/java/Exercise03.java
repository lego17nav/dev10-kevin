import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise03 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();
        games.add(new BoardGame("Backgammon", 2,2,"RockGame"));
        games.add(new BoardGame("Chess",2,2,"Mind"));
        games.add(new BoardGame("Yugioh",2,2,"Card"));
        Exercise02.printAll(games);
        // 1. Add three new games to `games` with the `add` method.
        // 2. Print `games` after each add.
    }
}

import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class Exercise04 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame>.
        // 2. Add three BoardGames to the new list.
        // 3. Print the new list.
        // 4. Add items in the new list to `games` with the `addAll` method.
        // 5. Print `games`.
        ArrayList<BoardGame> newGames = new ArrayList<>(Arrays.asList(
                new BoardGame("Pokemon",2,2,"VIdeoGame" ),
                new BoardGame("Final Fantasy",2,2,"VideoGame"),
                new BoardGame("Snakes",2,4,"BoardGames")
                ));
        games.addAll(newGames);
        Exercise02.printAll(games);

        }
    }


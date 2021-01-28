package learn.labgame.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Locale;
import java.util.Random;

@RestController
public class LabGameController {

    /*@GetMapping("/random")
    public int getRandomNumber() {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 50;
        return numberToGuess;
    }

    int numberToGuess = getRandomNumber();
    @GetMapping("/check/random/{guess}")
    public String checkRandom(@PathVariable int guess) {
        if (guess == numberToGuess) {
            return "You got it!";
        } else if (guess < numberToGuess) {
            return  "Guess a higher number.";
        } else if (guess > numberToGuess) {
            return "Guess a lower number.";
        }
    }*/
    Random rand = new Random();
    int computerMove = rand.nextInt(4) + 1;

    @PostMapping("/reset")
    public void resetGame() {
        computerMove = rand.nextInt(4) + 1;
    }

    @GetMapping("/move/{move}")
    public String checkMove(@PathVariable String move) {

        switch (move.toLowerCase()) {
            case "rock":
                if (computerMove == 1) {
                    return "It's a tie";
                } else if (computerMove == 2) {
                    return "Computer move: paper. You lose!";
                } else if (computerMove == 3) {
                    return "Computer move: Scissors. You win!";
                }
                break;
            case "paper":
                if (computerMove == 2) {
                    return "You win!";
                } else if (computerMove == 2) {
                    return "Computer move: paper. Tie!";
                } else if (computerMove == 3) {
                    return "Computer move: Scissors. You Lose!";
                }
                break;
            case "scissors":
                if (computerMove == 3) {
                    return "You lose!";
                } else if (computerMove == 2) {
                    return "Computer move: paper. You win!";
                } else if (computerMove == 3) {
                    return "Computer move: Scissors. It's a tie!";
                }
        }
        return null;
    }
}

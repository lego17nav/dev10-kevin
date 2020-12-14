import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        // BALLOON GAME
        Scanner console = new Scanner(System.in);
        boolean balloonGame = false;
        // 1. Instantiate three balloons of different colors.
        Balloon one = new Balloon("Red");
        Balloon two = new Balloon("Yellow");
        Balloon three = new Balloon("Blue");

        do {

            System.out.println("Inflate? [y/n]: ");
            if (console.nextLine().equalsIgnoreCase("y")) {
                // 2. If the user confirms an inflate, inflate each balloon.
                one.inflate();
                two.inflate();
                three.inflate();
            }
            if(one.isExploded()|| two.isExploded()|| three.isExploded()) {
                balloonGame = true;
            }
            // 3. When one or more balloons explode, stop the loop.
        } while (!balloonGame);
        if(one.isExploded()) {
            System.out.print(one.getColor());
        }
        if(two.isExploded()) {
            System.out.print(two.getColor());
        }
        if(two.isExploded()) {
            System.out.print(two.getColor());
        }
        // 4. Print the color of the winners (balloons that exploded).
    }
}

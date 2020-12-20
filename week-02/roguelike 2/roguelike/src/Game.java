import java.util.Random;
import java.util.Scanner;

public class Game {

    // constants
    private static int WIDTH;
    private final static String WALL_CHARACTER = "M";
    private final static String EMPTY_CHARACTER = " ";

    private final Scanner console = new Scanner(System.in);
    private Hero hero;
    private Treasure treasure1;
    private Treasure treasure2;
    private Trap trap1;
    private boolean isOver;

    public void run() {
        setUp();
        while (!isOver) {
            printWorld();
            move();
        }
        printWorld();
    }

    private void setUp() {
        System.out.print("How big is the world");
        WIDTH = console.nextInt();

        System.out.print("What is the name of your hero?: ");
        String name = console.nextLine();

        System.out.print("What symbol do you want to use for your hero?: ");
        String toChar = console.nextLine();
        char symbol = toChar.charAt(0);

        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);

        hero = new Hero(name, x, y, symbol);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY());

        treasure1 = new Treasure(x, y);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY() && x == treasure1.getX() && y == treasure1.getX());

        treasure2 = new Treasure(x, y);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY() && x == treasure1.getX() && y == treasure1.getX());

        trap1 = new Trap(x, y);
    }

    private void printWorld() {
        // top wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));

        for (int row = 0; row < WIDTH; row++) {
            // left wall border
            System.out.print(WALL_CHARACTER);
            for (int col = 0; col < WIDTH; col++) {
                if (row == hero.getY
                        () && col == hero.getX()) {
                    System.out.print(hero.getSymbol());
                } else if (row == treasure1.getY() && col == treasure1.getX()) {
                    System.out.print(treasure1.getSymbol());
                } else if (row == treasure2.getY() && col == treasure2.getX()) {
                    System.out.print(treasure2.getSymbol());
                } else {
                    System.out.print(EMPTY_CHARACTER);
                }
            }

            // right wall border
            System.out.println(WALL_CHARACTER);
        }

        // bottom wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));
    }

    private void move() {
        boolean treasuresFound = false;


            System.out.print(hero.getName() + ", move [WASD]: ");
            String move = console.nextLine().trim().toUpperCase();

            if (move.length() != 1) {
                return;
            }

            switch (move.charAt(0)) {
                case 'W':
                    hero.moveUp();
                    break;
                case 'A':
                    hero.moveLeft();
                    break;
                case 'S':
                    hero.moveDown();
                    break;
                case 'D':
                    hero.moveRight();
                    break;
            }

        int countTreasures = 0;
        if (hero.getX() == treasure1.getX() && hero.getY() == treasure1.getY()) {
            countTreasures++;
        } else if (hero.getX() == treasure2.getX() && hero.getY() == treasure2.getY()) {
            countTreasures++;
        }

        if (countTreasures == 2) {
            //treasuresFound = true;
            System.out.println(hero.getName() + " found the both treasures! You win.");
            isOver = true;
        } else if (hero.getX() < 0 || hero.getX() >= WIDTH
                || hero.getY() < 0 || hero.getY() >= WIDTH) {
            System.out.println(hero.getName() + " touched lava! You lose.");
            isOver = true;
        }



//        if () {
//
//        } else if (hero.getX() == treasure1.getX() && hero.getY() == treasure1.getY()) {
//            isOver = false;
//        } else if (hero.getX() == )
//        //
    }
}

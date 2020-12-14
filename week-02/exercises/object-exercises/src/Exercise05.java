import java.util.Scanner;

public class Exercise05 {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        Musician[] musicians = new Musician[5];
        musicians[0] = new Musician("Frank Ocean", 10);

        // 1. Use a loop to populate the `musicians` array with your top 5 favorite musicians.
        // (Replace Frank Ocean.)
        // Create musicians from user input. (See Exercise04.)
        for (int i = 0; i < musicians.length; i++) {
            String name = console.nextLine();
            String rating = console.nextLine();
            int ratingInt = Integer.parseInt(rating);
            musicians[i] = new Musician(name,ratingInt);
        }

        // 2. Use a second loop to print details about each musician.
        for (int i = 0; i < musicians.length; i++) {
            System.out.println(musicians[i].getName() + ":" + musicians[i].getRating());
        }
    }
}

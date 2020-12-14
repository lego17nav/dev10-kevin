import java.util.Scanner;

public class Exercise04 {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        // 1. Add an empty constructor to Musician.
        // 2. Uncomment the code below and make sure it runs.
        boolean exitLoop = false;
        while(!exitLoop) {
            Musician m = new Musician();
            System.out.print("Musician name:");
            String inName = console.nextLine();
            if(inName.equalsIgnoreCase("end")) {
                break;
            }
            m.setName(inName);
            System.out.print("Musician rating:");
            int rating = Integer.parseInt(console.nextLine());
            m.setRating(rating);
            System.out.printf("%s: %s%n", m.getName(), m.getRating());
        }
        // 3. Add a loop. The exercise should ask the user for musicians and print
        // them out until the user types "end".
    }
}

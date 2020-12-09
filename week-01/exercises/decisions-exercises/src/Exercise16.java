import java.util.Scanner;
public class Exercise16 {

    public static void main(String[] args) {
        // 1. Display the following menu and collect an integer choice from the user.
        // (See Exercise14 for a menu example.)
        //
        // Menu
        // 1. Print the name of an animal.
        // 2. Print the name of a state.
        // 3. Print the name of a beetle.
        // 4. Print the name of a mineral.
        // Select [1-4]:
        //
        // 2. Use a switch to cover cases 1-4 as well as a default.
        // For 1-4, print an animal, state, beetle, or mineral respectively.
        // For the default case, print "Unknown Menu Option".
        System.out.println("Please pick from the following choices \n1. Print the name of an animal" +
                "\n2. Print the name of s state \n3. Print the name of a beetle. \n4. Print the name of a mineral");
        Scanner console = new Scanner(System.in);
        String answer = console.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice) {
            case 1:
                System.out.println("Dog");
                break;
            case 2:
                System.out.println("Wisconsin");
                break;
            case 3:
                System.out.println("Stag");
                break;
            case 4:
                System.out.println("Sapphire");
                break;
            default:
                System.out.println("I don't recognize that one");
                break;
        }

    }
}

import java.util.Scanner;

public class Exercise12 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();
        boolean exist = false;
        for(int x = 0; x < phrase.length(); x++) {
            if(phrase.charAt(x) == 'x') {
                exist = true;
                break;
            }
        }
        if(exist) {
            System.out.println("Found the X");
        }
        else {
            System.out.println("No X here");
        }

        // 1. Write a loop to determine if the letter `x` occurs in a user-entered phrase.
        // 2. Print a message for both finding and not finding the `x`.
    }
}

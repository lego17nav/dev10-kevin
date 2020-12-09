import java.util.Scanner;

public class Exercise15 {

    public static void main(String[] args) {
        // SWITCH OPPOSITES
        // Given a word, print its opposite using a switch statement.
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String word = console.nextLine();
        String opposite = "";
        int len = word.length() - 1;
        while(len > 0)
        {
            opposite = opposite + word.charAt(len);
            len--;
        }
        System.out.println(opposite);

        // 1. Re-implement Exercise08 using a switch statement.
    }
}

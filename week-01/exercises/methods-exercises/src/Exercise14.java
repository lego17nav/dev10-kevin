import java.util.Scanner;
public class Exercise14 {
    /* SHORT SURVEY

    Write a program that asks a user four questions and prints the results:
    - What is your first name?
    - What is your last name?
    - How many towns/cities have you lived in?
    - How many musical instruments can you play?

    Store each answer in a variable with an appropriate type.
    Print the results after the user has answered all four questions.

    Use methods to break the program into reusable blocks of code.
     */
    public static String QuestionPrompt(Scanner console, String question) {
        System.out.println(question);
        String answer = console.nextLine();
        return answer;
    }
    public static void PrintInfo(String name, String last, String city, String instrument) {

        System.out.printf("First Name: %s\nLast Name: %s\nCity: %s\nInstrument: %s", name, last, city, instrument);
    }

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        String name = QuestionPrompt(console, "What is your first name?");
        String lastname = QuestionPrompt(console, "What is your last name?");
        String city = QuestionPrompt(console, "WHat city have you lived in?");
        String instrument = QuestionPrompt(console, "WHat instrument can you play?");

        PrintInfo(name,lastname,city,instrument);

    }
}

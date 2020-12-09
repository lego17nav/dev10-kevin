import java.util.Scanner;
public class Exercise09 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter A minimum number followed by a max number and a number in between the two");
        String min = console.nextLine();
        String max = console.nextLine();
        String between = console.nextLine();
        int minInt = Integer.parseInt(min);
        int maxInt = Integer.parseInt(max);
        int betweenInt = Integer.parseInt(between);
        if(betweenInt > minInt && betweenInt < maxInt) {
            System.out.println("That's Correct");
        }
        else {
            System.out.println("That's incorrect");
        }
    }
}

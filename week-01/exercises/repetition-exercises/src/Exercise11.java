import java.util.Scanner;

public class Exercise11 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a min");
        String min = console.nextLine();
        System.out.println(("Enter a max"));
        String max = console.nextLine();
        System.out.println("Enter an Interval");
        String interval = console.nextLine();
        int minInt = Integer.parseInt(min);
        int maxInt = Integer.parseInt(max);
        int intervalInt = Integer.parseInt(interval);
        int sum = 0;
        for(int x = minInt; x < maxInt; x += intervalInt) {
            sum += x;
        }
        System.out.println(sum);
    }
}

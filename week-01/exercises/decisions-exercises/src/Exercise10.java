import java.io.Console;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Exercise10 {

    public static void main(String[] args) {
        // USPS
        // Below is an abbreviated version of the US Postal Service retail parcel rates:
        /*
        Lbs | Zones 1&2 | Zone 3
        ===============
        1      $7.50      $7.85
        2       8.25       8.70
        3       8.70       9.70
        4       9.20      10.55
        5      10.20      11.30
        */

        // 1. Collect the parcel lbs and zone (1, 2, or 3) from the user.
        // 2. Add `if`/`else if`/`else` logic to cover all rates.
        // Use whatever strategy you think is best. You can create compound conditions or nest if/else statements.
        // If a lbs/zone combo does not exist, print a warning message for the user.

        Scanner console = new Scanner(System.in);
        System.out.println("Please Enter the weight");
        String lbs = console.nextLine();
        System.out.println("Please Enter the Zone");
        String zone = console.nextLine();
        String[] zoneList = {"1", "2"};
        String[] lbsList = {"1", "2", "3", "4", "5"};
        double price = 0;
        if (zone.equals("1")) {
            switch (lbs) {
                case "1":
                    price = 7.50;
                case "2":
                    price = 8.25;
                case "3":
                    price = 8.70;
                case "4":
                    price = 9.20;
                case "5":
                    price = 10.20;
            }

        }
        else if (zone.equals("2")) {
            switch (lbs) {
                case "1":
                    price = 7.85;
                case "2":
                    price = 8.70;
                case "3":
                    price = 9.70;
                case "4":
                    price = 10.55;
                case "5":
                    price = 11.30;
            }
        } else {
            System.out.println("That data is not available");
            System.exit(0);
        }



        System.out.printf("Hey for Zone %s with the weight of %s the total price is %.2f", zone, lbs, price);


    }
}


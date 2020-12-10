public class Exercise15 {

    public static void main(String[] args) {
        // BOX
        // 1. Use nested loops to print a box in the console.
        // One loop should represent rows and the other should represent columns.
        // 2. Change the row and column limit to change the shape of the box.

        // Expected Output (5X5)
        // #####
        // #####
        // #####
        // #####
        // #####


        // (3X4)
        // ####
        // ####
        // ####
        for (int x = 0; x != 5; x++) {
            for (int y = 0; y != 5; y++) {
                System.out.print("#");
            }
            System.out.print("\n");
        }
        for (int x = 0; x != 4; x++) {
            for (int y = 0; y != 3; y++) {
                System.out.print("#");
            }
            System.out.print("\n");
        }
    }
}

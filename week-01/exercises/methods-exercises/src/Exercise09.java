public class Exercise09 {

    public static void printBox(int x, int y) {
        for(int row = 0; row < x; row++) {
            for(int col = 0; col < y; col ++){
                System.out.print("#");
            }
            System.out.println("#");
        }
    }

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        printBox(5,5);
        printBox(3,4);
    }

    // 1. Create a method.
    // Name: printBox
    // Inputs: int, int
    // Output: void
    // Description: prints a visual box to the console. The first parameter is the number of rows to print.
    // The second parameter is the number of columns.
    // See repetition Exercise15.

    // Expected Output (5 rows, 5 columns)
    // #####
    // #####
    // #####
    // #####
    // #####

    // (3 rows, 4 columns)
    // ####
    // ####
    // ####

}

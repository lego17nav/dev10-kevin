public class Exercise16 {

    public static void main(String[] args) {
        // BORDER BOX
        // 1. Use nested loops to print a box in the console with a different character for the border.
        // One loop should represent rows and the other should represent columns.
        // The border character should be different from the internal box character.
        // 2. Change the row and column limit to change the shape of the box.

        // Expected Output (5X5)
        // *****
        // *###*
        // *###*
        // *###*
        // *****

        // (3X4)
        // ****
        // *##*
        // ****

        // (2X2)
        // **
        // **

        for(int x = 0; x != 5; x++){
            for(int y = 0; y != 5; y++)
            {
                if(x == 1 || y == 1)
                {
                    System.out.print("*");
                }
                else {
                    System.out.print("#");
            }

            }
        }
    }
}

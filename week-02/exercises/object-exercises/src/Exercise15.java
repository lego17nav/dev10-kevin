public class Exercise15 {

    // 1. Create a new method in the Hero class.
    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.

    public static void main(String[] args) {

        // 2. Instantiate your three favorite super heroes with appropriate powers.
        // 3. Use the `toLine` method to print each hero's details to the console.
        Hero one = new Hero("Captain America", new Power[]{new Power("Super Strength")});
        Hero two = new Hero("Black Widow", new Power[]{new Power ("Useless")});
        Hero three = new Hero("Spiderman", new Power[]{new Power ("Webs")});

        System.out.println(one.toLine());
        System.out.println(two.toLine());
        System.out.println(three.toLine());
    }
}

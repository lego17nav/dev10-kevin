public class Exercise08 {

    // 1. Create a method.
    // Name: getRandomFruit
    // Inputs: none
    // Output: String
    // Description: returns a random fruit name as a string.
    // See Exercise01.
    // Choose from at least 5 fruit.
    public static String getRandomFruit() {
        int fruit = (int) (Math.random() * 5 + 1);
        switch(fruit) {
            case 1:
                return "Apple";
            case 2:
                return "Orang";
            case 3:
                return "Banana";
            case 4:
                return "Dragon Fruit";
            case 5:
                return "Papaya";

        }
        return "";
    }

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        System.out.println(getRandomFruit());
        System.out.println(getRandomFruit());
        System.out.println(getRandomFruit());
        System.out.println(getRandomFruit());
        System.out.println(getRandomFruit());
    }
}

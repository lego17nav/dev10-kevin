public class Exercise06 {

    // 1. Create a method.
    // Name: isBetween
    // Inputs: int, int, int
    // Output: boolean
    // Description: return true if the first parameter is between the second and third parameter.
    // Otherwise, returns false.
    public static boolean isBetween(int first, int second, int third) {
        if(first > second && first < third) {
            return true;
        }
        else
            return false;
    }

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        System.out.println(isBetween(1,7,4));
        System.out.println(isBetween(110,100,300));
        System.out.println(isBetween(99,90,98));

    }
}

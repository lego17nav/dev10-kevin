public class Exercise07 {

    // 1. Create a method.
    // Name: areInOrder
    // Inputs: int, int, int, int
    // Output: boolean
    // Description: return true if the four parameters are in ascending order.
    // Otherwise, returns false.
    public static boolean areInOrder(int a, int b, int c, int d) {
        if(a < b && b < c && c < d) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        System.out.println(areInOrder(1,2,3,5));
        System.out.println(areInOrder(5,3,6,1));
        System.out.println(areInOrder(9,7,5,3));
    }
}

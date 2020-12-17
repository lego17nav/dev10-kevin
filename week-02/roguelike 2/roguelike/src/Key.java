public class Key {

    private final int x;
    private final int y;
    private char symbol = 'T';

    public Key(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }

}

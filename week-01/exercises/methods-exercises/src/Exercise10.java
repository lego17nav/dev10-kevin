public class Exercise10 {
    // 1. Add a `main` method.
    // 2. Create method that accepts a String and returns that string with all of its whitespace remove.
    // 2. Call your method in various ways in the main method.
    public static String whiteSpace(String phrase) {
        int phraseLength = phrase.length();
        String modifiedPhrase = "";
        for(int i = 0; i < phraseLength; i++)
        {
            if(phrase.charAt(i) != ' ') {
                modifiedPhrase += phrase.charAt(i);
            }
        }
        return modifiedPhrase;
    }
    public static void main(String[] args) {

        System.out.println(whiteSpace("Hello Johnny"));
        System.out.println(whiteSpace("TIck Tack Toe"));

    }
}

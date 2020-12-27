import java.io.*;

public class ReadSecret {

    public static void main(String[] args) {

        File filein = new File("Secretfile.txt");
        PrintWriter writer = null;

        try (PrintWriter newwriter = new PrintWriter("Secretfile.txt")){

            newwriter.println("Humpty Dumpty");
            newwriter.println("Merry Xmas");
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void readSecret(String filein) {

        try (FileReader fileReader = new FileReader("filein");
             BufferedReader reader = new BufferedReader(fileReader)) {

            // When there are no more lines, readLine() return null.
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}

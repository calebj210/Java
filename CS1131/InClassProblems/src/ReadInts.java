import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class ReadInts {
    public static void main(String[] args) {
        File input = new File("src/input.txt");
        File output = new File("src/output.txt");
        try (Scanner in = new Scanner(input);
             PrintWriter out = new PrintWriter(output)) {
            int N = in.nextInt();

            StringBuilder string = new StringBuilder();
            for (int i = 0; i < N; i++) {
                string.insert(0,in.nextInt() + "\n");
            }

            System.out.print(string);

            out.println(N);
            out.print(string);
        } catch (FileNotFoundException e) {
            System.err.println("AN ERROR OCCURRED");
            e.printStackTrace();
        }
    }
}

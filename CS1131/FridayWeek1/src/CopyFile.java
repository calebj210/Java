import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CopyFile {
    public static void main(String[] args) {
        if(args.length != 2) {
            throw new IllegalArgumentException("The main method requires exactly two arguments.");
        }

        try(Scanner inFile = new Scanner( new File( args[0] ) );
            PrintWriter outFile = new PrintWriter( new File( args[1] ) ) ) {
            inFile.useDelimiter( "" );
            while(inFile.hasNext()) {
                outFile.print(inFile.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
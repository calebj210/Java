import java.io.File;
import java.util.Scanner;

public class Week5Quiz {
    public static void main(String[] args) {
        try {
            p();
            System.out.println("After the method call");
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException");
        }
        catch (RuntimeException ex) {
            System.out.println("RuntimeException");
        }
    }

    static void p() {
        String s = "5.6";
        Integer.parseInt(s); // Cause a NumberFormatException

        int i = 0;
        int y = 2 / i;
        System.out.println("Welcome to Java");
    }
}

import java.util.Scanner;

public class AdditionQuiz {
    public static void main(String[] args) {
        int number1 = (int)(System.currentTimeMillis() % 10);
        int number2 = (int)(System.currentTimeMillis() /10 % 10);

        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Give addition problem prompt
        System.out.print("What is " + number1 + " + " + number2 + "? ");

        // Get answer from user
        int answer = input.nextInt();

        // Check answer
        System.out.println("What is " + number1 + " + " + number2 + " = " + answer + " is "
                + (number1 + number2 == answer));
    }
}
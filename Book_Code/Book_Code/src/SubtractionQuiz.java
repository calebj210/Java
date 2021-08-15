import java.util.Scanner;

public class SubtractionQuiz {
    public static void main(String[] args) {
        // Generate and store two random numbers from 0 to 9
        int number1 = (int)(Math.random() * 10);
        int number2 = (int)(Math.random() * 10);

        // Swap numbers if number1 < number2
        if (number1 < number2) {
            int tmp = number1;
            number1 = number2;
            number2 = tmp;
        }

        // Prompt subtraction problem
        System.out.print("What is " + number1 + " - " + number2 + "? ");

        // Create scanner
        Scanner input = new Scanner(System.in);

        // Get answer
        int answer = input.nextInt();

        // Check answer and display if solution is correct
        if (number1 - number2 == answer) {
            System.out.println("Your answer is correct!");
        } else {
            System.out.println("Your answer is incorrect!");
            System.out.println("The correct answer to " + number1 + " - " + number2 + " is " + (number1 - number2));
        }
    }
}
import java.util.Scanner;

public class nameScan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your first name?");
        String firstName = input.next();
        System.out.println("What is your age?");
        int age = 8;
        do {
            if (input.hasNextInt()){
                age = input.nextInt();
            } else {
                input.next();
                System.out.print("I need an integer. What is your age? ");
            }
        } while (age == 8);

        System.out.println("Hi " + firstName + ". You are " + age + " years old.");
    }
}

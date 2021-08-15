import java.util.Scanner;


public class Program0 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter side A:");
        int sideA = input.nextInt();
        System.out.print("Enter side B:");
        int sideB = input.nextInt();
        System.out.print("Enter side C:");
        int sideC = input.nextInt();

        if (sideA + sideB > sideC && sideA + sideC > sideB && sideB + sideC > sideA) {
            System.out.println("perimeter=" + (sideA + sideB + sideC));
        } else {
            System.out.println("Invalid Triangle");
        }
    }
}

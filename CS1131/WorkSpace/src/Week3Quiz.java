import java.util.Arrays;
import java.awt.geom.Point2D;
import java.util.Scanner;

public class Week3Quiz {
    public void printArray( int [ ] array ) {
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1] + "]");
    }

//    public static void main(String[] args) {
//        int[] array = {1,2,3,4,5,6,7,8,9,0};
//
//        Week3Quiz obj = new Week3Quiz();
//        obj.printArray(array);
//
//        int [][] MAP = new int[10][10];
//
//        for (int i = 1; i < 9; i++) {
//            for (int j = 1; j < 9; j++) {
//                MAP[i][j] = 99;
//            }
//        }
//
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(MAP[i][j] + "\t");
//            }
//            System.out.println();
//        }
//    }
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        int [] x = new int[3];
//
//        System.out.print("Please enter three whole numbers: ");
//        x[0] = input.nextInt();
//        x[1] = input.nextInt();
//        x[2] = input.nextInt();
//
//        Arrays.sort(x);
//
//        System.out.println("The three numbers in increasing order are: " +
//                x[0] + ", " + x[1] + ", and " + x[2]);
//    }

    public boolean isPerfectSquare(int n) {
        return (Math.sqrt(n) == Math.floor(Math.sqrt(n)));
    }

    public static void main(String[] args) {
        Week3Quiz obj = new Week3Quiz();
        for (int i = 0; i < 50; i++) {
            if (obj.isPerfectSquare(Integer.parseInt(args[i]))) {
                System.out.println(args[i] + " is a perfect square!");
            }
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.println(i + " " + j);
            }
        }
    }
}

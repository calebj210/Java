import java.util.Scanner;

public class Week1 {
    public static void main(String[] args) {
	System.out.println(xMethod(5, 500L));
    }

    public static int xMethod(int n, long l) {
	System.out.println("int, long");
	return n;
    }

    public static long xMethod(long n, long l) {
	System.out.println("long, long");
	return n;
    }
}

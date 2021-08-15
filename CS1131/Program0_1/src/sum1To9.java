public class sum1To9 {
    public static void main(String[] args) {
        int i = 1;

        for (int j = 2; j <= 9; j++) {
            i += j;
        }

        System.out.println("The sum from 1 to 9 is " + i);
    }
}

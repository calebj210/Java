public class PrintFDemo {


    public static void main(String[] args) {
        int len = -10;
        String format = "%" + len + "s\t%d\n";
        System.out.printf(format, "doc", 5886);
        System.out.printf(format, "sneezy", 1824);
    }
}

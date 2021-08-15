import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Week13Quiz {
    public static void main(String [] args) {
        String s = "good";
        Stream.of(s.toCharArray()).forEach(e ->
                System.out.print(e + " "));
    }
}

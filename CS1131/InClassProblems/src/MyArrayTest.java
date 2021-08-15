import static org.junit.Assert.*;
import org.junit.Test;

public class MyArrayTest {

    @Test
    public void get() {
        MyArray myArray = new MyArray(3);
        int index = 2;
        int expected = 0;
        int result = myArray.get(index);
        if (result != expected) {
            fail(String.format("FAIL: myArray.get(%d) returned %d but we expected %d", index, result, expected));
        }
    }

    @Test
    public void get1() {
        try {
            MyArray myArray = new MyArray(3);
            int index = -1;
            int result = myArray.get(index);
            fail(String.format("FAIL: myArray.get(%d) returned %d but we expected IndexOutOfBounds exception", index, result));
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    public void set() {

    }
}
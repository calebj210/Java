import org.junit.Test;
import static org.junit.Assert.*;

public class MathLibraryTest {

    @Test
    public void absoluteValue() {
        MathLibrary mathLibrary = new MathLibrary();
        double value = -1.0;
        double expectedResult = 1.0;
        double result = mathLibrary.absoluteValue(value);
        if (result != expectedResult) {
            fail(String.format("absoluteValue(%f) returned %f but we expected %f", value, result, expectedResult));
        }
    }

    @Test
    public void absoluteValue1() {
        MathLibrary mathLibrary = new MathLibrary();
        double value = 1.0;
        double expectedResult = 1.0;
        double result = mathLibrary.absoluteValue(value);
        if (result != expectedResult) {
            fail(String.format("absoluteValue(%f) returned %f but we expected %f", value, result, expectedResult));
        }
    }

    @Test
    public void absoluteValue2() {
        MathLibrary mathLibrary = new MathLibrary();
        double value = 0.0;
        double expectedResult = 0.0;
        double result = mathLibrary.absoluteValue(value);
        if (result != expectedResult) {
            fail(String.format("absoluteValue(%f) returned %f but we expected %f", value, result, expectedResult));
        }
    }
}
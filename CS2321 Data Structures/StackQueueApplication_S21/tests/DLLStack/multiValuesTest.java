import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;

@jug.SuiteName("test pop()")
public class multiValuesTest {

	private DLLStack<String> T = init();

	public DLLStack<String> init() {

		return new DLLStack<String>();
	
	}

	@Before
	public void setup() throws Throwable {
		T.push("Alice");
			T.push("Bob");
			T.push("Cindy");
	}

	@org.junit.Test()
	@jug.TestName("test multiple values \"Alice\",\"Bob\",\"Cindy\"")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("test multiple values \"Alice\",\"Bob\",\"Cindy\"", (Object)(("Cindy")), (Object)(T.top()));
	}

	@org.junit.Test()
	@jug.TestName("test top()")
	public void Test2() throws Throwable {
		String[] res = {T.pop().toString(), T.pop().toString(), T.pop().toString()}
		;
		String[] ans = {"Cindy", "Bob", "Alice"}
		;
		
		org.junit.Assert.assertArrayEquals("test top()", ans, res);
	}

}

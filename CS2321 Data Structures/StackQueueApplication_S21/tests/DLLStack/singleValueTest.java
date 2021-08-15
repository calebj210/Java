import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;

@jug.SuiteName("test pop()")
public class singleValueTest {

	private DLLStack TARGET = init();
	private DLLStack T = init();

	public DLLStack init() {

		return new DLLStack<String>();
	
	}

	@Before
	public void setup() throws Throwable {
		T.push("Alice");
	}

	@org.junit.Test()
	@jug.TestName("test single value \"Alice\"")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("test single value \"Alice\"", (Object)("Alice"), (Object)(T.top()));
	}

	@org.junit.Test()
	@jug.TestName("test top()")
	public void Test2() throws Throwable {
		
		org.junit.Assert.assertEquals("test top()", (Object)("Alice"), (Object)(T.pop()));
	}

}

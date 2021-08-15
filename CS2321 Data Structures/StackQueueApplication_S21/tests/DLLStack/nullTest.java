import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;

@jug.SuiteName("ArrayList Stack Test")
public class nullTest {

	private DLLStack TARGET = init();
	private DLLStack T = init();

	public DLLStack init() {

		return new DLLStack<String>();
	
	}

	@org.junit.Test(timeout=15000)
	@jug.TestName("test null stack")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("test null stack", (Object)(null), (Object)(T.top()));
	}

	@org.junit.Test()
	@jug.TestName("test top()")
	public void Test2() throws Throwable {
		
		org.junit.Assert.assertEquals("test top()", (Object)(null), (Object)(T.pop()));
	}

}

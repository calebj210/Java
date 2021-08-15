import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class Week11ProgramTest {

	String filepath = "";

	@Test(timeout=1000)
	public void Week11ProgramZeroQueueSizeTest() {
		String inputFile = filepath + "input5zero.txt";
		String output = "";

      PrintStream origOut = System.out;
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.setOut(new PrintStream(baos));

			String[] args = { inputFile };
			Week11Program.main(args);
			for( int i = 0; i < 100; i++ ) {
			   System.out.println( i );
			}
			output = baos.toString();

		} catch (QueueFullException e) {
			return;
		} catch (Exception e) {

			throw new Error(e);
		} finally {
	       System.setOut(origOut);
		}

		fail("Queue size limited to 0 did not throw QueueFullError.");
	}

	@Test(timeout=1000)
	public void Week11ProgramOneQueueSizeTest() {
		String inputFile = filepath + "input5one.txt";
		String expected = "1\n2\n3\n4\n5\n6\n7\n8\n9\n";
		String output = "";

      PrintStream origOut = System.out;

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.setOut(new PrintStream(baos));

			String[] args = { inputFile };
			Week11Program.main ( args );
			output = baos.toString();
		} catch (Exception e) {

			throw new Error(e);
		} finally {
         System.setOut(origOut);
     }

		if (!output.equalsIgnoreCase( expected )) {
			fail("Queue limited to size = 1. Expected output: \""
					+ expected.replace("\n", "\\n") + "\" Your output: \""
					+ output.replace("\n", "\\n") + "\"");
		}

	}

	@Test(timeout=1000)
	public void Week11ProgramEmbeddedNewlinesTest() {
		String inputFile = filepath + "input5Newlines.txt";
		String expected = "\n1\n2\n3\n4\n5\n6\n\n7\n8\n9\n\n";
		String output = "";
      PrintStream origOut = System.out;

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.setOut(new PrintStream(baos));

			String[] args = { inputFile };
			Week11Program.main ( args );
			output = baos.toString();
		} catch (Exception e) {

			throw new Error(e);
		} finally {
         System.setOut(origOut);
		}

		if (!output.equalsIgnoreCase(expected)) {
			fail("Queue limited to size = 12. Expected output: \""
					+ expected.replace("\n", "\\n") + "\" Your output: \""
					+ output.replace("\n", "\\n") + "\"");
		}

	}

	@Test(timeout=1000)
	public void Week11ProgramLargeQueueLimitTest() {
		String inputFile = filepath + "input5ninetynine.txt";
		String expected = "123456789\n";
		String output = "";
      PrintStream origOut = System.out;

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.setOut(new PrintStream(baos));

			String[] args = { inputFile };
			Week11Program.main ( args );
			output = baos.toString();
		} catch (Exception e) {

			throw new Error(e);
		} finally {
         System.setOut(origOut);
      }

		if (!output.equalsIgnoreCase(expected)) {
			fail("Queue limited to size = 99. \nExpected output: \""
					+ expected.replace("\n", "\\n") + "\" \nYour output: \""
					+ output.replace("\n", "\\n") + "\"");
		}

	}

	@Test(timeout=1000)
	public void Week11ProgramCatExample() {
		String inputFile = filepath + "input3.txt";
		String expected = "            '-.       .-'         \n            '-.'-\"\"\"-'.-'      _  \n             |= _:'.':_ =|    /:`)\n             \\ <6>   <6> /   /  / \n             |=   |_|   =|   |:'\\ \n             >\\:.  \"  .:/<    ) .|\n              /'-._^_.-'\\    /.:/ \n             /::.     .::\\  /' /  \n           .| '::.  .::'  |;.:/   \n          /`\\:.         .:/`\\(    \n         |:. | ':.   .:' | .:|    \n         | ` |:.;     ;.:| ` |    \n          \\:.|  |:. .:|  |.:/     \n           \\ |:.|     |.:| /      \n           /'|  |\\   /|  |`\\      \n          (,,/:.|.-'-.|.:\\,,)     \n            (,,,/     \\,,,)       \n";
		String output = "";
      PrintStream origOut = System.out;

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.setOut(new PrintStream(baos));

			String[] args = { inputFile };
			Week11Program.main ( args );
			output = baos.toString();
		} catch (Exception e) {

			throw new Error(e);
		} finally {
         System.setOut(origOut);
      }

		if (!output.equalsIgnoreCase(expected)) {
			fail("Queue limited to size = 34. This was the cat example \"input3.txt\" \nExpected output: \""
					+ expected.replace("\n", "\\n") + "\" \nYour output: \""
					+ output.replace("\n", "\\n") + "\"");
		}

	}

	@Test(timeout=1000)
	public void QueueToStringTest() {
		String expected = "[4, 3, 2, 1]";
		Queue<Integer> queue = new Queue<Integer>( 200 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			queue.enqueue( 4 );
		} catch ( QueueFullException e ) {
			throw new Error(e);
		}
		if ( !expected.equalsIgnoreCase( queue.toString( ) ) ) {
			fail("toString failed \nExpected output: \""
					+ expected.replace("\n", "\\n") + "\" \nYour output: \""
					+ queue.toString().replace("\n", "\\n") + "\"");
		}
		
	}

	@Test(timeout=1000)
	public void FullQueueToStringTest() {
		String expected = "[3, 2, 1]";
		Queue<Integer> queue = new Queue<Integer>( 3 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			queue.enqueue( 4 );
		} catch ( QueueFullException e ) {
			
		}
		if ( !expected.equalsIgnoreCase( queue.toString( ) ) ) {
			fail("toString failed \nExpected output: \""
					+ expected.replace("\n", "\\n") + "\" \nYour output: \""
					+ queue.toString().replace("\n", "\\n") + "\"");
		}
		
	}

	@Test(timeout=1000)
	public void EmptyQueueToStringTest() {
		String expected = "[]";
		Queue<Integer> queue = new Queue<Integer>( 200 );
		if ( !expected.equalsIgnoreCase( queue.toString( ) ) ) {
			fail("toString failed \nExpected output: \""
					+ expected.replace("\n", "\\n") + "\" \nYour output: \""
					+ queue.toString().replace("\n", "\\n") + "\"");
		}
		
	}

	@Test(timeout=1000)
	public void NewQueueSizeTest() {
		int expected = 0;
		Queue<Integer> queue = new Queue<Integer>( 200 );
		if ( expected != ( queue.size( ) ) ) {
			fail("size( ) failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ queue.size() );
		}
		
	}

	@Test(timeout=1000)
	public void EmptyQueueSizeTest() {
		int expected = 0;
		Queue<Integer> queue = new Queue<Integer>( 200 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.dequeue();
			queue.dequeue();
			queue.dequeue();
		} catch ( QueueFullException e ) {
			throw new Error(e);
		} catch ( QueueEmptyException e ) {
			
		}
		if ( expected != ( queue.size( ) ) ) {
			fail("size( ) failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ queue.size() );
		}
		
	}

	@Test(timeout=1000)
	public void QueueSizeTest() {
		int expected = 6;
		Queue<Integer> queue = new Queue<Integer>( 200 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			queue.enqueue( 4 );
			queue.enqueue( 5 );
			queue.enqueue( 6 );
		} catch ( QueueFullException e ) {
			
		}
		if ( expected != ( queue.size( ) ) ) {
			fail("size( ) failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ queue.size() );
		}
		
	}

	@Test(timeout=1000)
	public void FullQueueSizeTest() {
		int expected = 4;
		Queue<Integer> queue = new Queue<Integer>( 4 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			queue.enqueue( 4 );
			queue.enqueue( 5 );
			queue.enqueue( 6 );
		} catch ( QueueFullException e ) {
			
		}
		if ( expected != ( queue.size( ) ) ) {
			fail("toString failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ queue.size() );
		}
		
	}

	@Test(timeout=1000)
	public void EmptiedQueueSizeTest() {
		int expected = 3;
		Queue<Integer> queue = new Queue<Integer>( 3 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.dequeue( );
			queue.dequeue( );
			queue.dequeue( );
			queue.dequeue( );
		} catch ( QueueFullException e ) {
			throw new Error(e);
		} catch ( QueueEmptyException e ) {
			
		}
		try {
			queue.enqueue( 3 );
			queue.enqueue( 4 );
			queue.enqueue( 5 );
			queue.enqueue( 6 );
		} catch ( QueueFullException e ) {
			
		}
		if ( expected != ( queue.size( ) ) ) {
			fail("size( ) failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ queue.size() );
		}
		
	}

	@Test(timeout=1000)
	public void QueueIsFullEnqueueTest() {
		int expected = 1;
		Queue<Integer> queue = new Queue<Integer>( 3 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			queue.enqueue( 4 );
		} catch ( QueueFullException e ) {
			return;
		}
		fail("Enqueue past full failed to throw error for queue="+ queue.toString() );
	}

	@Test(timeout=1000)
	public void QueueIsSizeTest() {
		int expected = 4;
		Queue<Integer> queue = new Queue<Integer>( 4 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			queue.enqueue( 4 );
			queue.enqueue( 5 );
			queue.enqueue( 6 );
		} catch ( QueueFullException e ) {
			
		}
		if ( expected != ( queue.size( ) ) ) {
			fail("size( ) failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ queue.size() );
		}
		
	}

	@Test(timeout=1000)
	public void QueueIsEmptyUnderfillTest() {
		int expected = 1;
		Queue<Integer> queue = new Queue<Integer>( 3 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.dequeue( );
			queue.dequeue( );
			queue.dequeue( );
		} catch ( QueueFullException e ) {
			throw new Error(e);
		} catch ( QueueEmptyException e ) {
			return;
		}
		fail("Dequeue past full failed to throw error for queue="+ queue.toString() );
	}

	@Test(timeout=1000)
	public void QueueIsEmptySizeTest() {
		int expected = 0;
		Queue<Integer> queue = new Queue<Integer>( 4 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			queue.dequeue(  );
			queue.dequeue(  );
			queue.dequeue(  );
		} catch ( QueueFullException e ) {
			throw new Error(e);
		} catch ( QueueEmptyException e ) {
			
		}
		if ( expected != ( queue.size( ) ) ) {
			fail("size( ) failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ queue.size() );
		}
		
	}

	@Test(timeout=1000)
	public void QueueValueTest() {
		Integer [ ] foo = {1, 2, 3};
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(foo));
		Queue<Integer> queue = new Queue<Integer>( 40 );
		ArrayList<Integer> result = new ArrayList<Integer>( );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			result.add(queue.dequeue(  ));
			result.add(queue.dequeue(  ));
			result.add(queue.dequeue(  ));
		} catch ( QueueFullException e ) {
			throw new Error(e);
		} catch ( QueueEmptyException e ) {
			throw new Error(e);
		}
		if ( result == null || expected.get(0) != result.get(0) || expected.get(1) != result.get(1) || expected.get(1) != result.get(1)  ) {
			fail("Value test failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ result );
		}
		
	}

	@Test(timeout=1000)
	public void QueuePeekTest() {
		int expected = 2;
		int result = -1;
		Queue<Integer> queue = new Queue<Integer>( 40 );
		try {
			queue.enqueue( 1 );
			queue.enqueue( 2 );
			queue.enqueue( 3 );
			queue.dequeue(  );
			queue.peek( );
			result = queue.peek( );
		} catch ( QueueFullException e ) {
			throw new Error(e);
		} catch ( QueueEmptyException e ) {
			throw new Error(e);
		}
		if ( expected != result ) {
			fail("Peek failed for queue="+ queue.toString() + ". \nExpected output: "
					+ expected + " \nYour output: "
					+ result );
		}
		
	}

	@Test(timeout=1000)
	public void QueueTest() {
		Queue<Integer> queue = new Queue<Integer>( 40 );
	}

}

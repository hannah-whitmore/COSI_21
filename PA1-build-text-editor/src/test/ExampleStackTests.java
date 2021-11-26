package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Stack;

/**
 * This class tests the functionality of the Stack.
 * @author COSI21a 
 */
class ExampleStackTests {

	/** Stack used for testing */
	Stack<String> s;

	/**
	 * Before each test, the Stack is re-initialized
	 */
	@BeforeEach
	void reset() {
		s = new Stack<String>();
	}

	/**
	 * Tests the state of the stack after a sequence of push and pop operations
	 */
	@Test
	void pushPopTest() {
		s.push("three");
		s.push("five");
		s.pop();
		s.push("seven");
		assertFalse(s.isEmpty());
		assertEquals(s.top(),"seven");
		assertEquals(s.size(),2);
		assertEquals(s.pop(),"seven");
		assertEquals(s.size(),1);
		assertEquals(s.pop(),"three");
		assertEquals(s.size(),0);
		assertTrue(s.isEmpty());
	}
	
	/**
	 * Tests the format of the toString of the stack
	 */
	@Test
	void toStringTest() {
		assertEquals(s.toString(),"");
		s.push("ten");
		assertEquals(s.toString(),"ten\n");
		s.push("eleven");
		assertEquals(s.toString(),"eleven\nten\n");
		s.pop();
		assertEquals(s.toString(),"ten\n");
		s.pop();
		assertEquals(s.toString(),"");
	}

}

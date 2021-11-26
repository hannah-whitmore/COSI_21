/**
 * This class tests the stack 
 * Known bugs: not fully confident in my runtime analysis 
 * @author Hannah Whitmore
 * October 7, 2020
 * COSI 21A PA1
 */

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Stack;

class StudentStackTests {
	
	Stack <Integer> s1;
	Stack<String> s2;
	
	void constructorInt() {
		s1 = new Stack<Integer>();
	}
	
	void constructorString() {
		s2 = new Stack<String>();
	}

	@Test
	void testPush() {
		constructorInt();
		assertEquals("", s1.toString());
		assertEquals(10, s1.internalSize());
		
		s1.push(1);
		assertEquals("1\n", s1.toString());
		
		s1.push(3);
		s1.push(5);
		assertEquals("5\n3\n1\n", s1.toString());
		assertEquals(5, s1.top());
		
		// adding to capacity 
		s1.push(7); s1.push(9); s1.push(11); s1.push(13); s1.push(15); s1.push(17); s1.push(19);
		assertEquals(10, s1.size());
		assertEquals("19\n17\n15\n13\n11\n9\n7\n5\n3\n1\n", s1.toString());
		
		// ensure capacity should be called
		s1.push(21);
		assertEquals(11, s1.size());
		assertEquals(20, s1.internalSize());
		assertEquals("21\n19\n17\n15\n13\n11\n9\n7\n5\n3\n1\n", s1.toString());
		assertFalse(s1.isEmpty());
		assertEquals(21, s1.top());
	}
	
	@Test
	void testPop() {
		constructorString();
		s2.push("a");
		s2.push("b");
		s2.push("c");
		assertEquals(3, s2.size());
		s2.pop();
		assertEquals(2, s2.size());
		assertFalse(s2.isEmpty());
		assertEquals("b\na\n", s2.toString());
		assertEquals("b", s2.top());
		
		s2.pop();
		assertEquals(1, s2.size());
		assertFalse(s2.isEmpty());
		assertEquals("a\n", s2.toString());
		
		// empty 
		s2.pop();
		assertEquals(0, s2.size());
		assertEquals(10, s2.internalSize());
		assertTrue(s2.isEmpty());
		assertEquals("", s2.toString());
		assertNull(s2.top());
		
		// push on emptied stack 
		s2.push("d"); 
		s2.push("e");
		assertEquals(2, s2.size());
		assertFalse(s2.isEmpty());
		assertEquals("e\nd\n", s2.toString());
		assertEquals("e", s2.top());
	}
	
	@Test
	void testPopEmpty() {
		constructorInt();
		assertTrue(s1.isEmpty());
		assertNull(s1.top());
		
		boolean thrown = false;
		try {
			s1.pop();
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);	
		
		assertNull(s1.top());
		assertEquals(0, s1.size());
	}

}

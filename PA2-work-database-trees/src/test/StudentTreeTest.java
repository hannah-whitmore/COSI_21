package test;

import static org.junit.jupiter.api.Assertions.*;
import main.WorkEntrySearchNode;
import main.InputFileReader;
import main.WorkEntry;
import main.WorkTimeAnalysisTool;

import org.junit.jupiter.api.Test;

class StudentTreeTest {
	WorkTimeAnalysisTool tool;

	
	void construct() {
		WorkEntry[] entries = InputFileReader.getWorkEntriesFromCSV("pdf_table_csv.txt");
		tool = new WorkTimeAnalysisTool(entries);
	}


	@Test
	void testAdd() {
		WorkEntrySearchNode oh = new WorkEntrySearchNode("office hours");
		WorkEntrySearchNode g = new WorkEntrySearchNode("grading hw");
		WorkEntry e1  = new WorkEntry("9/18/2019", 1.5, "office hours", "helped students with pa1");
		WorkEntry e2 = new WorkEntry("10/1/2019", 1.0, "office hours", "");
		WorkEntry e3 = new WorkEntry("9/19/2019", 0.5, "grading hw", "grading late hw");
		WorkEntry e4 = new WorkEntry("9/25/2019", 0.5, "grading hw", "graded pa 1");
		oh.add(e1);
		oh.add(e2);
		g.add(e3);
		g.add(e4);
		assertEquals(2, oh.entrySize);
		assertEquals(2, g.entrySize);
		assertEquals("[9/18/2019] office hours (1.5 h): helped students with pa1" + "\n" + 
				"[10/1/2019] office hours (1.0 h):" + "\n" +  
				"Total: 2.5 h", oh.getEntryData());
		
		// should only add a work entry if its activity matches the key 
		g.add(e1);
		assertEquals(2, g.entrySize);
	
	}
	
	@Test
	void testCompareTo() {
		WorkEntrySearchNode oh = new WorkEntrySearchNode("office hours");
		WorkEntrySearchNode hw = new WorkEntrySearchNode("grading hw");
		assertEquals(-1, hw.compareTo(oh));
		WorkEntrySearchNode g2 = new WorkEntrySearchNode("grading hw");
		assertEquals(0, hw.compareTo(g2));
		WorkEntrySearchNode exam = new WorkEntrySearchNode("grading exam");
		assertEquals(1, hw.compareTo(exam));
		
	}
	
	@Test
	void testInsertWithSizeOneTree() {
		WorkEntrySearchNode root = new WorkEntrySearchNode("abc");
		root = root.insert(new WorkEntrySearchNode("z"));
		assertEquals("((abc)z)", root.getStructure());
		
		root = new WorkEntrySearchNode("zbc");
		root = root.insert(new WorkEntrySearchNode("a"));
		assertEquals("(a(zbc))", root.getStructure());
	}
	
	@Test
	void testInsertWithSizeTwoTrees() {
		WorkEntrySearchNode root = new WorkEntrySearchNode("zbc");
		root.left = new WorkEntrySearchNode("b");
		root.left.parent = root;
		root = root.insert(new WorkEntrySearchNode("a"));
		assertEquals("(a(b(zbc)))", root.getStructure());
		
		root = new WorkEntrySearchNode("abc");
		root.right = new WorkEntrySearchNode("b");
		root.right.parent = root;
		root = root.insert(new WorkEntrySearchNode("d"));
		assertEquals("(((abc)b)d)", root.getStructure());
	}
	
	@Test
	void testSplayWithSizeFourTrees() {
		WorkEntrySearchNode a = new WorkEntrySearchNode("a");
		WorkEntrySearchNode b = new WorkEntrySearchNode("b");
		WorkEntrySearchNode c = new WorkEntrySearchNode("c");
		WorkEntrySearchNode d = new WorkEntrySearchNode("d");
		
		a.right = b;
		b.parent = a;
		
		b.right = c;
		c.parent = b;
		
		c.right = d;
		d.parent = c;
		
		d.splay();
		assertEquals("((a((b)c))d)", d.getStructure());
		// f(node) = ( + f(node.left) + node.val + f(node.right) + )
		
	}
	
	@Test
	void testSplayTreeFourLeft() {
		WorkEntrySearchNode d = new WorkEntrySearchNode("d");
		WorkEntrySearchNode c = new WorkEntrySearchNode("c");
		WorkEntrySearchNode b = new WorkEntrySearchNode("b");
		WorkEntrySearchNode a = new WorkEntrySearchNode("a");
		
		d.left = c;
		c.parent = d;
		
		c.left = b;
		b.parent= c;
		
		b.left = a;
		a.parent = b;
		
		a.splay();
		assertEquals("(a((b(c))d))", a.getStructure());
		
	}
}

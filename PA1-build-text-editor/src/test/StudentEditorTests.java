/**
 * This class tests the editor 
 * Known bugs: none
 * @author Hannah Whitmore
 * October 7, 2020
 * COSI 21A PA1
 */

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Node;
import java.io.FileNotFoundException;
import main.Editor;
import java.util.*;

class StudentEditorTests {
	Editor e;

	
	void genericConstruct() {
		e = new Editor();
	}
	
	void fileConstructorSingle() throws FileNotFoundException {
		e = new Editor("single_line_file.txt");
	}
	
	void fileConstructorMulti() throws FileNotFoundException {
		e = new Editor("multi_line_file.txt");
	}
	
	@Test
	void testMoveRight() {
		genericConstruct();
	// create editor storing "blue"
		// create editor storing "blue"
		loadEditorBLUE(e);

		// point cur at head
		e.cur = e.head;
		e.curPos = 0;

		// move cur right 2x
		e.moveRight();
		assertEquals(1, e.getCursorPosition());
		assertEquals('l', e.cur.data);
		e.moveRight();
		assertEquals(2, e.getCursorPosition());
		assertEquals('u', e.cur.data);
		
		// move cursor to be after last character
		e.moveRight();
		e.moveRight();
		assertEquals(4, e.getCursorPosition());
		assertNull(e.cur);
		
		// since cursor is at the end, another call should result in no change
		e.moveRight();
		assertEquals(4, e.getCursorPosition());
		assertNull(e.cur);
	
	}
	
	@Test
	void testMoveLeft() {
		genericConstruct();
		
		// create editor storing "blue"
		loadEditorBLUE(e);

		// point cur at tail
		e.cur = e.tail;
		e.curPos = 3;	
	
		// move cur left 2x
		e.moveLeft();
		assertEquals(2, e.getCursorPosition());
		assertEquals('u', e.cur.data);
		e.moveLeft();
		assertEquals(1, e.getCursorPosition());
		assertEquals('l', e.cur.data);
		
		e.moveLeft();
		assertEquals(0, e.getCursorPosition());
		assertEquals('b', e.cur.data);
		
		// trying to move left when cursor is before the first node should be ignored
		e.moveLeft();
		assertEquals(0, e.getCursorPosition());
		assertEquals('b', e.cur.data);
		
		e.moveToTail();
		e.moveLeft();
		assertEquals(3, e.getCursorPosition());
	}
	
	@Test
	void testMoveToHead() {
		genericConstruct();
		// create editor storing "blue"
		loadEditorBLUE(e);

		// put cur at 'u'
		e.cur = e.head.next.next;
		e.curPos = 2;

		// move to head
		e.moveToHead();

		// cur should now be at 'b'
		assertEquals(0, e.getCursorPosition());
		assertEquals('b', e.cur.data);
		
		e.moveRight(); // curPos = 3 and cur points to 'e'
		e.moveRight(); // curPos = 4 and cur points to null
		e.moveToHead();
		assertEquals(0, e.getCursorPosition());
		assertEquals('b', e.cur.data);
		
		// cur is 'l'
		e.curPos = 1;
		e.cur = e.head.next;
		e.moveLeft(); // cursor is already at head
		e.moveToHead();
		assertEquals(0, e.getCursorPosition());
		assertEquals('b', e.cur.data);
	}
	
	@Test
	void testMoveToTail() {
		genericConstruct();
		loadEditorBLUE(e);
		
		// put cur at 'b'
		e.curPos = 0;
		e.cur = e.head;
		e.moveToTail();
		assertEquals(4, e.getCursorPosition());
		assertNull(e.cur);
		
		// put cur at 'u'
		e.cur = e.head.next.next;
		e.curPos = 2;
		
		// move to tail
		e.moveToTail();

		// cur should now be after 'e'
		assertEquals(4, e.getCursorPosition());
		assertNull(e.cur);
		assertEquals("blue", e.toString());
	}
	
	@Test
	void testInsertFront() {
		genericConstruct();
		loadEditorBLUE(e);
		
		// cursor at front, insert before first node
		e.cur = e.head;
		e.curPos = 0;
		e.insert('!');
		assertEquals(1, e.getCursorPosition());
		assertEquals('b', e.cur.data);
		assertEquals(5, e.numChars);
		assertEquals("!blue", e.toString());
		assertEquals('!', e.head.data);
		
		// test that head is now !, insert at front again to make E new head
		e.moveToHead();
		//!blue
		e.insert('E');
		assertEquals(1, e.getCursorPosition());
		assertEquals('!', e.cur.data);
		assertEquals(6, e.numChars);
		assertEquals("E!blue", e.toString());
		assertEquals('E', e.head.data);
		
	}
	
	@Test
	void testInsertSingleFile() throws FileNotFoundException {
		// inserting at the front 
		
		fileConstructorSingle();
		e.moveToHead();
		e.insert('!');
		assertEquals("!I am a single line file.", e.toString());
		
		e.moveRight();
		e.moveRight();
		e.insert('*');
		assertEquals("!I *am a single line file.", e.toString());
		assertEquals('a', e.cur.data);
		assertEquals(4, e.getCursorPosition());
		
		// inserting in the middle 
		
		fileConstructorSingle();
		e.moveLeft();
		e.moveLeft();
		e.insert('%');
		assertEquals("I am a single line fil%e.", e.toString());
		assertEquals('e', e.cur.data);
		assertEquals(23, e.getCursorPosition());
		
		// inserting at the end
		e.moveToTail();
		e.insert('@');
		assertEquals("I am a single line fil%e.@", e.toString());
		assertEquals('@', e.tail.data);
		assertEquals(26, e.getCursorPosition());
	}
	
	
	@Test 
	void testInsertEnd() {
		genericConstruct();
		loadEditorBLUE(e);
		e.curPos = 0;
		e.cur = e.head;
		
		// cursor at end, insert after last node
		e.moveToTail();
		assertEquals('e', e.tail.data);
		e.insert('*');
		assertEquals('*', e.tail.data);
		assertEquals(5, e.getCursorPosition());
		assertNull(e.cur);
		assertEquals(5, e.numChars);
		assertEquals("blue*", e.toString());
		
		// insert again at the end 
		e.insert('^');
		assertEquals('^', e.tail.data);
		assertEquals(6, e.getCursorPosition());
		assertNull(e.cur);
		assertEquals(6, e.numChars);
		assertEquals("blue*^", e.toString());
	}
	
	@Test 
	void testInsertMiddle() {
		genericConstruct();
		loadEditorBLUE(e);
		
		// point cur at 'l'
		e.cur = e.head.next;
		e.curPos = 1;
		
		// insert $, @, % to get "b$@%lue"
		e.insert('$');
		e.insert('@');
		e.insert('%');
		assertEquals(4, e.getCursorPosition());
		assertEquals('l', e.cur.data);
		assertEquals("b$@%lue", e.toString());
		assertEquals(7, e.numChars);
		
		e.moveRight();
		e.moveRight();
		e.insert('!');
		// "b$@%lu!e"
		assertEquals(7, e.getCursorPosition());
		assertEquals('e', e.cur.data);
		assertEquals("b$@%lu!e", e.toString());
		assertEquals(8, e.numChars);
	}
	

	
	@Test
	void testInsertMultiFile() throws FileNotFoundException{
		// inserting at the front 

		fileConstructorMulti();
		e.moveToHead();
		e.insert('!');
		assertEquals("!I am a" + "\n" + "multiline" + "\n" + "file.", e.toString());
		
		
		e.moveRight();
		e.moveRight();
		e.insert('*');
		assertEquals("!I *am a" + "\n" + "multiline" + "\n" + "file.", e.toString());
		assertEquals('a', e.cur.data);
		assertEquals(4, e.getCursorPosition());
		
		// inserting in the middle 
		
		fileConstructorMulti();
		e.moveLeft();
		e.moveLeft();
		e.insert('%');
		assertEquals("I am a" + "\n" + "multiline" + "\n" + "fil%e.", e.toString());
		assertEquals('e', e.cur.data);
		assertEquals(21, e.getCursorPosition());
		
		
		// inserting at the end
		e.moveToTail();
		e.insert('@');
		assertEquals("I am a" + "\n" + "multiline" + "\n" + "fil%e.@", e.toString());
		assertEquals('@', e.tail.data);
		assertEquals(24, e.getCursorPosition());
		assertNull(e.cur);
	}
	
	@Test 
	void testSingleFileConstructor() throws FileNotFoundException {
		fileConstructorSingle();
		// conditions after constructor 
		assertEquals("I am a single line file.", e.toString());
		assertEquals(24, e.numChars);
		assertEquals(24, e.getCursorPosition());
		assertNull(e.cur);
		
		
		e.insert('!');
		assertEquals("I am a single line file.!", e.toString());
		assertEquals(25, e.numChars);
		assertEquals(25, e.getCursorPosition());
		assertNull(e.cur);
		assertEquals('I', e.head.data);
		assertEquals('!', e.tail.data);
		
		e.moveLeft();
		assertEquals(24, e.getCursorPosition());				
		assertEquals("I am a single line file.!", e.toString());
	}
	
	
	@Test
	void testClear() {
		genericConstruct();
		loadEditorBLUE(e);
		e.curPos = 0;
		e.cur = e.head;
		assertEquals("blue", e.toString());
		assertEquals(4, e.numChars);
		
		e.clear();
		
		e.insert('H');
		assertEquals("H", e.toString());
		e.insert('e'); 	
		assertEquals("He", e.toString());
		assertEquals(2, e.numChars);
		
		e.backspace();
		assertEquals("H", e.toString());
	}
	
	@Test
	void testClearSingleFile() throws FileNotFoundException {
		fileConstructorSingle();
		e.clear();
		assertNull(e.cur);
		assertNull(e.head);
		assertNull(e.tail);
		
		
		e.insert('H');
		assertEquals("H", e.toString());
		assertEquals(1, e.getCursorPosition());
		e.backspace();
		assertNull(e.cur);
	}
	
	
	@Test
	void testClearMultiFile() throws FileNotFoundException{
		fileConstructorMulti();
		e.clear();
		assertNull(e.cur);
		assertNull(e.head);
		assertNull(e.tail);
		
		
		e.insert('a');
		assertEquals("a", e.toString());
		assertEquals(1, e.getCursorPosition());
		e.insert('b');
		assertEquals("ab", e.toString());
		assertEquals(2, e.getCursorPosition());
		
	}

	
	@Test
	void testDeleteMultipleMiddle() {
		// create editor storing "blue"
		genericConstruct();
		loadEditorBLUE(e);

		// point cur at 'l'
		e.cur = e.head.next;
		e.curPos = 1;

		// delete 3x, cur moves to 'u', then 'e', then null
		e.delete(); // b|ue

		assertEquals('b', e.head.data);
		assertEquals('e', e.tail.data);
		assertEquals(1, e.getCursorPosition());
		assertEquals(3, e.size());
		assertEquals('u', e.cur.data);

		e.delete(); //b|e

		assertEquals('b', e.head.data);
		assertEquals('e', e.tail.data);
		assertEquals(1, e.getCursorPosition());
		assertEquals(2, e.size());
		assertEquals('e', e.cur.data);

		e.delete(); // b|

		assertEquals('b', e.head.data);
		assertEquals('b', e.tail.data);
		assertEquals(1, e.getCursorPosition());
		assertEquals(1, e.size());
		assertNull(e.cur);
		
	}
	@Test
	void testDeleteFront() {
		genericConstruct();
		 loadEditorBLUE(e);
		 e.curPos =0;
		 e.cur = e.head;
		//|blue
		 
		 e.delete();  //|lue
		 
		 assertEquals("lue", e.toString());
		 assertEquals('l', e.head.data);
		 assertEquals(0, e.getCursorPosition());
		 assertEquals('e', e.tail.data);
		 assertEquals(3 ,e.size());
		 assertEquals('l', e.cur.data);	 	 
	}
	
	@Test
	void testDeleteEnd() {
		genericConstruct();
		loadEditorBLUE(e);
		e.curPos = 3;
		e.cur = e.tail;
		// blu|e
		e.delete();
		assertEquals("blu", e.toString());
		assertEquals('u', e.tail.data);
		assertEquals(3, e.getCursorPosition());
		assertNull(e.cur);
		assertEquals(3 ,e.size());
		
		
		e.curPos = 1; //b|lu
		e.cur = e.tail.prev;
		e.delete(); //b|u
		assertEquals("bu", e.toString());
		assertEquals('u', e.tail.data);
		assertEquals('u', e.cur.data);	
		
		e.moveToTail();
		e.delete(); // nothing should happen bc cur is null
		assertEquals("bu", e.toString());
		
	}
	
	@Test
	void testDeleteSingleFile() throws FileNotFoundException{
		// delete first node
		fileConstructorSingle();
		e.moveToHead();
		e.delete();
		assertEquals(" am a single line file.", e.toString());
		assertEquals(' ', e.head.data);
		assertEquals(0, e.getCursorPosition());
		assertEquals('.', e.tail.data);
		assertEquals(23 ,e.size());
		assertEquals(' ', e.cur.data);
		
		// delete from middle
		fileConstructorSingle();
		e.moveLeft();
		e.moveLeft();
		e.moveLeft();
		e.delete();
		assertEquals("I am a single line fie.", e.toString());
		assertEquals(21, e.getCursorPosition());
		assertEquals('e', e.cur.data);
		
		// delete last node
		fileConstructorSingle();
		e.moveLeft();
		e.delete();
		assertEquals("I am a single line file", e.toString());
		assertEquals(23, e.getCursorPosition());
		assertNull(e.cur);
	}
	
	@Test
	void testDeleteMultiFile() throws FileNotFoundException{
		// delete first node
		fileConstructorMulti();
		e.moveToHead();
		e.delete();
		assertEquals(" am a" + "\n" + "multiline" + "\n" + "file.", e.toString());
		assertEquals(' ', e.head.data);
		assertEquals(0, e.getCursorPosition());
		assertEquals('.', e.tail.data);
		assertEquals(21 ,e.size());
		assertEquals(' ', e.cur.data);
		
		// delete from middle
		fileConstructorMulti();
		e.moveLeft();
		e.moveLeft();
		e.moveLeft();
		e.delete();
		assertEquals("I am a" + "\n" + "multiline" + "\n" + "fie.", e.toString());
		assertEquals('e', e.cur.data);
		
		// delete last node
		fileConstructorMulti();
		e.moveLeft();
		e.delete();
		assertEquals("I am a" + "\n" + "multiline" + "\n" + "file", e.toString());
		assertNull(e.cur);
	}

	@Test
	void testBackspaceMultipleMiddle() {
		genericConstruct();
		// create editor storing "blue"
		loadEditorBLUE(e);

		// point cur after 'u'
		e.cur = e.tail;
		e.curPos = 3;

		// backspace 3x, curPos moves from 2, 1, 0
		e.backspace();

		assertEquals('b', e.head.data);
		assertEquals('e', e.tail.data);
		assertEquals(2, e.getCursorPosition());
		assertEquals(3, e.size());
		assertEquals('e', e.cur.data);

		e.backspace();

		assertEquals('b', e.head.data);
		assertEquals('e', e.tail.data);
		assertEquals(1, e.getCursorPosition());
		assertEquals(2, e.size());
		assertEquals('e', e.cur.data);

		e.backspace();

		assertEquals('e', e.head.data);
		assertEquals('e', e.tail.data);
		assertEquals(0, e.getCursorPosition());
		assertEquals(1, e.size());
		assertEquals('e', e.cur.data);
	}

	@Test
	void testBackspaceSingleFile() throws FileNotFoundException{
		fileConstructorSingle();
		e.moveToHead();
		e.backspace(); // should be ignored
		assertEquals("I am a single line file.", e.toString());
		
		// backspace first node
		e.moveRight();
		e.backspace();
		assertEquals(" am a single line file.", e.toString());
		assertEquals(0, e.getCursorPosition());
		assertEquals(' ', e.cur.data);
		
		
		// backspace middle node
		e.moveRight();
		e.moveRight();
		e.backspace();
		assertEquals(" m a single line file.", e.toString());
		assertEquals(1, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		
		// backspace last node
		fileConstructorSingle();
		e.backspace();
		assertEquals("I am a single line file", e.toString());
		assertNull(e.cur);
		assertEquals(e.getCursorPosition(), e.numChars);
	}
	
	@Test
	void testBackSpaceMultiFile() throws FileNotFoundException{
		fileConstructorMulti();
		e.moveToHead();
		e.backspace(); // should be ignored
		assertEquals("I am a" + "\n" + "multiline" + "\n" + "file.", e.toString());
		
		// backspace first node
		e.moveRight();
		e.backspace();
		assertEquals(" am a" + "\n" + "multiline" + "\n" + "file.", e.toString());
		assertEquals(0, e.getCursorPosition());
		assertEquals(' ', e.cur.data);
		
		
		// backspace middle node
		e.moveRight();
		e.moveRight();
		e.backspace();
		assertEquals(" m a" + "\n" + "multiline" + "\n" + "file.", e.toString());
		assertEquals(1, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		
		
		// backspace last node
		fileConstructorMulti();
		e.backspace();
		assertEquals("I am a" + "\n" + "multiline" + "\n" + "file", e.toString());
		assertNull(e.cur);
		assertEquals(e.getCursorPosition(), e.numChars);
	}

	
	@Test
	void testMultiLineFileConstruct() throws FileNotFoundException{
		fileConstructorMulti();
		assertEquals("I am a" + "\n" + "multiline" + "\n" + "file.", e.toString());
		assertEquals(22, e.numChars);
		assertEquals(22, e.getCursorPosition());
		assertNull(e.cur);
	}
	
	@Test 
	void testSave() {
		genericConstruct();
		loadEditorBLUE(e);
		e.cur = e.tail;
		e.curPos = 3;
		e.save();
		assertEquals("blue", e.getSavedVersion().top());
		
		e.save();
		e.save(); // if save is called multiple times with no change, should not be pushed to the stack
		assertEquals(1, e.getSavedVersion().size);
		
		e.moveRight();
		e.insert('!');
		e.save();
		assertEquals(2, e.getSavedVersion().size);
		assertEquals("blue!", e.getSavedVersion().top());
		
		e.backspace();
		e.save();
		assertEquals(3, e.getSavedVersion().size());
		assertEquals("blue", e.getSavedVersion().top());
	}

	@Test
	void testSave2() throws FileNotFoundException {
		fileConstructorSingle();
		e.save();
		assertEquals(1, e.getSavedVersion().size());
		assertEquals("I am a single line file.", e.getSavedVersion().top());
		assertEquals(e.toString(), e.getSavedVersion().top());
		
		e.clear();
		e.insert('a');
		e.save();
		assertEquals(2, e.getSavedVersion().size());
		assertEquals("a", e.getSavedVersion().top());
		
		fileConstructorMulti();
		e.insert('!');
		e.save();
		assertEquals("I am a" + "\n" + "multiline" + "\n" + "file.!", e.getSavedVersion().top());
		
		e.moveToHead();
		e.insert('!');
		e.save();
		assertEquals("!I am a" + "\n" + "multiline" + "\n" + "file.!", e.toString());
		assertEquals(e.toString(), e.getSavedVersion().top());
		assertEquals(2, e.getSavedVersion().size());
	}
	
	@Test 
	void testUndo() {
		genericConstruct();
		loadEditorBLUE(e);
		e.cur = e.tail.prev;
		e.curPos = 2;
		// bl|ue
		
		e.save();
		e.insert('@');
		e.undo();
		assertEquals("blue", e.toString());
		assertEquals(4, e.getCursorPosition());
		assertNull(e.getSavedVersion().top());
		
		
		//blue|
		e.backspace();
		e.insert('*');
		e.save();
		// saved versions: blu* 
		e.insert('%');
		e.insert('!');
		// editor: blu*%!
		assertEquals("blu*%!", e.toString());
		e.undo();
		assertEquals("blu*", e.toString());
		assertEquals(4, e.getCursorPosition());
		
		e.save();
		// saved versions: blu*
		assertEquals("blu*", e.getSavedVersion().top());
		
		//blu*
		e.backspace();
		e.insert('!');
		e.moveToHead();
		e.insert('a');
		assertEquals("ablu!", e.toString());
		
		// saved versions: ablu! , blu*
		e.save();
		e.insert('@');
		// editor: a@blu!
		e.save();
		
		// saved versions: a@blu! , ablu! , blu*
		
		assertEquals(3, e.getSavedVersion().size());
		assertEquals("a@blu!", e.toString());
		e.insert('~');
		
		e.undo();
		assertEquals("a@blu!", e.toString());
		
		e.undo();
		assertEquals("ablu!", e.toString());
		
		e.undo();
		assertEquals("blu*", e.toString());
		
		e.undo(); // should do nothing
		assertEquals("blu*", e.toString());

	}
	
	@Test
	void testFileConditions() throws FileNotFoundException {
		e = new Editor("empty.txt");
		assertEquals("", e.toString());
		assertEquals(0, e.getCursorPosition());
		assertNull(e.cur);
		
		e = new Editor("new_line.txt");
		assertEquals("test" + "\n" + "2" + "\n ", e.toString());
		
	}
	
	
	public static void loadEditorBLUE(Editor editor) {
		Node b = new Node('b');
		Node l = new Node('l');
		Node u = new Node('u');
		Node e = new Node('e');

		b.next = l;
		l.prev = b;
		l.next = u;
		u.prev = l;
		u.next = e;
		e.prev = u;

		editor.head = b;
		editor.tail = e;

		editor.numChars = 4;
	}

}

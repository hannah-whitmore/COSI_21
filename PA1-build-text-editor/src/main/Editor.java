/**
 * This class builds the editor functionality using a linked list 
 * Known bugs: not fully confident in my runtime analysis 
 * @author Hannah Whitmore
 * October 7, 2020
 * COSI 21A PA1
 */

package main;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;

public class Editor {

	public int numChars; /** KEEP THIS PUBLIC : use this to store the number of characters in your Editor */
	public int curPos; /** KEEP THIS PUBLIC : use this to store the current cursor index in [0, numChars] */
	
	public Node cur; /** KEEP THIS PUBLIC : use this to reference the node that is after the visual cursor or null if curPos = numChars */
	public Node head; /** KEEP THIS PUBLIC : use this to reference the first node in the Editor's doubly linked list */
	public Node tail; /** KEEP THIS PUBLIC : use this to reference the last node in the Editor's doubly linked list */
	
	public Stack<String> savedVersions; /** KEEP THIS PUBLIC : use this to store the contents of the editor that are saved by the user */
	
	/***
	 * generic constructor  
	 * runtime: O(1): assigning pointers
	 */
	public Editor() {
		this.numChars = 0;
		this.curPos = 0;
		this.cur = null;
		this.head = null;
		this.tail = null;
		this.savedVersions = new Stack<String>();
	}
	
	/***
	 * constructor that populates the editor with the contents of a text file
	 * @param filepath, name of the text file
	 * @throws FileNotFoundException
	 * runtime: O(n) 
	 */
	
	// TODO: fix new line 
	// file could end with a new line
	public Editor(String filepath) throws FileNotFoundException {
		this();
		String chars = "";
		File file = new File(filepath);
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) { //O(n)
			chars += input.nextLine();
			if (input.hasNextLine()) {
				chars+= "\n";
			}		
		}
		
		
		for (int i=0; i<chars.length();i++) { //O(n) 
			insert(chars.charAt(i));
		}
	}

	/***
	 * @return the index of the cursor in the editor
	 * runtime: O(1) 
	 */
	public int getCursorPosition() {
		return this.curPos;
	}
	
	/***
	 * @return the number of characters stored in the editor
	 * runtime: O(1)
	 */
	public int size() {
		return this.numChars;
	}
	
	
	/**
	 * moving the cursor to the right by one character
	 * curPos should be incremented by 1, and cur should point to the next node to the right 
	 * runtime: O(1) 
	 */
	public void moveRight() {
		if (curPos < numChars) {  // will ignore when curPos = numChars
			this.curPos++;
			this.cur = this.cur.next;
		}
		
	}
	
	/**
	 * moving the cursor to the left by one character
	 * curPos should be decremented by 1, and cur should point to the prior node to the left 
	 * runtime: O(1)
	 */
	public void moveLeft() {
		if (curPos > 0) { // will ignore when curPos = 0
			if (this.curPos == this.numChars) {
				this.cur = this.tail;
			} else {
				this.cur = this.cur.prev;
			}
			this.curPos--;
			
		}
	}
	
	/**
	 * moving the cursor to before the first character
	 * curPos should be 0 and cur should point to the first node
	 * runtime: O(1) 
	 */
	public void moveToHead() {
		this.curPos = 0;
		this.cur = head;
		
	}
	/**
	 * moving the cursor to be after the last character
	 * curPos should = numChars and cur should point to null
	 * runtime: O(1) 
	 */
	public void moveToTail() {
		this.curPos = this.numChars;
		this.cur = null;
	}
	
	/**
	 * inserts a character before the node referenced by cur
	 * cur remains the same, and curPos is incremented by 1 
	 * @param c : character to be inserted 
	 * runtime: O(1) : all pointer / variable assignments and checking data 
	 * 
	 */
	public void insert(char c) {
		Node newNode = new Node(c);
		
		if (this.curPos == 0) { // c is the new head
			if (this.numChars==0) { // if the list is currently empty and we insert the first element
				this.head = newNode;
				this.tail = newNode;
			} else {
				newNode.next = this.head;
				this.head.prev = newNode;
				this.head= newNode;
			}
			
		} else if (this.curPos == this.numChars) { // c is the new tail
				newNode.prev = this.tail;
				this.tail.next = newNode;
				this.tail = newNode;
				
		//b|lue
				
		} else {  // enter newNode after cur
			(this.cur.prev).next= newNode;		
			newNode.prev = this.cur.prev;
			newNode.next = this.cur;
			this.cur.prev = newNode;
		}
		this.curPos++;
		this.numChars++;
		
	}
	
	/**
	 * removes the node referenced by cur
	 * curPos remains the same and cur should point to the next node to the right 
	 * runtime: O(1) :  all pointer / variable assignments and checking data 
	 */
	public void delete() {
		if (this.cur==null) {	 // equal would mean the cursor has no following characters / cur is null
			return;
		}
		if (this.curPos == 0) { 	// cur is the first node 
			this.head = this.cur.next;
			(this.cur.next).prev = null;
			this.cur = this.cur.next;
			
			
		} else if (this.curPos == this.numChars-1) {	// cur is the last node
			this.tail = this.cur.prev;
			(this.cur.prev).next = null;
			this.cur = null;
			//b|e
			//b|
		} else {	 // cur is in the middle 
			(this.cur.prev).next = this.cur.next;
			(this.cur.next).prev = this.cur.prev;
			this.cur = this.cur.next;
		}
		this.numChars--;
			
		}	
		
	
	/**
	 * removes the node before the node referenced by cur
	 * curPos is decremented by 1 and cur remains the same
	 * runtime: O(1) : all pointer / variable assignments and checking data 
	 */
	public void backspace() {
		if (this.curPos > 0 ) { // equal would mean the cursor has no preceding characters to delete
			if (this.curPos == 1) { // backspacing the first node
				this.head = this.cur;
				if(this.cur!= null) {
					this.cur.prev = null;
				}
				
			} else if (this.curPos == this.numChars) { // backspacing the last node
			//null b null
				this.tail = this.tail.prev;
				if (this.tail!= null){
					this.tail.next = null;
				} 				
	
			} else { // backspacing in the middle
				(this.cur.prev.prev).next = this.cur;
				this.cur.prev = this.cur.prev.prev;
			}
			this.curPos--;
			this.numChars--;
		}
	}
	
	/**
	 * @return String concatenation of all the characters stored in the text editor
	 * runtime: O(n) : loops through list
	 */
	public String toString() {
		String result = "";
		Node pointer = this.head;
		while (pointer!=null) {
			result+= pointer.data;
			pointer = pointer.next;
		}
		return result;
	}
	
	/**
	 * removes all the characters stored in the text editor
	 * runtime: O(1) 
	 */
	public void clear() {
		this.head = null;
		this.numChars = 0;
		this.curPos = 0;
		this.cur = null;
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * exports the contents of the text editor to a file
	 * @param savepath: save path for the export file 
	 * @throws FileNotFoundException
	 * runtime: O(n) : loops through list
	 */
	public void export(String savepath) throws FileNotFoundException {
		PrintStream export = new PrintStream(new File (savepath));
		Node print = this.head;
		while (print != null) {
			export.print(print.data);
			print = print.next;
		}
	}
	
	/**
	 * saves the string of characters currently in the text editor
	 * pushes the string onto savedVersions stack
	 * runtime: O(n) because I call toString() which is O(n) ... otherwise would have been O(1) since 
	 * stack operations take constant time
	 * 
	 */
	public void save() {
		String curVersion = toString();
		if (this.savedVersions.isEmpty()) { 
			this.savedVersions.push(curVersion);
		} else {
			if (!this.savedVersions.top().equals(curVersion)) { //can only be pushed if it is different from most recently saved version 
				this.savedVersions.push(curVersion);
			}
		}
			
	}
	
	/**
	 * @return the stack containing the saved versions 
	 * runtime: O(1)
	 */
	public Stack <String> getSavedVersion(){
		return this.savedVersions;
	}
	
	/**
	 * reverts the contents of the text editor to the most recently saved version
	 * runtime: O(n) 
	 */
	public void undo() {
		if (!this.savedVersions.isEmpty()) {
			String curVersion = this.savedVersions.pop();
			clear();
			for (int i=0; i<curVersion.length();i++) {
				insert(curVersion.charAt(i));
			}
		}
	}

	
	
}

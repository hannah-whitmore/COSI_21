/**
 * This class builds a stack, which is used in the editor class to save versions 
 * Known bugs: possibly runtime analysis 
 * @author Hannah Whitmore
 * October 7, 2020
 * COSI 21A PA1
 */

package main;

public class Stack<T> {

	public T[] stack;
	public int size;
	
	/**
	 * generic constructor
	 * runtime: O(1) 
	 */
	@SuppressWarnings("unchecked")
	public Stack() {
		// uncomment this line and replace <your initial size goes here> with the initial capacity of your internal array
		this.stack = (T[]) new Object[10];
		this.size = 0;
		
		//[1, 2, 3, 4, 5]
		/*
		 * 5
		 * 4
		 * 3
		 * 2
		 * 1
		 * 
		 */
	}
	
	/**
	 *  
	 * @param x: element to be pushed onto the stack
	 * if the internal array is filled to capacity and push is called,
	 * ensureCapacity() is called to resize the internal array 
	 * runtime: O(n) if ensureCapacity() gets called, if not it's O(1) 
	 */
	//1, 2, 3, 4, 5, 0, 0, 0, 0, 0]
	public void push(T x) {
		if (size() == this.stack.length) {
			ensureCapacity();
		}
		this.stack[this.size] = x;
		this.size++;
	}
	
	/**
	 * resizing the array, making it twice as large
	 * runtime: O(n) 
	 */
	public void ensureCapacity() {
		 T[] newStack = (T[]) new Object[ this.size*2];
		 for (int i=0; i<this.size; i++) {
			 newStack[i] = this.stack[i];
		 }
		this.stack = newStack;

		
	}
	
	/**
	 * removes the top element from the stack and returns it 
	 * @return: top element 
	 * runtime: O(1) 
	 */
	// [1, 2, 3, 4, 5, 0, 0, 0, 0, 0]
	public T pop() {
		if (size ==0) {
			throw new IllegalStateException(); // there are no elements in the stack 
		}
		this.size--;
		T element = (T) this.stack[this.size];
		this.stack[this.size] = null;
		return element;
		
	}
	
	/**
	 * @return the element that is at the top of the stack 
	 * runtime: O(1)
	 */
	public T top() {
		if (isEmpty()) {
			return null;
		}
		T top = (T) this.stack[this.size-1];
		return top;
		
	}
	
	/**
	 * @return the number of elements in the stack 
	 * runtime: O(1) 
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * @return true if the stack is empty, false if not 
	 * runtime: O(1) 
	 */
	public boolean isEmpty() {
		if (this.size==0) {
			return true;
		} 
		return false;
	}
	
	/**
	 * @return a string representation of the contents of the stack 
	 * runtime: O(n) 
	 */
	
	/*
	 * [foo, bar]
	 * 
	 * bar
	 * foo
	 * 
	 * "bar\nfoo\n"
	 */
	public String toString() {
		String res = "";  
		for (int i=this.size-1; i>=0; i--) {
			res += this.stack[i] + "\n";
		}
		return res;
	}
	
	/**
	 * @return the internal array length (for testing purposes) 
	 */
	public int internalSize() {
		return this.stack.length;
	}
	
}

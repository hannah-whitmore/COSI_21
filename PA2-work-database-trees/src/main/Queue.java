/**
 * This class provides the functionality for a queue 
 * Known bugs: none
 * 
 * @auhtor Hannah Whitmore
 * hwhitmore@brandeis.edu
 * 10/8/20
 * COSI 21 PA2
 */

package main;

public class Queue<T> {
	
	public T[] queue;
	public int size;

	public Queue() {
		this.queue = (T[]) new Object[10];
		this.size = 0;
	}
	
	/**
	 * @param data to be added to the queue 
	 */
	public void enqueue(T data) {
		if (this.size == this.queue.length) {
			ensureCapacity();
		}
		this.queue[this.size] = data;
		this.size++;
	}
	
	/**
	 * if internal array is at capacity, need to resize
	 */
	public void ensureCapacity() {
		 T[] newQueue = (T[]) new Object[ this.size*2];
		 for (int i=0; i<this.size; i++) {
			 newQueue[i] = this.queue[i];
		 }
		this.queue = newQueue;
	}

	/**
	 * @return the element at the front of the queue, removes it from the queue 
	 */
	public T dequeue() {
		if (this.size ==0) {
			throw new IllegalStateException(); // there are no elements in the stack 
		}
		T element = (T) this.queue[0];
		for (int i=0; i<this.size; i++) {
			this.queue[i] = this.queue[i+1];
		}
		this.size--;
		
		return element;
	}
	
	/**
	 * @return the element at the front of the queue without removing it  
	 */
	public T front() {
		if (this.size ==0) {
			return null;
		}
		T front = (T) this.queue[0];
		return front;
	}
	
	/**
	 * @return number of elements in the queue 
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * @return true if the queue is empty, false otherwise 
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/**
	 * @return string representation of the queue 
	 */
	public String toString() {
		String res = "";
		for (int i=0; i<this.size; i++) {
			res += this.queue[i] + "\n";
			
		}
		return res;
	}
	
	/**
	 * @param index 
	 * @return the element in the queue at the index passed in
	 */
	public T getElement(int index) {
		return this.queue[index];
	}
	
	/**
	 * removes an element from the queue 
	 * @param index of where to remove 
	 */
	public void removeElement(int index) {
		 T[] newQueue = (T[]) new Object[this.queue.length - 1];
		
		// copy over elements from original to shifted up until index of removal
		for (int i = 0; i < index; i++) {
			newQueue[i] = this.queue[i];
		}
		
		// continues to copy over elements but skips over the index being removed
		for (int i= index; i< this.queue.length -1 ; i++) {
			newQueue [i] = this.queue[i+1];
		}	
		this.size--;
		this.queue = newQueue;
	}
	
	
	
}

/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * December 8, 2020
 * PA #3
 * Implements a min priority queue structure
 * Known bugs: none
 */

package main;

public class MinQueue {
	
	public MinHeap heap;
	public int size;
	
	public MinQueue() {
		this.heap = new MinHeap();
		this.size = 0;
	}
	
	/**
	 * enqueues a given graphNode into the priority queue
	 * @param g
	 */
	public void insert(GraphNode g) {
		this.heap.insert(g);
		this.size++;
	}
	
	/**
	 * extracts the element with the highest priority from the queue
	 * @return minimum priority graphNode
	 */
	public GraphNode pullHighestPriorityElement() {
		this.size--;
		return this.heap.extractMin();
	}
	
	/**
	 * calls heapifyUp/heapifyDown when the priority is changed & the heap needs to be rebalanced
	 * @param g
	 */
	public void rebalance(GraphNode g) {
		this.heap.heapify(g);
	}
	
	/**
	 * @return true if the queue is empty, false if not
	 */
	public boolean isEmpty() {
		return this.size==0;
	}
	
	/**
	 * returns whether or not a given graphNode is in the queue 
	 * @param g 
	 * @return true if in the queue, false if not
	 */
	public boolean isInQueue(GraphNode g) {
		return this.heap.isInHeap(g);
	}
}

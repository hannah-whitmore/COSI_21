/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * December 8, 2020
 * PA #3
 * Explanation of the class: implements a min heap structure
 * Known bugs: none
 */

package main;
import java.util.Arrays;

public class MinHeap {
	
	public HashMap map;
	public GraphNode [] heap;
	public int currCapacity;
	public int length;

	public MinHeap() {
		this.map = new HashMap();
		this.heap = new GraphNode[10];
		this.currCapacity = 0;
		this.length = 10;
	}
	
	/**
	 * @param i: index of the graphnode in the heap array 
	 * @return: the parent of that node 
	 */
	public int parent(int i) {
		double r = 1.0 * (i/2);
		int P = (int)r;
		return P; 
	}
	
	/**
	 * @param i: the index of the graphnode in the heap array 
	 * @return: the left child of that node
	 */
	public int left(int i) {
		return 2*i;
	}
	
	/**
	 * @param i: the index of the graphnode in the heap array
	 * @return: the right child of that node 
	 */
	public int right(int i) {
		return (2*i)+1;
	}
	
	/**
	 * method to swap any 2 nodes in the heap array 
	 * @param i: index of the first node 
	 * @param j: idex of the second node 
	 */
	public void swap(int i, int j) {
		GraphNode temp;
		temp = this.heap[i];
		this.heap[i] = this.heap[j];
		this.heap[j] = temp;
		this.map.set(this.heap[i], i);
		this.map.set(this.heap[j], j);
	}
	
	/**
	 * @param i: index of the node that violates heap property 
	 * node at i and it's children need to be moved to restore heap
	 */
	public void heapifyDown(int i) {
		int left = left(i);
		int right = right(i);
		int smallest = i;
		if (left<this.heap.length && this.heap[left]!= null) {
			if (left < this.currCapacity && this.heap[left].priority < this.heap[i].priority) {
				smallest = left;
			}
		}
		
		if (right<this.heap.length && smallest <this.heap.length && this.heap[right]!= null && this.heap[smallest]!= null) {
			if (right < this.currCapacity && this.heap[right].priority < this.heap[smallest].priority) {
				smallest = right;
			}
			
		}
		
		if (smallest != i) {
			swap(i, smallest);
			heapifyDown(smallest);
		}
	}
	
	/**
	 * @param i: index of the node that violates heap property 
	 * node and i and its parent need to be moved to restore heap
	 */
	public int heapifyUp(int i) {
	
		if (this.heap[parent(i)]!= null && this.heap[i]!=null) {
			while (i > 0 && this.heap[parent(i)].priority > this.heap[i].priority) {
				swap(i, parent(i));
				i = parent(i);
			}
		}
		return i;
	}
	
	/**
	 * inserts a node into the heap at the bottom and calls heapifyUp 
	 * @param g: node to insert
	 */
	public void insert(GraphNode g) {
		if (this.currCapacity==this.heap.length) {
			// need to make the heap array bigger
			GraphNode [] biggerHeap = new GraphNode[this.heap.length*2];  
			for (int i =0; i<this.currCapacity; i++) {
				biggerHeap[i] = this.heap[i];
			}
			this.heap = biggerHeap;
			
		}
		this.heap[this.currCapacity] = g;
		int i = heapifyUp(this.currCapacity);
		this.map.set(g, i);
		this.currCapacity++;
	}
	
	/**
	 * finds and returns the minimum node, but does not remove it
	 * @return: minimum node 
	 */
	public GraphNode findMin() {
		if (this.currCapacity > 0) {
			return this.heap[0];
		}
		System.out.println("heap underflow");
		return null;
	}
	
	/**
	 * @return true if the heap is empty, false otherwise 
	 */
	public boolean isEmpty() {
		return this.currCapacity == 0;
	}
	
	/**
	 * returns and removes the minimum node
	 * @return: minimum node 
	 */
	public GraphNode extractMin() {
		if (this.currCapacity > 0) {
			GraphNode head = this.heap[0];
			
			// shift array with deleted entry
			GraphNode[] newArr = new GraphNode[this.heap.length];
			for (int i=1; i<this.heap.length; i++){
				newArr[i-1] = this.heap[i];
			}
			
			this.heap = newArr;
			this.currCapacity--;
			heapifyDown(0);
			return head;
		}
		System.out.println("heap uderflow");
		return null;
	}
	
	/**
	 * calls heapifyUp and heapifyDown
	 * @param g: graphnode that needs to be shifted within the heap 
	 */
	public void heapify(GraphNode g) {
		int i = this.map.getValue(g);  
		heapifyUp(i);
		heapifyDown(i);
	}
	
	/**
	 * checks if a given graphnode is in the heap -- useful to check if something is in the priority queue 
	 * @param g: graphnode
	 * @return true if the graphnode is in the heap, false if not  
	 */
	public boolean isInHeap(GraphNode g) {
		return (this.map.getValue(g)!= -1);
	}	
}


/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * December 8, 2020
 * PA #3
 * Explanation of the class: holds the key/value pair used in the map
 * Known bugs: none
 */
package main;

public class Entry {
	
	GraphNode key;
	int value;
	
	public Entry (GraphNode key, int value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * returns the key field of this entry
	 * @return graphNode key
	 */
	
	public GraphNode getKey() {
		return this.key;
	}
	
	/**
	 * returns the value field of this entry 
	 * @return the integer value 
	 */
	public int getValue() {
		return this.value;
	}
	
	
}


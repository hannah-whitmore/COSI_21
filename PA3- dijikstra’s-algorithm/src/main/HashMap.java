/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * December 8, 2020
 * PA #3
 * Implements a hash map structure
 * Known bugs: none
 */

package main;

public class HashMap {
	
	public Entry [] map;
	public int length; // length of internal array
	public int currCapacity; // number of slots occupied
	
	public HashMap() {
		this.map = new Entry[10]; 
		this.length = 10;
		this.currCapacity = 0;
	}
	
	/**
	 * @return length of the internal array 
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * @return true if the map is empty, false otherwise
	 */
	public boolean isEmpty() {
		return this.length == 0;
	}

	
	/**
	 * function to take in the String UUID and convert it to an int -- gets the bytes and adds them together 
	 * @param key: graphnode to insert
	 * @return: the graphnode's ID as an int
	 */
	public int getKeyAsInt(GraphNode key) {
		String id = key.getId();
		byte[] array1 = id.getBytes();
		int intKey=0;
		for(byte b: array1){
			intKey+=b;
		}
		return intKey;
	}
	
	/**
	 * adds a given key value pair to the map
	 * @param key
	 * @param value
	 */
	public void set(GraphNode key, int value) {
		int intkey = getKeyAsInt(key);
		int bucket = intkey % getLength();
		int i = 0;
		int j = 0;
		do {
			j = (bucket + i) % this.length;
			if (map[j] == null || this.map[j].value == -1) {
				Entry e = new Entry(key, value);
				this.map[j] = e;
				this.currCapacity++;
				
				// if load factor is >= 0.5, rehash 
				double loadFatcor = (1.0 * this.currCapacity)/this.length;
				if (loadFatcor >= 0.5) {
					rehash();
				}
				return;
			} else {
				i++;
			}
		} while (i < this.length);
	}
	
	/**
	 * deletes a given graphNode from the map
	 * sets the value of this key to be -1
	 * @param g: graphNode to be deleted
	 */
	public void deleteEntry(GraphNode g) {
		int val = getValue(g);
		if (val != -1) {
			for (int i=0; i<this.length; i++) {
				if (this.map[i]!= null) {
					if (this.map[i].key.equals(g)) {
						this.map[i].value = -1;
					}
				}
				
			}
		}
	}
	
	/**
	 * rehashes the table with length * 2
	 * copies over the entries 
	 */
	public void rehash() {
		Entry [] temp = this.map; // store the current map in temp
		
		// make a new map twice the size and assign this to it --
		// makes this map empty
		Entry [] newMapEntries = new Entry[this.length*2]; 
		this.map = newMapEntries;
		
		this.length = this.length*2;
		this.currCapacity=0;
				
		// get each entry from the original, and rehash it into the bigger map with the set method 
		for (int i=0; i<temp.length; i++) {
			if (temp[i]!= null) {
				Entry e = temp[i];
				set(e.key, e.value);
			}
		}
	}
	
	/**
	 * returns the value corresponding to a given key 
	 * @param g: key 
	 * @return: corresponding int value
	 */
	public int getValue(GraphNode g) {
		for (int i=0; i<this.length; i++) {
			if (this.map[i]!= null) {
				if (this.map[i].key.equals(g)) {
					return this.map[i].value;
				}
			}
		}
		return -1; // if key is not in map
	}
	
	/**
	 * checks if the map contains an entry with a given key 
	 * @param g: the key
	 * @return: true if contained, false otherwise
	 */
	public boolean hasKey(GraphNode g) {
		for (int i=0; i<this.length; i++) {
			if (this.map[i]!= null) {
				if (this.map[i].key.equals(g)) {
					return true;
				}
			}
		}
		return false;
	}

}

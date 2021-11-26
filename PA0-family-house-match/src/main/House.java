/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * August 31, 2020
 * PA #0
 * Explanation of the program/class: sets up a House object, assigns fields, and has getters/setters for these fields
 * Known bugs:
 */

package main;

public class House {
	
	private int rooms;
	private int price;
	private boolean petsAllowed;
	private boolean assigned; // keeps track of whether this house has been assigned to a family or not 
							// in assignFamiliesToHomes in main, this field makes it easy to skip over houses
								// that have already been assigned a family 
	
	/**
	 * Creates a House object with the given number of rooms, price, and whether pets are allowed
	 * @param rooms: the number of rooms in the House
	 * @param price: the price of the House
	 * @param petsAllowed: true if the House allows pets, false otherwise 
	 */
	
	public House(int rooms, int price, boolean petsAllowed) {
		if (rooms<0) {
			System.out.println("house cannot have negative number of rooms.");
		} else {
			this.rooms = rooms;
		}
		if (price<0) {
			System.out.println("house cannot have negative price");
		} else {
			this.price = price;
		}
		this.petsAllowed = petsAllowed;
		this.assigned = false;
	}
	
	/**
	 * returns the number of rooms in this House
	 * @return an integer representing the number of rooms 
	 */
	public int getRooms() {
		return this.rooms;
	}
	
	/**
	 * returns the price of this House
	 * @return an integer representing the price of this House
	 */
	public int getPrice() {
		return this.price;
	}
	
	/**
	 * returns whether pets are allowed in this House
	 * @return true if pets are allowed, false otherwise 
	 */
	public boolean petsAllowed() {
		return this.petsAllowed;
	}
	
	/**
	 * sets assigned field to true to ensure a house does not get assigned to multiple families
	 */
	public void setAssigned() {
		this.assigned = true;
	}
	
	/**
	 * 
	 * @return: the assignment status of a given house 
	 * when iterating over houses in assignFamiliesToHomes, only if !(house.getAssignment()) will the house be considered
	 */
	
	public boolean isAssigned() {
		return this.assigned;
	}
	
	/**
	 * returns a String representation of this House including the number of rooms, the price, and whether pets are allowed
	 * @return a String representation of this House
	 */
	@Override
	public String toString() {
		if (this.petsAllowed == true) {
			return "This house has " + this.rooms + " rooms, it costs $" + this.price + ", and pets are allowed.";
		} else {
			return "This house has " + this.rooms + " rooms, it costs $" + this.price + ", and pets are not allowed.";
		}
		
	}
}


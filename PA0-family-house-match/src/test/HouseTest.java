/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * August 31, 2020
 * PA #0
 * Explanation of the program/class: sets up a Person object: assigns fields, has getters/setters for the object
 * Known bugs: none that I know of :)
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.House;

class HouseTest {
	
	House casa = new House(4, 150000, true);
	House ranch = new House(2, 400000, false); 

	@Test
	void testHouseInit() {
		assertEquals(4, casa.getRooms());
		assertEquals(150000, casa.getPrice());
		assertEquals(false, ranch.petsAllowed());
		
	}
	
	@Test
	void testToString() {
		assertEquals("This house has 4 rooms, it costs $150000, and pets are allowed.", casa.toString());
	}

}

/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * August 31, 2020
 * PA #0
 * Explanation of the program/class: tests the Person class
 * Known bugs: none that I know of :)
 */


package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Pet;

class PetTest {
	
	Pet Max = new Pet("Max", "Dog", 2);
	Pet Gary = new Pet("Gary", "SnAiL", 7);
	Pet Fluffy = new Pet("Fluffy", "cAt", 13); // tests different capitalizations 
	Pet test = new Pet("test", "negative", -9); // shoudn't assign a negative age to animal 

	@Test
	void testPetInit() {
		assertEquals("Max", Max.getName());
		assertEquals(7, Gary.getAge());
		assertEquals("cAt", Fluffy.getSpecies());
		assertEquals(0, test.getAge());
	}
	
	@Test
	void testMakeSound() {
		assertEquals("meow!", Fluffy.makeSound());
		assertEquals("bark!", Max.makeSound());
		assertEquals("squak!", Gary.makeSound());
	}

	@Test
	void testToString() {
		assertEquals("This animal is a SnAiL and its name is Gary.", Gary.toString());
	}
}

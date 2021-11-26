/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * August 31, 2020
 * PA #0
 * Explanation of the program/class: sets up the Pet object, initializes fields, has setters/getters, etc.
 * Known bugs: none that I know of :) 
 */

package main;

public class Pet {
	
	private String name;
	private String species;
	private int age;
	
	/**
	 * Creates a Pet object with the given name, species, and age
	 * @param name: the name of the Pet to be created
	 * @param name: the name of the species of the Pet to be created 
	 * @param age: the age of the Pet to be created
	 */
	public Pet(String name, String species, int age) {
		this.name = name; 
		this.species = species;
		if (age<0) {
			System.out.println("Age cannot be a negative number.");
		} else {
			this.age = age;
		}
	}
	
	/**
	 * returns the name of this Pet 
	 * @return a String representing the name of this Pet
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * returns the species of this Pet
	 * @return a String representing the species of this Pet
	 */
	public String getSpecies() {
		return this.species;
	}
	
	/**
	 * returns the age of this Pet
	 * @return an integer representing the age of this Pet
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * returns the string said by this Pet when they speak
	 * @return String representation of what this Pet says
	 */
	public String makeSound() {
		if (this.species.toLowerCase().equals("cat")) {
			return "meow!";
			
		} else if (this.species.toLowerCase().equals("dog")) {
			return "bark!";
		} else {
			return "squak!";
		}
	}
	
	/**
	 * returns a String representation of this Pet including its name, species, age, and the sound it makes
	 * @return a String representation of this Pet
	 */
	
	@Override
	public String toString() {
		return "This animal is a " + this.species + " and its name is " + this.getName() + ".";
		
	}
}


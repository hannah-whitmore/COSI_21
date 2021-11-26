/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * August 31, 2020
 * PA #0
 * Explanation of the program/class: sets up a Person object: assigns fields, has getters/setters for the object
 * where information like age or salary for each person would be retrieved 
 * Known bugs: none that I know of :)
 */

package main;

/**
 * NOTE: This class has provided sample Javadoc comments for you. 
 *       In all other classes you must include your own Javadocs.
 *
 */
public class Person {
	
	private String name;
	private int age;
	private int salary;
	
	/**
	 * Creates a Person object with the given name, age, and salary
	 * @param name: the name of the Person to be created
	 * @param age: the age of the Person to be created
	 * @param salary: the salary of the Person to be created
	 */
	public Person(String name, int age, int salary) {
		this.name = name;
		if (age<0) {
			System.out.println("Age must be greater than zero.");
		} else {
			this.age = age;
		}
		if (salary<0) {
			System.out.println("Salary cannot be a negative number.");
		} else {
			this.salary = salary;
		}
	}
	
	/**
	 * returns the name of this Person 
	 * @return a String representing the name of this Person
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * returns the age of this Person
	 * @return an integer representing the age of this Person
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * returns the salary of this Person
	 * @return an integer representing the salary of this Person
	 */
	public int getSalary() {
		return this.salary;
	}
	
	/**
	 * returns the string said by this Person when they speak
	 * @return String representation of what this Person says
	 */
	public String speak() {
		if (this.age<=18) {
			return "I want a bigger house!";
		} else {
			return "This house does not have enough rooms to accommodate my family. I would like my family to be assigned a house with more rooms.";
		}
	}
	
	/**
	 * returns a String representation of this Person, including name, age, and salary 
	 * @return a String representation of this Person 
	 */
	@Override
	public String toString() {
		return "This person's name is " + this.name + " and their salary is $" + this.salary + ".";
	}
}


/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * August 31, 2020
 * PA #0
 * Explanation of the program/class: driver of the program -- reads from the text files to get info about all of the 
 * families and houses, also contains the main algorithm that assigns families to houses 
 * Known bugs: none that I know of :) 
 */

package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import main.Family;
import main.House;


public class Main {
	
	public static Family[] f;
	public static House[] h;
	public static boolean[][] assignments;

	// opens the files and calls each method in main
	public static void main(String[] args) throws FileNotFoundException{
		File file1 = new File("familyUnits.txt");
		Scanner scanner = new Scanner(file1);
		createFamilies(scanner);
		File file2 = new File("housingUnits.txt");
		Scanner scanner2 = new Scanner(file2);
		createHomes(scanner2);
		assignFamiliesToHomes();
		displayAssignments();
		for (int i=0; i<f.length; i++) {
			checkAssignment(i);
		}
	}
	
	/**
	 * reads from the familyUnits.txt file and populates the Family array with families and each family with family members
	 * @param s: scanner to read file
	 */
	public static void createFamilies(Scanner s) {
		while (s.hasNext()) {
			int numOfFamilies = s.nextInt();
			f = new Family[numOfFamilies]; // array of type Family  
			for (int i=0; i<f.length; i++) {
				int numOfPeoplePerFamily  = s.nextInt();
				int numOfPetsPerFamily = s.nextInt();
				Family fam = new Family(numOfPeoplePerFamily, numOfPetsPerFamily); // creates each family instance
				for (int j=0; j<numOfPeoplePerFamily; j++) {
					String personName = s.next();
					int personAge = s.nextInt();
					int personSalary = s.nextInt();
					Person per = new Person(personName, personAge, personSalary); // creates each person instance 
					fam.addMember(per); // adds each person to their family 
				}
				for (int j=0; j<numOfPetsPerFamily; j++) { // if there are no pets, this loop won't execute 
					String petName = s.next();
					String petSpecies = s.next();
					int petAge = s.nextInt();
					Pet p = new Pet(petName, petSpecies, petAge); // creates each pet instance
					fam.addPet(p); // adds each pet to their family 
				}
				f[i] = fam; // family is complete, add to array of families 
			}
		}
	}
	
	/**
	 * reads from the housingUnits.txt file and populates the House array with each house
	 * @param s: scanner to read the file 
	 */

	public static void createHomes(Scanner s) {
		while (s.hasNext()) {
			int numOfHouses = s.nextInt();
			h = new House[numOfHouses]; // array of type House 
			for (int i=0; i<h.length; i++) {
				int numOfRooms =  s.nextInt();
				int cost = s.nextInt();
				boolean petsAllowed = s.nextBoolean();
				House hous = new House(numOfRooms, cost, petsAllowed);	// creates each house instance 
				h[i] = hous; // house is complete, add to array of houses 
			}
		}
	}
	
	/**
	 * compares fields of the families and homes and assigns homes to families 
	 */
	public static void assignFamiliesToHomes() {
		assignments = new boolean[f.length][h.length];
		for (int i=0; i<assignments.length; i++) { // families
			for (int j=0; j<assignments[i].length; j++) { // houses 
				if (!(h[j].isAssigned()) && f[i].canBuy(h[j]) && !(f[i].isAssigned()) ) { // house has not been assigned yet,  family can buy this house, and family doesn't have a house yet
					assignments[i][j] = true;
					h[j].setAssigned(); // sets the assigned field of this house to true so that no house gets assigned to more than one family
					f[i].setAssigned(); // ^ same for families, keeping track of which have been matched
					f[i].setHouse(h[j]); // keeps track of which house has been assigned to which family 
				}
			}
		}
	}
	
	/**
	 * prints all the assignments created by assignFamiliesToHomes
	 */
	public static void displayAssignments() {
		for (int i=0; i<assignments.length; i++) {
			System.out.println("Family #" + (i+1) + ": ");
			System.out.println(f[i].toString());
			if (!(f[i].getAssigned())) {
				System.out.println("This family has not been assigned a house.");
			} else {
				System.out.println("This family has been assigned a house: ");
				System.out.println(f[i].getHouseInfo());
			}
			System.out.println('\n');
		}
	
	}
	
	/**
	 * checks that the assignment given to the family at index familyIndex of the Family array is valid
	 * prints messages if a wrong assignment has been made
	 * @param familyIndex
	 */
	public static void checkAssignment(int familyIndex) {
		if (f[familyIndex].getAssigned()) { // if this family has been assigned a house, double check if house is suitable
			if (f[familyIndex].numberOfPeople()> f[familyIndex].getHouse().getRooms()) {  // more people in the family than rooms in the house
				Person [] members = f[familyIndex].getPeople();
				for (int i=0; i<members.length; i++) {
					System.out.println(members[i].speak());
				}
				
			}
			// if the family has pets and their house does not allow pets
			if (f[familyIndex].numberOfPets()>0 && !(f[familyIndex].getHouse().petsAllowed())) {
				Pet [] pets = f[familyIndex].getPets();
				for (int i=0; i<pets.length; i++) {
					System.out.println(pets[i].makeSound());
				}
			}
			// if the family's combined salary is less than the price of the house 
			if (f[familyIndex].getBudget()<f[familyIndex].getHouse().getPrice()) {
				System.out.println("House over budget!");
			}
			
			// if a family has been assigned to more than one house 
			if (f[familyIndex].getCount()>1) {
				System.out.println("Family assigned to more than one house");
			}	
		}	
	}
}


/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * August 31, 2020
 * PA #0
 * Explanation of the program/class: sets up a Family object, which contains information about the members of
 * a family, their pets, their budget, and which house they have been assigned, if any 
 * Known bugs: none that I know of :)
 */

package main;
import main.House;

public class Family {
	
	private Person[] familyMembers;
	private Pet[] familyPets;
	private int budget;
	private int countMembers;
	private int countPets;
	private boolean assignedHouse;
	private House house;
	private int count;

	/**
	 * Creates a Family object with the given array of People and array of Pets
	 * @param Person[]: array of type Person, representing the people in a given Family 
	 * @param Pet[]: array of type Pet, representing the Pets in a given Family 
	 */
	
	public Family(int humans, int pets) {
		this.familyMembers = new Person[humans];
		this.familyPets = new Pet[pets];
		this.countMembers =0;
		this.countPets = 0;	
		this.assignedHouse = false;
		this.budget = 0;
	}
	
	/**
	 * returns the array of all the People in this Family
	 * @return a Person array with each element representing each Person in this Family 
	 */
	public Person [] getPeople() {
		return this.familyMembers;
	}
	

	/**
	 * returns the array of all the Pets in this Family
	 * @return a Pet array with each element representing each Pet in this Family
	 */
	public Pet [] getPets() {
		return this.familyPets;
	}
	

	/**
	 * returns the budget of this Family
	 * @return an integer representing the sum of all the salaries of the People
	 */
	
	public int getBudget() {
		return budget;
	}
	
	/**
	 * returns the total number of people that will be in the Family
	 * @return an integer representing the number of People in this Family 
	 */
	public int numberOfPeople() {
		return this.familyMembers.length;
	}
	
	/**
	 * returns the total number of pets that will be in the family once all of the pets are added
	 * @return an integer representing the number of Pets in this Family
	 */
	
	public int numberOfPets() {
		return this.familyPets.length;
	}
	
	/**
	 * adds the Person to the Family if this is possible 
	 * @param p: a given Person
	 * @return true if the person was added to the Family, false otherwise
	 */
	public boolean addMember(Person p) {
		if (this.countMembers< this.familyMembers.length) {
			this.familyMembers[countMembers] = p;
			countMembers++;
			this.budget+= p.getSalary();
			return true;
		}  else {
			return false;
		}
	}
	
	/**
	 * adds the Pet to the Family if this is possible
	 * @param p: a given Pet
	 * @return true if the pet was added to the Family, false otherwise 
	 */
	public boolean addPet(Pet p) {
		if (this.countPets < this.familyPets.length) {
			this.familyPets[countPets] = p;
			countPets++;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * checks if a given house is suitable for a given family based on the requirements of size, price, and pets
	 * num of people in family <= num of rooms in house
	 * combined salary of family >= price of house 
	 * pets must be allowed for fam with pets, if fam has no pets it doesn't matter
	 * @param h: a house 
	 * @return if a house meets a family's requirements, return true, and the pairing will be assigned in main
	 */
	public boolean canBuy(House h) { 
		if (numberOfPeople()<=h.getRooms() && (getBudget()>=h.getPrice())){
			if ((numberOfPets()>0 && h.petsAllowed()) ||numberOfPets()==0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * sets the assignedHouse field to true when a family has been assigned a house,
	 * increments a count variable to make sure for each instance of a family, count is always
	 * either 0 or 1 -- family cannot be assigned to more than 1 house 
	 */
	public void setAssigned() {
		this.assignedHouse = true;
		count++;
	}
	
	/**
	 * keeps track of if a family has been assigned a house yet 
	 * @return: boolean representing if a family has been assigned a house 
	 */
	public boolean isAssigned() {
		return this.assignedHouse;
	}
	
	/**
	 * 
	 * @return true if family has been assigned a house, false if not 
	 */
	public boolean getAssigned() { 
		return this.assignedHouse;
	}
	
	/**
	 * keeps track of the paring between families and houses for later reference 
	 * @param h: house set to this family instance 
	 */
	public void setHouse(House h) {
		this.house =h;
	}
	
	/**
	 * gets the info about the house assigned to this family 
	 * @return: this house in string format 
	 */
	public String getHouseInfo() {
		return this.house.toString();
	}
	
	/**
	 *
	 * @return: the house assigned to this family 
	 */
	public House getHouse() {
		return this.house;
	}
	
	// note: the above added helper methods were used mainly for the checkAssignments method 
	
	/**
	 * count increments when a family has been assigned a house to ensure no multiple assignments 
	 * @return: count
	 */
	public int getCount() {
		return this.count;
	}
	
	
	/**
	 * returns a String representation of this Family, including information about each family member and each pet
	 * @return a String representing this Family
	 */
	public String toString() {
		String family = "";
		family+= "There are " + countMembers + " people in this family." + '\n';
		for (int i=0; i<this.countMembers; i++) {
			family += "Member #" + (i+1) + ": " + this.familyMembers[i].toString() + '\n';
		}
		family+= "This family's budget is: $" + getBudget() + "." + '\n';
		if (countPets == 0) {
			family+= "This family has no pets." + '\n';
		} else {
			family+= "There are " + countPets + " pets in this family." + '\n';
		}
		for (int i=0; i<this.countPets; i++) { 
			family+= "Pet #" + (i+1) + ": " + this.familyPets[i].toString() + '\n';
		}
		return family;		
	}
	
	
	
}


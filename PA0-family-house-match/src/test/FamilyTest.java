/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * August 31, 2020
 * PA #0
 * Explanation of the program/class
 * Known bugs:
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Family;
import main.Pet;
import main.Person;

class FamilyTest {
	
	Family Cohen = new Family(4, 1);
	Person Ilanna = new Person("Ilanna", 23, 30000);
	Person Abbi = new Person("Abbi", 30, 60000);
	Person Ted = new Person("Ted", 5, 0);
	Person Charles = new Person("Charles", 18, 10000);
	Person Lily = new Person("Lily", 18, 0);
	Pet Hedwig = new Pet("Hedwig", "owl", 7);
	Pet Crookshanks = new Pet("Crookshanks", "cat", 7);

	@Test
	void testFamilyInit() {
		assertEquals(4, Cohen.numberOfPeople());
		assertEquals(1, Cohen.numberOfPets());
	}
	
	@Test
	void testAddMember() {
		assertEquals(true, Cohen.addMember(Ilanna));
		assertEquals(true, Cohen.addMember(Abbi));
		assertEquals(true, Cohen.addMember(Ted));
		assertEquals(true, Cohen.addMember(Charles));
		assertEquals(false, Cohen.addMember(Lily));
		assertEquals(false, Cohen.addMember(Abbi));
	}
	
	@Test
	void testAddPet() {
		assertEquals(true, Cohen.addPet(Hedwig));
		assertEquals(false, Cohen.addPet(Crookshanks));
	}
	
	@Test
	void testToString() {
		Cohen.addMember(Abbi);
		Cohen.addMember(Charles);
		Cohen.addPet(Hedwig);
		int budget = Cohen.getBudget();
		assertEquals("There are 2 people in this family." + '\n' + "Member #1: This person's name is Abbi and their salary is $60000." + '\n'+  "Member #2: This person's name is Charles and their salary is $10000." + '\n' + "This family's budget is: $" + budget + "." + '\n'  + "There are 1 pets in this family." + '\n' + "Pet #1: This animal is a owl and its name is Hedwig." + '\n', Cohen.toString());
	}
	
	@Test
	void testGetBudget() {
		Cohen.addMember(Abbi);
		Cohen.addMember(Ilanna);
		Cohen.addMember(Charles);
		Cohen.addMember(Ted);
		assertEquals(100000, Cohen.getBudget());
	}
	@Test 
	void testGetPeople(){
		Person [] members = new Person[4];
		Cohen.addMember(Ilanna); Cohen.addMember(Abbi); Cohen.addMember(Ted); Cohen.addMember(Charles);  
		members[0] = Ilanna; members[1] = Abbi; members[2] = Ted; members[3] = Charles;
		assertEquals(4, Cohen.getPeople().length);
	}
	
	@Test
	void testGetPets() {
		Pet [] pets = new Pet[2];
		Cohen.addPet(Crookshanks); Cohen.addPet(Hedwig); // shouldn't add the second pet 
		pets[0] = Crookshanks; 
		assertEquals(1, Cohen.getPets().length);
		
	}

}

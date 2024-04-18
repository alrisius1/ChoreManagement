/**
* Name : CircularChoreListTest
* Author: Alyssa Risius
* Created : Apr 1, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file tests the functionality of the CircularChoreList class. 
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.Chores;
import Classes.CircularChoreList;

class CircularChoreListTest {	
	private CircularChoreList choreList;

	@BeforeEach
	public void setUp() {
		choreList = new CircularChoreList();
	}

	@Test
	public void testAddSingleChore() {
		Chores dishes = new Chores("Dishes", "High", null, 3);
		choreList.add(dishes);
		assertTrue(choreList.contains(dishes));
	}

	@Test
	public void testCircularitySingleAdd() {
		Chores chore = new Chores("Clean room", "Mid", null, 2);
		choreList.add(chore);
		assertEquals(choreList.tail.next, choreList.head);
	}

	@Test
	public void testDeleteChore() {
		Chores chore = new Chores("Mow lawn", "Low", null, 10);
		choreList.add(chore);
		choreList.deleteChore(chore);
		assertFalse(choreList.contains(chore));
	}

	@Test
	public void testCircularityAfterDelete() {
		Chores vacuum = new Chores("Vacuum", "Low", null, 1);
		Chores dust = new Chores("Dust", "Mid", null, 5);
		choreList.add(vacuum);
		choreList.add(dust);
		choreList.deleteChore(vacuum);
		assertEquals(choreList.tail.next, choreList.head);
	}
	

	@Test
	public void testToList() {
		choreList.add(new Chores("Pick up Sticks", "Low", null, 5));
		choreList.add(new Chores("Sweep", "mid", null, 2));
		List<Chores> chores = choreList.toList();
		assertTrue(chores.size() == 2);
	}

	@Test
	public void testContainsFalse() {
		Chores chore = new Chores("Sweep", "mid", null, 15);
		assertFalse(choreList.contains(chore));
	}
}
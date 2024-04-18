/**
* Name : ChoreListManagerTest
* Author: Alyssa Risius
* Created : Apr 16, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file tests the methods listed the ChoreListManager file. 
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.ChoreListManager;
import Classes.Chores;
import Classes.FamilyMember;

class ChoreListManagerTest {

	private ChoreListManager choreListManager;
    private FamilyMember bob;
    private FamilyMember joe;

    @BeforeEach
    public void setUp() {
        choreListManager = ChoreListManager.getInstance();
        bob = new FamilyMember("Bob", 30);
        joe = new FamilyMember("Joe", 30);
    }

    @Test
    public void testAddAndRetrieveChores() {
    	bob = new FamilyMember("Bob", 30);
        Chores dishes = new Chores("Dishes", "High", bob, 5);
        Chores laundry = new Chores("Laundry", "Mid", bob, 3);
        Chores vacuum = new Chores("Vacuum", "Low", bob, 2);
        choreListManager.addChore(dishes);
        choreListManager.addChore(laundry);
        choreListManager.addChore(vacuum);
        
        PriorityQueue<Chores> retrievedChores = choreListManager.getChoresForFamilyMember(bob);
        assertTrue(retrievedChores.contains(dishes));
        assertTrue(retrievedChores.contains(laundry));
        assertTrue(retrievedChores.contains(vacuum));

        assertEquals(dishes, retrievedChores.poll());
        assertEquals(laundry, retrievedChores.poll());
        assertEquals(vacuum, retrievedChores.poll());
    }

    @Test
    public void testDeleteChore() {
    	Chores vacuum = new Chores("Vacuum", "Low", bob, 2);
        choreListManager.addChore(vacuum);
        choreListManager.deleteChore(vacuum);
        assertFalse(choreListManager.getSharedChores().contains(vacuum));
        assertFalse(choreListManager.getChoresForFamilyMember(bob).contains(vacuum));
    }

    @Test
    public void testRotateChoresandGetSharedChores() {
        Chores laundry = new Chores("Laundry", "High", null, 3);
        Chores dishes = new Chores("Dishes", "Mid", null, 2);
        choreListManager.addChore(laundry);
        choreListManager.addChore(dishes);
        choreListManager.rotateChores();
        List<Chores> sharedChores = choreListManager.getSharedChores();
        assertEquals(dishes, sharedChores.get(0));
    }

    @Test
    public void testCompleteChore() {
        Chores chore = new Chores("Mopping", "High", joe, 10);
        choreListManager.addChore(chore);
        choreListManager.completeChore(chore);
        assertTrue(chore.complete());
        assertEquals(10, joe.getRewardPoints());
    }
}

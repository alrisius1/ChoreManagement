/**
* Name : ChoresTest
* Author: Alyssa Risius
* Created : Apr 1, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file tests the functionality of the chore class. 
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.Chores;
import Classes.FamilyMember;

class ChoresTest {

	private Chores chore;
	FamilyMember fm;

    @BeforeEach
    void setUp() {
    	fm = new FamilyMember("Elena", 9);
        chore = new Chores("Clean Room", "High", fm, 2);
    }

    @Test
    public void testCompleteChore() {
        assertFalse(chore.complete());
        chore.completeChore();
        assertTrue(chore.complete());
    }

    @Test
    public void testCompleteChoreTwice() {
        chore.completeChore();
        boolean initialCompleteStatus = chore.complete();
        chore.completeChore();  
        assertEquals(initialCompleteStatus, chore.complete());
    }

    @Test
    public void testAddRewardPoints() {
        int initialPoints = fm.getRewardPoints();
        chore.completeChore();
        assertEquals(initialPoints + 2, fm.getRewardPoints());
    }

    @Test
    public void testNoAssignedMember() {
        Chores unassignedChore = new Chores("Clean windows", "Mid", null, 5);
        unassignedChore.completeChore();
        assertTrue(unassignedChore.complete());
    }

    @Test
    public void testGettersAndSetters() {
        chore.setChoreName("Wash dishes");
        assertEquals("Wash dishes", chore.getChoreName());

        chore.setPriority("Low");
        assertEquals("Low", chore.getPriority());

        FamilyMember newfm = new FamilyMember("Wyatt", 28);
        chore.setAssignedTo(newfm);
        assertEquals("Wyatt", chore.getAssignedTo().getName());
        
        chore.setChorePoints(5);
        assertEquals(5, chore.getChorePoints());
    }
    
    @Test
    public void testGetPriorityValue() {
    	chore.setPriority("High");
        assertEquals(3, chore.getPriorityValue());

        chore.setPriority("Mid");
        assertEquals(2, chore.getPriorityValue());

        chore.setPriority("Low");
        assertEquals(1, chore.getPriorityValue());

        chore.setPriority("Undefined");
        assertEquals(1, chore.getPriorityValue());
    }

}

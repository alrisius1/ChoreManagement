/**
* Name : FamilyMemberListTest
* Author: Alyssa Risius
* Created : Apr 13, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description :This file tests the methods listed the FamilyMemberList file. 
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.FamilyMember;
import Classes.FamilyMemberList;

class FamilyMemberListTest {

	private FamilyMemberList fmList;
    private FamilyMember alyssa;
    private FamilyMember bob;
    private FamilyMember charlie;

    @BeforeEach
    public void setUp() {
    	fmList = FamilyMemberList.getInstance();
    	fmList.getHousehold().clear(); 

        alyssa = new FamilyMember("Alyssa", 35);
        bob = new FamilyMember("Bob", 40);
        charlie = new FamilyMember("Charlie", 20);

        fmList.addFamilyMember(alyssa);
        fmList.addFamilyMember(bob);
    }
    
    @AfterEach
    public void tearDown() {
	   fmList.removeFamilyMemberByName(alyssa.getName()); 
	   fmList.removeFamilyMemberByName(bob.getName()); 
	   fmList.removeFamilyMemberByName(charlie.getName()); 
	   
    }

    @Test
    public void testAddFamilyMember() {        
        fmList.addFamilyMember(charlie);
        assertTrue(fmList.getHousehold().contains(charlie));
        assertEquals(3, fmList.getHousehold().size());
    }

    @Test
    public void testRemoveFamilyMemberByName() {
    	fmList.removeFamilyMemberByName(alyssa.getName());
        assertFalse(fmList.getHousehold().contains(alyssa));
        assertEquals(1, fmList.getHousehold().size());
    }

    @Test
    public void testGetHousehold() {
        List<FamilyMember> household = fmList.getHousehold();
        assertNotNull(household);
        assertFalse(household.isEmpty());
        assertTrue(household.contains(alyssa) && household.contains(bob));
    }
    
    @Test
    public void testResetAllRewardPoints() {
        alyssa.addRewardPoints(10);
        bob.addRewardPoints(12);

        fmList.resetAllRewardPoints();
        assertTrue(fmList.getHousehold().stream().allMatch(fm -> fm.getRewardPoints() == 0));
    }
    
    @Test
    public void testSortMembersByPoints() {
        alyssa.addRewardPoints(10);
        bob.addRewardPoints(12);
        fmList.addFamilyMember(charlie); 
        charlie.addRewardPoints(8);

        fmList.sortMembersByPoints();

        List<FamilyMember> sortedList = fmList.getHousehold();
        assertEquals(bob, sortedList.get(0));
        assertEquals(alyssa, sortedList.get(1));
        assertEquals(charlie, sortedList.get(2));
        
    }

}

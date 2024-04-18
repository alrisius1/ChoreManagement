/**
* Name : FamilyMemberTest
* Author: Alyssa Risius
* Created : Apr 1, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file creates the functionality of my FamilyMember class. 
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.FamilyMember;

class FamilyMemberTest {
	private FamilyMember member;
	
	@BeforeEach
	void setUp() {
		member = new FamilyMember("John", 30);
	}
	
	@Test
	public void testConstructor() {
		assertEquals("John", member.getName());
		assertEquals(30, member.getAge());
	}

	 @Test
	 public void testGetName() {
		 assertEquals("John", member.getName());
	 }
	 
	 @Test
	 public void testGetAge() {
		 assertEquals(30, member.getAge());
	 }
	 
	 @Test
	 public void testGetRewardPoints() {
		 assertEquals(0, member.getRewardPoints());
	 }
	
	 @Test
	 public void testSetName() {
		 member.setName("Bob");
	     assertEquals("Bob", member.getName());
	 }

	 @Test
	 public void testSetAge() {
		 member.setAge(25);
	     assertEquals(25, member.getAge());
	 }
	 
	@Test
    public void testToString() {
        String expectedToString = "FamilyMember [name=John, age=30]";
        assertEquals(expectedToString, member.toString());
    }
	
	@Test
	public void testAddRewardPoints() { 
		member.addRewardPoints(2);
		assertEquals(2, member.getRewardPoints());
	}

}

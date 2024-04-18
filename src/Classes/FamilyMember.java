package Classes;

/**
* Name : FamilyMember
* Author: Alyssa Risius
* Created : Apr 1, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This class creates an Object for each family member. It will 
* 			add each member into a list called household and holds the rewards points. 
*            BigO: O(1)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/

public class FamilyMember{
	// attributes
	private String name; 
	private int age;
	private int rewardPoints = 0;
	
	// constructor
	public FamilyMember(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// getters
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	public int getRewardPoints() { 
		return this.rewardPoints;
	}
	
	
	// setters
	public void setName(String name) { 
		this.name = name; 
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "FamilyMember [name=" + name + ", age=" + age + "]";
	}
	
	// adding points 
	public void addRewardPoints(int points) { 
		this.rewardPoints += points;
	}
	
    // reset rewards
    public void resetRewardPoints() { 
    	this.rewardPoints = 0;
    }

}



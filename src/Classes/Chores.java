/**
* Name : Chores
* Author: Alyssa Risius
* Created : Apr 1, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This class creates an Object for each chore. 
*            Input: The user will input each attribute.
*            assignedTo will be optional. If entered it will assign
*            chore to specified family member.
*            Output: None
*            BigO: O(1)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package Classes;

public class Chores {
	// attributes
	private String choreName; 
	private String priority; 
	private FamilyMember assignedTo = null; 
	private int chorePoints;
	private boolean complete;
	
	// constructor
	public Chores(String choreName, String priority, FamilyMember assignedTo, int chorePoints) {
		this.choreName = choreName;
		this.priority = priority;
		this.assignedTo = assignedTo;
		this.chorePoints = chorePoints;
		this.complete = false;
	}

	// getters
	public String getChoreName() {
		return choreName;
	}

	public String getPriority() {
		return priority;
	}
	
	public FamilyMember getAssignedTo() { 
		return assignedTo; 
	}

	public int getChorePoints() { 
		return chorePoints; 
	}
	public boolean complete() {
		return complete;
	}

	// setters
	public void setChoreName(String choreName) {
		this.choreName = choreName;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setAssignedTo(FamilyMember string) {
		this.assignedTo = string;
	}
	
	public void setChorePoints(int chorePoints) { 
		this.chorePoints = chorePoints;
	}
	
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return "Chores [choreName=" + choreName + ", priority=" + priority + ", assignedTo=" + assignedTo
				+ ", chorePoints=" + chorePoints + ", complete=" + complete + "]";
	}

	// mark as complete and add reward points
	public void completeChore() {
	    if (!this.complete()) {
	        this.setComplete(true);
	        if (this.getAssignedTo() != null) {
	            this.getAssignedTo().addRewardPoints(this.getChorePoints());
	        }
	    }
	}
	
	// converting priority string to int
	public int getPriorityValue() {
        switch (this.priority) {
            case "High":
                return 3;
            case "Mid":
                return 2;
            case "Low":
                return 1;
        }
        return 1;
    }
}

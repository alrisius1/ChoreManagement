/**
* Name : ChoreListManager
* Author: Alyssa Risius
* Created : Apr 13, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file creates a HashMap to link the chores with 
* 	the family members. It provides most of the functionality within
* 	the app. 
*            BigO: O(n)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package Classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ChoreListManager {
	
	private static ChoreListManager instance; 
	private Map<FamilyMember, List<Chores>> assignedChores; 
    private CircularChoreList sharedChores;
    private List<Chores> allChores;
    
    // constructor
    private ChoreListManager() {
        assignedChores = new HashMap<>();
        sharedChores = new CircularChoreList();
        allChores = new ArrayList<>();
    }
    
    public static ChoreListManager getInstance() { 
    	if (instance == null) { 
    		instance = new ChoreListManager(); 
    	}
    	return instance; 
    }

    // if assignedTo is not null -> add to specified Map
    // else add to shared chore
    public void addChore(Chores chore) {
        if (chore.getAssignedTo() != null) {
            assignedChores.putIfAbsent(chore.getAssignedTo(), new ArrayList<>());
            assignedChores.get(chore.getAssignedTo()).add(chore);
        } else {
            sharedChores.add(chore);
        }
        allChores.add(chore);
    }
 	
	// deletes chore from sharedChore list, assignedChore list, and allChore list
	public void deleteChore(Chores chore) {
	    sharedChores.deleteChore(chore);
	    for (List<Chores> chores : assignedChores.values()) {
	        chores.remove(chore);
	    }
	    allChores.remove(chore);
	}
	
    // rotating shared chores 
    public void rotateChores() {
        sharedChores.rotate();
        reassignSharedChores();
        // setting chore to no complete 
        List<Chores> choresList = ChoreListManager.getInstance().getSharedChores();
        for (Chores chore : choresList) {
            chore.setComplete(false); 
        }
    }
    
    // assigning chores to each family member
    private void reassignSharedChores() {
    	List<FamilyMember> household = FamilyMemberList.getInstance().getHousehold();
    		if (household.isEmpty()) return;

    	    // grabbing list of chores
    	    List<Chores> choresToReassign = new ArrayList<>(sharedChores.toList());
    	    int currentIndex = 0;

    	    // removing currently assigned-shared chores
    	    for (List<Chores> chores : assignedChores.values()) {
    	        chores.removeIf(chore -> choresToReassign.contains(chore));
    	    }

    	    // reassigning chores
    	    for (Chores chore : choresToReassign) {
    	        FamilyMember member = household.get(currentIndex);
    	        chore.setAssignedTo(member);
    	        assignedChores.putIfAbsent(member, new ArrayList<>());
    	        assignedChores.get(member).add(chore);
    	        currentIndex = (currentIndex + 1) % household.size();
    	    }
    }
    
    // returns list of chores mapped to the specified name
    public PriorityQueue<Chores> getChoresForFamilyMember(FamilyMember fmName) {
    	// grabbing chore list
    	List<Chores> choresList = assignedChores.getOrDefault(fmName, new ArrayList<>());
    	// sorting by priority
    	PriorityQueue<Chores> choresPQ = new PriorityQueue<>(Comparator.comparing(Chores::getPriorityValue, Comparator.reverseOrder()));
    	//adding to priority queue
    	choresPQ.addAll(choresList);
    	return choresPQ;
    }

    // returns list of shared chores
    public List<Chores> getSharedChores() {
        return sharedChores.toList();
    }
    
    // returns list of allChores
	public List<Chores> getAllChores() {
		return allChores;
	}
    
    // call complete chore method that sets chore to true and updates reward points
    public void completeChore(Chores chore) {
        if (chore.getAssignedTo() != null && !chore.complete()) {
            chore.completeChore(); 
        }
    }
    
    // searching for chore by name
    public Chores findChoreByName(String choreName) {
        List<Chores> chores = ChoreListManager.getInstance().getAllChores();
        for (Chores chore : chores) {
            if (chore.getChoreName().equals(choreName)) {
                return chore;
            }
        }
        return null; 
    }
    
    // used driver to test rotate method
    public static void main(String[] args) {
    	ChoreListManager choreList = new ChoreListManager();
    	FamilyMemberList fmList = FamilyMemberList.getInstance();
    	
    	// setting up family members
    	FamilyMember joe = new FamilyMember("Joe", 30);
    	FamilyMember alyssa = new FamilyMember("Alyssa", 30);
    	FamilyMember bob = new FamilyMember("Bob", 30);
    	
    	fmList.addFamilyMember(joe);
    	fmList.addFamilyMember(alyssa);
    	fmList.addFamilyMember(bob);
    	
    	// setting up chores
    	Chores cleanRoom = new Chores("Clean Room", "Low", null, 2);
    	Chores dishes = new Chores("Dishes", "Mid", null, 3);
    	Chores sticks = new Chores("Pick Up Sticks", "High", bob, 10);
    	
    	choreList.addChore(cleanRoom);
    	choreList.addChore(dishes);
    	choreList.addChore(sticks);
    	
    	// comparing chore list before rotate and after rotate -> head chore should become tail chore 
    	// and the chores should be assigned if not already assigned. 
    	System.out.println("Testing adding to shared list and also before rotate: \n" + choreList.getSharedChores()); 
    	System.out.println("\nTesting adding to assignedTo list: \n" + choreList.getChoresForFamilyMember(bob)); 
    	choreList.rotateChores(); 
    	System.out.println("\nTesting after rotate: \n" + choreList.getSharedChores()); 
    }
}

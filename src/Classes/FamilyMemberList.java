/**
* Name : FamilyMemberList
* Author: Alyssa Risius
* Created : Apr 12, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file handles the family member list and the 
* 	reward methods. 
*            BigO: O(n)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package Classes;

import java.util.ArrayList;
import java.util.List;

public class FamilyMemberList {
	private static FamilyMemberList instance;
    private List<FamilyMember> household;

    // private constructor 
    private FamilyMemberList() {
        household = new ArrayList<>();
    }

    public static FamilyMemberList getInstance() {
        if (instance == null) {
            instance = new FamilyMemberList();
        }
        return instance;
    }

    //add family member
    public void addFamilyMember(FamilyMember member) {
        household.add(member);
    }

    // remove family member by name
    public void removeFamilyMemberByName(String name) {
        household.removeIf(fm -> fm.getName().equals(name));
    }

    // getter for the household list
    public List<FamilyMember> getHousehold() {
        return new ArrayList<>(household);
    }
    
    // finds/returns fm by name
    public FamilyMember findMemberByName(String name) {
        for (FamilyMember member : household) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null; 
    }
    
    // reset specified fm rewards
    public void resetAllRewardPoints() {
        for (FamilyMember member : household) {
            member.resetRewardPoints();
        }
    }
    
    // using insertion sort to sort members descending based on reward points
    public void sortMembersByPoints() {
        for (int i = 1; i < household.size(); i++) {
        	// object to insert
            FamilyMember key = household.get(i);
            int j = i - 1;
            // finding correct spot to place incoming object - shifting objects to the right
            while (j >= 0 && household.get(j).getRewardPoints() < key.getRewardPoints()) {
            	household.set(j + 1, household.get(j));
                j = j - 1;
            }
            household.set(j + 1, key);
        }
    }
}

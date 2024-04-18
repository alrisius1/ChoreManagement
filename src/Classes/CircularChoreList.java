/**
* Name : CircularChoreList
* Author: Alyssa Risius
* Created : Apr 1, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file creates a circular linked list and handles the 
* 			functionality of the list. 
*            BigO:O(n)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package Classes;

import java.util.ArrayList;
import java.util.List;

public class CircularChoreList {
	// attributes
	public Node head = null; 
	public Node tail = null; 
	
	// node class 
	public class Node { 
		Chores chore; 
		public Node next; 
		
		// node constructor
		Node(Chores chore) { 
			this.chore = chore; 
			this.next = null; 
		}
	}
	
	// constructor
	public CircularChoreList() {}
	
	
	// adding chore to shared chore list and connecting tail to head
	// to make it circular
    public void add(Chores chore) {
        Node newNode = new Node(chore);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
    }

    // delete chore
    public void deleteChore(Chores chore) { 
    	Node current = head;
    	// if list empty -> return false
    	if (head == null) {
    		return;
        }
    	do {
    		Node next = current.next;
    		if (next.chore == chore) {
    			if (tail == head) { 
    				head = null;
    				tail = null; 
    			} else { 
    				current.next = next.next; 
    				if (head == next) { 
    					head = head.next;
    				}
    				if (tail == next) { 
    					tail = current;
    				}
    			}
    			break;
    		}
    		current = next; 
    	} while(current != head);
    }
    
    
    // if 2 or more chores -> rotate list to right
    public void rotate() {
        if (head != null && head.next != null) { 
            tail = head;
            head = head.next;
        }
    }

    // grabbing each chore in LinkedList and adding to new list
    public List<Chores> toList() {
        List<Chores> chores = new ArrayList<>();
        
        if (head != null) {
            Node current = head;
            while (true) { 
            	chores.add(current.chore); 
            	current = current.next; 
            	if (current == head) { 
            		break; 
            	}
            }  	
        }
        return chores;
    }
    
    // checks for specific chore in list
	public boolean contains(Chores chore) {
		if (head == null) return false; 

        Node current = head;
        do {
            if (current.chore.equals(chore)) {
                return true; 
            }
            current = current.next;
        } while (current != head);
        return false;
    }
	
    public static void main(String[] args) {
    	
    	// testing rotate
    	CircularChoreList choreList = new CircularChoreList();
    	FamilyMember wyatt = new FamilyMember("Wyatt", 9);
    	Chores cleanRoom = new Chores("Clean Room", "Low", null, 2);
    	Chores dishes = new Chores("Dishes", "Mid", wyatt, 3);
    	Chores sticks = new Chores("Pick Up Sticks", "High", null, 3);
    	choreList.add(cleanRoom);
    	choreList.add(dishes);
    	choreList.add(sticks);
    	System.out.println("Chore list before rotating:\n" + choreList.toList()); 
    	 
    	choreList.rotate(); 
    	System.out.println();
    	System.out.println("Chore list after rotating:\n" + choreList.toList()); 
    	

    }



}


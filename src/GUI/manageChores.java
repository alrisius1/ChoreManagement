/**
* Name : manageChores
* Author: Alyssa Risius
* Created : Apr 16, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file creates a JDialog page in the GUI to add, update, delete and 
* 			rotate the chores. 
*            Input: Chore name, priority, if it should be assigned to a familymember, and chore points.
*            Output: success or error messages
*            BigO: O(n)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.ChoreListManager;
import Classes.Chores;
import Classes.FamilyMember;
import Classes.FamilyMemberList;

import java.awt.SystemColor;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class manageChores extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField choreNameField;
	private JTextField chorePointsField;
	private JComboBox<String> fmDropdown;
	private JComboBox<String> choreDropdown;
	private JComboBox<String> priorityDropdown;


	/**
	 * Create the dialog.
	 */
	public manageChores() {
		setTitle("Manage Chores");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel choreDropdownLabel = new JLabel("Select a Chore");
			choreDropdownLabel.setBounds(94, 14, 93, 18);
			choreDropdownLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			contentPanel.add(choreDropdownLabel);
		}
		{
			// list of shared chores
			choreDropdown = new JComboBox<String>();
			choreDropdown.setBackground(new Color(255, 228, 196));
			choreDropdown.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
					 	// grabbing selected chore
				        String selectedChoreName = (String) choreDropdown.getSelectedItem();
				        if (selectedChoreName == null) {
				            return; 
				        }
				        // finding specified chore -> setting chore info to field boxes
				        Chores selectedChore = findChoreByName(selectedChoreName);
				        if (selectedChore != null) {
				            choreNameField.setText(selectedChore.getChoreName());
				            priorityDropdown.setSelectedItem(selectedChore.getPriority());

				            FamilyMember assignedTo = selectedChore.getAssignedTo();
				            // if assigned to fm -> set to fm name else -> null
				            if (assignedTo != null) {
				                fmDropdown.setSelectedItem(assignedTo.getName());
				            } else {
				                fmDropdown.setSelectedIndex(0);
				            }
				            // setting chore points
				            chorePointsField.setText(Integer.toString(selectedChore.getChorePoints()));
				        }
				    }
			});
			choreDropdown.setBounds(201, 15, 123, 16);
			contentPanel.add(choreDropdown);
		}
		{
			JLabel nameLabel = new JLabel("Chore Name:");
			nameLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			nameLabel.setBounds(24, 46, 86, 14);
			contentPanel.add(nameLabel);
		}
		{
			JLabel priorityLabel = new JLabel("Priority:");
			priorityLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			priorityLabel.setBounds(34, 69, 55, 14);
			contentPanel.add(priorityLabel);
		}
		{
			JLabel assignedToLabel = new JLabel("Assigned To:");
			assignedToLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			assignedToLabel.setBounds(24, 92, 86, 14);
			contentPanel.add(assignedToLabel);
		}
		{
			JLabel chorePointsLabel = new JLabel("Chore Points:");
			chorePointsLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			chorePointsLabel.setBounds(24, 117, 86, 14);
			contentPanel.add(chorePointsLabel);
		}
		{
			choreNameField = new JTextField();
			choreNameField.setBounds(120, 43, 273, 17);
			contentPanel.add(choreNameField);
			choreNameField.setColumns(10);
		}
		{
			priorityDropdown = new JComboBox<String>();
			priorityDropdown.setBackground(new Color(255, 228, 196));
			priorityDropdown.setBounds(120, 65, 123, 18);
			contentPanel.add(priorityDropdown);
			priorityDropdown.addItem("High");
			priorityDropdown.addItem("Mid");
			priorityDropdown.addItem("Low");
		}
		{
			fmDropdown = new JComboBox<String>();
			fmDropdown.setBackground(new Color(255, 228, 196));
			fmDropdown.setBounds(120, 88, 123, 18);
			contentPanel.add(fmDropdown);
		}
		{
			chorePointsField = new JTextField();
			chorePointsField.setBounds(120, 114, 86, 20);
			contentPanel.add(chorePointsField);
			chorePointsField.setColumns(10);
		}
		{
			// add chore
			JButton addbtn = new JButton("Add");
			addbtn.setBackground(new Color(224, 255, 255));
			addbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// grabbing inputs
					String selectedMemberName = (String) fmDropdown.getSelectedItem();
			        String name = choreNameField.getText().trim();
			        String priority = (String) priorityDropdown.getSelectedItem();
			        String rewardsTxt = chorePointsField.getText().trim();
			        int rewardPoints = 0;

			        // if fm not selected -> assignedTo = null else -> grab fm
			        FamilyMember assignedTo = null;
			        if (selectedMemberName != null && !selectedMemberName.isEmpty()) {
			            assignedTo = FamilyMemberList.getInstance().findMemberByName(selectedMemberName);
			        }

			        // convert to int and create new chore
			        try {
			            rewardPoints = Integer.parseInt(rewardsTxt);
			            Chores chore = new Chores(name, priority, assignedTo, rewardPoints);
			            ChoreListManager.getInstance().addChore(chore);
			            JOptionPane.showMessageDialog(manageChores.this, "Chore added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			            refreshChoreDropdown();
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(manageChores.this, "Please enter a valid number for chore points.", "Error", JOptionPane.ERROR_MESSAGE);
			        }

			        // clear fields after adding chore
			        choreNameField.setText("");
			        chorePointsField.setText("");
			        priorityDropdown.setSelectedIndex(0);
			        fmDropdown.setSelectedIndex(0);
			    }
			});
			addbtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			addbtn.setBounds(24, 165, 89, 23);
			contentPanel.add(addbtn);
		}
		{
			// updates chore information
			JButton updatebtn = new JButton("Update");
			updatebtn.setBackground(new Color(224, 255, 255));
			updatebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// grab selected chore
					String selectedChoreName = (String) choreDropdown.getSelectedItem();
			        if (selectedChoreName == null) {
			            JOptionPane.showMessageDialog(manageChores.this, "No chore selected for update.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        // grabbing new values 
			        String newName = choreNameField.getText().trim();
			        String newPriority = (String) priorityDropdown.getSelectedItem();
			        String selectedMemberName = (String) fmDropdown.getSelectedItem();
			        FamilyMember newAssignedTo = FamilyMemberList.getInstance().findMemberByName(selectedMemberName);
			        String newChorePointsTxt = chorePointsField.getText().trim();
			        int rewardPoints = 0;

			        // convert reward points to int
			        try {
			            rewardPoints = Integer.parseInt(newChorePointsTxt);
			            // find chore and update
			            boolean found = false;
			            for (Chores chore : ChoreListManager.getInstance().getSharedChores()) {
			                if (chore.getChoreName().equals(selectedChoreName)) {
			                    chore.setChoreName(newName);
			                    chore.setPriority(newPriority);
			                    chore.setAssignedTo(newAssignedTo);  
			                    chore.setChorePoints(rewardPoints);
			                    found = true;
			                    break;
			                }
			            }
			            if (found) {
			                // update chore dropdown and success message
			                refreshChoreDropdown(); 
			                JOptionPane.showMessageDialog(manageChores.this, "Chore updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

			                // clear text fields
			                choreNameField.setText("");
			                chorePointsField.setText("");
			                priorityDropdown.setSelectedIndex(0);
			                fmDropdown.setSelectedIndex(0);
			            } else {
			                JOptionPane.showMessageDialog(manageChores.this, "Chore not found for update.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(manageChores.this, "Please enter a valid number for chore points.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			updatebtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			updatebtn.setBounds(123, 165, 89, 23);
			contentPanel.add(updatebtn);
		}
		{
			// deletes selected chore
			JButton deletebtn = new JButton("Delete");
			deletebtn.setBackground(new Color(224, 255, 255));
			deletebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// grabbing chore
			        String selectedChoreName = (String) choreDropdown.getSelectedItem();
			        if (selectedChoreName == null || selectedChoreName.isEmpty()) {
			            JOptionPane.showMessageDialog(manageChores.this, "No chore selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        // finding chore by name
			        Chores selectedChore = ChoreListManager.getInstance().findChoreByName(selectedChoreName);
			        if (selectedChore == null) {
			            JOptionPane.showMessageDialog(manageChores.this, "Chore not found.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        // deleting selected chore 
			        ChoreListManager.getInstance().deleteChore(selectedChore);
			        JOptionPane.showMessageDialog(manageChores.this, "Chore deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

			        // refresh dropdown and clear fields
			        refreshChoreDropdown();
			        choreNameField.setText("");
			        chorePointsField.setText("");
			        priorityDropdown.setSelectedIndex(0);
			        fmDropdown.setSelectedIndex(0);
			    }
			});
			deletebtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			deletebtn.setBounds(222, 165, 89, 23);
			contentPanel.add(deletebtn);
		}
		{
			// will rotate and assign shared chores
			JButton rotatebtn = new JButton("Rotate");
			rotatebtn.setBackground(new Color(224, 255, 255));
			rotatebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// grabbing shared chore list -> rotating -> then assigning to each fm
				    ChoreListManager.getInstance().rotateChores();
				    refreshChoreDropdown();
		            JOptionPane.showMessageDialog(manageChores.this, "Chores have been rotated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
		        }
			});
			rotatebtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			rotatebtn.setBounds(321, 165, 89, 23);
			contentPanel.add(rotatebtn);
		}
		refreshFMDropdown();
		refreshChoreDropdown();
	}
	
	// refreshes family member dropdown menu
	private void refreshFMDropdown() { 
		fmDropdown.removeAllItems();
		fmDropdown.addItem("");
		List<FamilyMember> household = FamilyMemberList.getInstance().getHousehold();
		for (FamilyMember fm : household) {
			fmDropdown.addItem(fm.getName());
	    }
		if (!household.isEmpty()) { 
			fmDropdown.setSelectedIndex(0);
		}
	}
	
	// refreshes chore dropdown menu
	private void refreshChoreDropdown() { 
		choreDropdown.removeAllItems();
		choreDropdown.addItem("");
		List<Chores> sharedChores = ChoreListManager.getInstance().getSharedChores();
		for (Chores chore : sharedChores) {
			choreDropdown.addItem(chore.getChoreName());
		}
		if (!sharedChores.isEmpty()) { 
			choreDropdown.setSelectedIndex(0);
		}
	}
	
	// finds the chore by name 
	private Chores findChoreByName(String choreName) {
	    List<Chores> chores = ChoreListManager.getInstance().getSharedChores();
	    for (Chores chore : chores) {
	        if (chore.getChoreName().equals(choreName)) {
	            return chore;
	        }
	    }
	    return null; 
	}
}

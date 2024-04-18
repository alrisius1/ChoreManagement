/**
* Name : manageFamilyMembers
* Author: Alyssa Risius
* Created : Apr 16, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file creates a JDialog page in the GUI to add, update, and delete family members chores.
*            Input: Family Member name and age.
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

import Classes.FamilyMember;
import Classes.FamilyMemberList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class manageFamilyMembers extends JDialog {

	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private final JPanel contentPanel = new JPanel();
	private FamilyMemberList household;
	private JTextField nameTxtField;
	private JTextField ageTxtField;
	JComboBox<String> fmDropdown;

	public manageFamilyMembers(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		household = FamilyMemberList.getInstance();
		setTitle("Manage Family Members");
		setBounds(100, 100, 452, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel fmDropdownLabel = new JLabel("Select Family Member");
			fmDropdownLabel.setBounds(67, 11, 133, 14);
			fmDropdownLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
			contentPanel.add(fmDropdownLabel);
		}
		
		fmDropdown = new JComboBox<String>();
		fmDropdown.setBackground(new Color(255, 228, 196));
		fmDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FamilyMember selectedMember = getSelectedFamilyMember();
		        if (selectedMember != null) {
		            nameTxtField.setText(selectedMember.getName());
		            ageTxtField.setText(String.valueOf(selectedMember.getAge()));
		        }
			}
		});
		fmDropdown.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fmDropdown.setBounds(210, 7, 103, 22);
		contentPanel.add(fmDropdown);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		nameLabel.setBounds(36, 55, 59, 14);
		contentPanel.add(nameLabel);
		
		nameTxtField = new JTextField();
		nameTxtField.setBounds(88, 52, 225, 20);
		contentPanel.add(nameTxtField);
		nameTxtField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Age: ");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(35, 94, 46, 14);
		contentPanel.add(lblNewLabel);
		
		ageTxtField = new JTextField();
		ageTxtField.setBounds(88, 91, 86, 20);
		contentPanel.add(ageTxtField);
		ageTxtField.setColumns(10);
		
		JButton addbtn = new JButton("Add");
		addbtn.setBackground(new Color(224, 255, 255));
		// adds new family member to list
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTxtField.getText().trim();
				String ageText = ageTxtField.getText().trim();
				int age = 0; 
				
				// convert to int -> create new member -> successful box -> refresh
		        try {
		            age = Integer.parseInt(ageText);
		            FamilyMember newFM = new FamilyMember(name, age);
		            household.addFamilyMember(newFM);
		            JOptionPane.showMessageDialog(manageFamilyMembers.this, "Family member added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
		            refreshFMDropdown();
		            mainFrame.refreshFMDropdown();
		        // if not int -> error box
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(manageFamilyMembers.this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
			} 
		});
		addbtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		addbtn.setBounds(23, 134, 89, 23);
		contentPanel.add(addbtn);
		
		JButton updatebtn = new JButton("Update");
		updatebtn.setBackground(new Color(224, 255, 255));
		// will update family member information
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> comboBox = fmDropdown;
		        String selectedName = (String) comboBox.getSelectedItem();
		        String newName = nameTxtField.getText().trim();
		        String ageText = ageTxtField.getText().trim();
		        int newAge = 0; 
				
			    // convert to int 
		        try {
		        	newAge = Integer.parseInt(ageText);
		        // if not int -> error box
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(manageFamilyMembers.this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
						
		        // finding member to update
		        FamilyMember memberToUpdate = null; 
		        for (FamilyMember fm : FamilyMemberList.getInstance().getHousehold()) { 
		        	if (fm.getName().equals(selectedName)) { 
		        		memberToUpdate = fm; 
		        		break; 
		        	} 
		        } 
		        
		        // updating specified member and refreshing drop down box
		        if (memberToUpdate != null) {
		            memberToUpdate.setName(newName);
		            memberToUpdate.setAge(newAge);
		            
		            refreshFMDropdown();
		            mainFrame.refreshFMDropdown();
		            JOptionPane.showMessageDialog(manageFamilyMembers.this, "Family member updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
		        } else { 
		        	JOptionPane.showMessageDialog(manageFamilyMembers.this, "Family member not selected.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		updatebtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		updatebtn.setBounds(122, 134, 89, 23);
		contentPanel.add(updatebtn);
		
		JButton deletebtn = new JButton("Delete");
		deletebtn.setBackground(new Color(224, 255, 255));
		// deletes family member from list
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String selectedName = (String) fmDropdown.getSelectedItem();
			        if (selectedName != null) {
			        	household.removeFamilyMemberByName(selectedName);
			        	refreshFMDropdown();
			        	mainFrame.refreshFMDropdown();
			        	JOptionPane.showMessageDialog(manageFamilyMembers.this, "Family member deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			        } else {
			            JOptionPane.showMessageDialog(manageFamilyMembers.this, "Please select a family member to delete.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
		deletebtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		deletebtn.setBounds(221, 134, 89, 23);
		contentPanel.add(deletebtn);
		
		JButton clearbtn = new JButton("Clear");
		clearbtn.setBackground(new Color(224, 255, 255));
		// clears text fields
		clearbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				refreshFMDropdown();
			}
		});
		clearbtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		clearbtn.setBounds(320, 134, 89, 23);
		contentPanel.add(clearbtn);
		refreshFMDropdown();
		mainFrame.refreshFMDropdown();
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
		// clear name fields
		nameTxtField.setText("");
		ageTxtField.setText("");
		
	}
	
	// grabs the selected family member
	private FamilyMember getSelectedFamilyMember() {
	    String selectedName = (String) fmDropdown.getSelectedItem();
	    if (selectedName != null) {
	        for (FamilyMember fm : FamilyMemberList.getInstance().getHousehold()) {
	            if (fm.getName().equals(selectedName)) {
	                return fm;
	            }
	        }
	    }
	    return null;
	}
}

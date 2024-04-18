/**
* Name : MainFrame
* Author: Alyssa Risius
* Created : Apr 16, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file creates the main frame of the GUI. No Input. 
*			 Output: success or error messages
*            BigO: O(n)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import Classes.FamilyMember;
import Classes.FamilyMemberList;

import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> fmDropdown;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle("Chore Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel(" Chore Management System");
		titleLabel.setFont(new Font("Cooper Black", Font.BOLD, 20));
		titleLabel.setBounds(57, 11, 302, 35);
		contentPane.add(titleLabel);
		
		JLabel selectFMLabel = new JLabel("Select Family Member:");
		selectFMLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		selectFMLabel.setBounds(57, 89, 151, 24);
		contentPane.add(selectFMLabel);
		
		fmDropdown = new JComboBox<String>();
		fmDropdown.setBackground(new Color(255, 228, 196));
		fmDropdown.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		fmDropdown.setBounds(228, 89, 131, 24);
		contentPane.add(fmDropdown);
		
		JButton myChoresbtn = new JButton("My Chores");
		// uses selected fm name to open page with priority shorted chores
		myChoresbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedName = (String) fmDropdown.getSelectedItem();
				if (selectedName != null) {
					FamilyMember familyMember = FamilyMemberList.getInstance().findMemberByName(selectedName);
					if (familyMember != null) {
						myChores myChores = new myChores(familyMember);
			            myChores.setVisible(true);
					} else {
		                JOptionPane.showMessageDialog(MainFrame.this, "Selected family member data not found.", "Error", JOptionPane.ERROR_MESSAGE);	        
		            } 
				}else {
		            JOptionPane.showMessageDialog(MainFrame.this, "Please select a family member.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		myChoresbtn.setBackground(new Color(224, 255, 255));
		myChoresbtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		myChoresbtn.setBounds(57, 124, 103, 23);
		contentPane.add(myChoresbtn);
		
		JButton rewardsbtn = new JButton("Rewards");
		rewardsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rewards rewards = new rewards();
				rewards.setVisible(true);
			}
		});
		rewardsbtn.setBackground(new Color(224, 255, 255));
		rewardsbtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		rewardsbtn.setBounds(270, 124, 89, 23);
		contentPane.add(rewardsbtn);
		
		JButton manageFMbtn = new JButton("Manage Family Members");
		manageFMbtn.setBackground(new Color(224, 255, 255));
		// calls for the manage family member page to open
		manageFMbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageFamilyMembers manageFM = new manageFamilyMembers(MainFrame.this); 
				manageFM.setVisible(true);
			}
		});
		manageFMbtn.setFont(new Font("Cooper Black", Font.PLAIN, 10));
		manageFMbtn.setBounds(217, 55, 174, 23);
		contentPane.add(manageFMbtn);
		
		JButton manageChoresbtn = new JButton("Manage Chores");
		// calls for the manage chore page to open
		manageChoresbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageChores manageChores = new manageChores();
				manageChores.setVisible(true);
			}
		});
		manageChoresbtn.setBackground(new Color(224, 255, 255));
		manageChoresbtn.setFont(new Font("Cooper Black", Font.PLAIN, 10));
		manageChoresbtn.setBounds(46, 57, 144, 21);
		contentPane.add(manageChoresbtn);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{titleLabel, selectFMLabel, fmDropdown, myChoresbtn, rewardsbtn, manageFMbtn, manageChoresbtn}));
		refreshFMDropdown();
	}
	
	// refreshed family member drop down to be current
	public void refreshFMDropdown() { 
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

}

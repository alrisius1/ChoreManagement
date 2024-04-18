/**
* Name : rewards
* Author: Alyssa Risius
* Created : Apr 18, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file creates a JDialog page in the GUI that shows each family member and their
* 			reward points in descending order. 
*            Output: Family Members name and reward points
*            BigO: O(n log n)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.FamilyMember;
import Classes.FamilyMemberList;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Color;

public class rewards extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FamilyMemberList fmList; 
	private JPanel namePanel;

	/**
	 * Create the dialog.
	 */
	public rewards() {
		setTitle("Rewards");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		namePanel = new JPanel();
		namePanel.setBackground(SystemColor.activeCaption);
		namePanel.setBounds(76, 39, 288, 177);
		contentPanel.add(namePanel);
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
		
		JButton resetbtn = new JButton("Reset All Points");
		resetbtn.setBackground(new Color(255, 228, 196));
		// resets all award points when clicked
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fmList = FamilyMemberList.getInstance();
				fmList.resetAllRewardPoints();
				updateNamePanel();
			}
		});
		resetbtn.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		resetbtn.setBounds(76, 227, 288, 23);
		contentPanel.add(resetbtn);
		
		JLabel lblNewLabel = new JLabel("Rewards");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		lblNewLabel.setBounds(167, 11, 102, 23);
		contentPanel.add(lblNewLabel);
		
		updateNamePanel();
	}
	
	// lists the names and reward points of each member
	public void updateNamePanel() {
		// sorting members by points
	    FamilyMemberList.getInstance().sortMembersByPoints();  
	    List<FamilyMember> sortedMembers = FamilyMemberList.getInstance().getHousehold();  
	    // clearing existing information
	    namePanel.removeAll();  

	    // print statement
	    for (FamilyMember member : sortedMembers) {
	        JLabel memberLabel = new JLabel(member.getName() + ": " + member.getRewardPoints() + " points");
	        namePanel.add(memberLabel);
	    }
	    // refresh panel to display updated information
	    namePanel.revalidate(); 
	    namePanel.repaint();
	}
}

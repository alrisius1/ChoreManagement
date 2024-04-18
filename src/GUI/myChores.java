/**
* Name : myChores
* Author: Alyssa Risius
* Created : Apr 18, 2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows11 Version 22H2
* IDE: eclipse 
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This file creates a JDialog page in the GUI that shows each family members chores.
*            Input: needs to have name selected in MainFrame
*            Output: shows list of chores with checkboxes next to them
*            BigO: O(n)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
*/
package GUI;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.ChoreListManager;
import Classes.Chores;
import Classes.FamilyMember;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.PriorityQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class myChores extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JPanel chorePanel;

	/**
	 * Create the dialog.
	 */
	public myChores(FamilyMember familyMember) {
		setTitle("My Chores");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel headerLabel = new JLabel(familyMember.getName() + "'s Chores");
			headerLabel.setBounds(117, 11, 198, 24);
			headerLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			contentPanel.add(headerLabel);
		}
		{
			chorePanel = new JPanel();
			chorePanel.setBorder(null);
			chorePanel.setBackground(SystemColor.activeCaption);
			chorePanel.setBounds(117, 53, 198, 197);
			contentPanel.add(chorePanel);
			loadChoresForFamilyMember(familyMember, chorePanel);
			chorePanel.setLayout(new BoxLayout(chorePanel, BoxLayout.Y_AXIS));
		}
	
	}

	// grabs pq of chores assigned to specified fm and creates checkbox for each chore
	private void loadChoresForFamilyMember(FamilyMember familyMember, JPanel panel) {
		PriorityQueue<Chores> chores = ChoreListManager.getInstance().getChoresForFamilyMember(familyMember);
	    if (chores.isEmpty()) {
	    	chorePanel.add(new JLabel("No chores assigned."));
	    } else {
	    	for (Chores chore : chores) {
	    	    JCheckBox checkBox = new JCheckBox(chore.getChoreName());
	    	    checkBox.addActionListener(new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	            if (checkBox.isSelected()) {
	    	                ChoreListManager.getInstance().completeChore(chore);
	    	                JOptionPane.showMessageDialog(null, "Chore completed! Points added: " + chore.getChorePoints());
	    	            }
	    	        }
	    	    });
	    	    chorePanel.add(checkBox);
	    	}
	    }
	
		// Refresh panel to display new components
	    panel.revalidate();
	    panel.repaint();
	   }
}
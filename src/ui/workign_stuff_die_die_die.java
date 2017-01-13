package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.adithyasairam.tba4j.Events;
import com.adithyasairam.tba4j.models.Match;
import com.adithyasairam.tba4j.models.Match.Alliance;

import buildingBlocks.RobotNumber;
import buildingBlocks.RobotTabbedPanel;
import buildingBlocks.UIV3;
import elements.Robot;

public class workign_stuff_die_die_die extends UIV3 implements ActionListener {
	public Match[] fullMatches;
	public JTextField matchField;
	Events ev = new Events();
	public int countOfMatches = 0;
	public workign_stuff_die_die_die() {
		ITEM_IMPORT_TEAM_NUMBERS.setText("Next");
		matchField = new JTextField();
		matchField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					ArrayList<String> teamNums = new ArrayList<String>();
					fullMatches = ev.getEventMatches(matchField.getText());
					List<String> autoNums = new Vector<String>();
					List<String> teleNums = new Vector<String>();
					Match newmatch = fullMatches[countOfMatches];
					countOfMatches++;
					Alliance[] ally = newmatch.alliances;
					for(int i =0; i<ally.length; i++){
						if(i<=3){
							RobotNumber
							autoNums.add(ally);
						}
					}
					
					for (RobotTabbedPanel<AutonomousRobotPanel, TeleoperatedRobotPanel> rp : workign_stuff_die_die_die.this.panels) {
						autoNums.add(rp.autonomous.name);
						teleNums.add(rp.teleoperated.name);
					}
					for (int i = 0; i < Math.min(teamNums.size(), autoNums.size()); i++) {
						autoNums.get(i).setText(teamNums.get(i));
						teleNums.get(i).setText(teamNums.get(i));
					}
					matchField.setText("");
					System.out.println("Successfully imported: " + teamNums.toString());
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		this.MENU_BAR.add(matchField);
		
		// Initializing/Adding all the RobotTabbedPanels
		for (int i = 1; i < 7; i++) {
			if (i <= 3) {
				getContentPane().add(new Robot(new AutonomousRobotPanel("Team " + i, Color.RED), new TeleoperatedRobotPanel("Team " + i, Color.RED)));	
			}
			else {
				getContentPane().add(new Robot(new AutonomousRobotPanel("Team " + i, Color.BLUE), new TeleoperatedRobotPanel("Team " + i, Color.BLUE)));
			}
		}
		
		contentPane.setLayout(new GridLayout(2, 3));
		this.getRobotTabbedPanel(3).setTabPlacement(JTabbedPane.BOTTOM);
		this.getRobotTabbedPanel(4).setTabPlacement(JTabbedPane.BOTTOM);
		this.getRobotTabbedPanel(5).setTabPlacement(JTabbedPane.BOTTOM);
		
		this.pack();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		this.setVisible(true);
		this.setTitle("FRC SteamWorks - Scouting Program");
	}
	
}

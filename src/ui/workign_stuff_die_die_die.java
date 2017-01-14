package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.google.gson.stream.JsonReader;

import buildingBlocks.RobotNumber;
import buildingBlocks.RobotTabbedPanel;
import buildingBlocks.UIV3;
import elements.Parser;
import elements.Robot;
import elements.Tools;
import javax.swing.JMenuItem;

public class workign_stuff_die_die_die extends UIV3 implements ActionListener {
	
	public JTextField matchField;
	
	public workign_stuff_die_die_die() {
		matchField = new JTextField();
		matchField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						
						List<String> teamNums = Tools.getTeamNumbers(matchField.getText());
								
						List<RobotNumber> autoNums = new Vector<RobotNumber>();
						List<RobotNumber> teleNums = new Vector<RobotNumber>();
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
					} catch (FileNotFoundException e) {
						System.err.println("Invalid match id: " + matchField.getText());
					} catch (IOException e) {
						System.err.println("Unable to import team numbers");
					}
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
		
		JMenuItem mntmTeamNumGetter = new JMenuItem("Team NUM GETTER");
		MENU_COMPETITION.add(mntmTeamNumGetter);
	}
	
}

package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import buildingBlocks.RobotNumber;
import buildingBlocks.RobotTabbedPanel;
import buildingBlocks.UIV3;
import elements.Robot;
import elements.Tools;
import tools.FileUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class U extends UIV3 implements ActionListener {
	
	/**
	 * 
	 */
	
	public JTextField matchField;
	ArrayList<ArrayList<String>> fullMatches = new ArrayList<ArrayList<String>>();
	public int matchCount = 0;
	public UIV3 ui = new UIV3();
	
	public U() {
		ITEM_IMPORT_TEAM_NUMBERS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				fullMatches = ui.makeFullArray(matchCount);
				ui.setMatchReset(matchCount);
				
			}
		});
		ITEM_TEAM_GET.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				TI.setVisible(true);
				List<String> teamNums =fullMatches.get(matchCount);
				List<RobotNumber> autoNums = new Vector<RobotNumber>();
				List<RobotNumber> teleNums = new Vector<RobotNumber>();
				for (RobotTabbedPanel<AutonomousRobotPanel, TeleoperatedRobotPanel> rp : U.this.panels) {
					autoNums.add(rp.autonomous.name);
					teleNums.add(rp.teleoperated.name);
				}
				for (int i = 0; i < Math.min(teamNums.size(), autoNums.size()); i++) {
					autoNums.get(i).setText(teamNums.get(i));
					teleNums.get(i).setText(teamNums.get(i));
				}
				matchField.setText("");
				System.out.println("Successfully imported: " + teamNums.toString());
				matchCount++;
				
			}
		});
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
						for (RobotTabbedPanel<AutonomousRobotPanel, TeleoperatedRobotPanel> rp : U.this.panels) {
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
				// TODO Auto-generated method stub
				
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
		
		JMenuItem ITEM_NEXT = new JMenuItem("NEXT");
		MENU_COMPETITION.add(ITEM_NEXT);
		
	}
	public File getEvent() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setCurrentDirectory(defaultSaveFile);

		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			 return chooser.getSelectedFile();
		} else {
			return null;
		}
	}
	public ArrayList<String> makeArrayList(File file){
		ArrayList<String> out = new ArrayList<String>();
		try {
			out = (ArrayList<String>) FileUtils.read(file);
			
			System.out.println(out.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
		
	}
	
}

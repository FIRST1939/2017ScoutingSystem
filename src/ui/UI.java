package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
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
import tools.FileUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class UI extends UIV3 implements ActionListener {
	
	/**
	 * 
	 */
	
	public JTextField matchField;
	ArrayList<ArrayList<String>> fullMatches = new ArrayList<ArrayList<String>>();
	public int matchCount = 0;
	public UIV3 ui = new UIV3();
	String fileName = "";
	File outputFile = null;
	
	
	public UI() {
		
		JMenuItem mntmMakenewfile = new JMenuItem("MakeNewFile");
		mntmMakenewfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				outputFile = getSave();
				
			}
		});
		MENU_EXPORT.add(mntmMakenewfile);
		
		JMenuItem ITEM_NEXT = new JMenuItem("NEXT");
		MENU_COMPETITION.add(ITEM_NEXT);
		ITEM_IMPORT_TEAM_NUMBERS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				fullMatches = ui.makeFullArray();
				ui.setMatchReset(matchCount);
				
			}
			
		});
		ITEM_NEXT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				ArrayList<String> teamNums =fullMatches.get(matchCount);
				List<RobotNumber> autoNums = new Vector<RobotNumber>();
				List<RobotNumber> teleNums = new Vector<RobotNumber>();
				for (RobotTabbedPanel<AutonomousRobotPanel, TeleoperatedRobotPanel> rp : UI.this.panels) {
					autoNums.add(rp.autonomous.name);
					teleNums.add(rp.teleoperated.name);
				}
				for (int i = 0; i < Math.min(teamNums.size(), autoNums.size()); i++) {
					autoNums.get(i).setText(teamNums.get(i));
					teleNums.get(i).setText(teamNums.get(i));
				}
				matchCount++;
				ArrayList<ArrayList<String>> teamList = makeNewCompiledMatch();
				
				try {
					FileUtils.writeNewMatch(outputFile, teamList);
					System.out.println("Exported: " + teamList);
				} catch (IOException e) {
					e.printStackTrace();
				}
				resetBoard();
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
						String teamString = matchField.getText();
						String[] teams = teamString.split(",");
						ArrayList<String> teamNums = new ArrayList<String>();
						for (String i : teams){
							teamNums.add(i);
						}
						
						List<RobotNumber> autoNums = new Vector<RobotNumber>();
						List<RobotNumber> teleNums = new Vector<RobotNumber>();
						for (RobotTabbedPanel<AutonomousRobotPanel, TeleoperatedRobotPanel> rp : UI.this.panels) {
							autoNums.add(rp.autonomous.name);
							teleNums.add(rp.teleoperated.name);
						}
						for (int i = 0; i < Math.min(teamNums.size(), autoNums.size()); i++) {
							autoNums.get(i).setText(teamNums.get(i));
							teleNums.get(i).setText(teamNums.get(i));
						}
						matchField.setText("");
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
				this.add(new Robot(new AutonomousRobotPanel("Team " + i, Color.RED), new TeleoperatedRobotPanel("Team " + i, Color.RED)));	
			}
			else {
				this.add(new Robot(new AutonomousRobotPanel("Team " + i, Color.BLUE), new TeleoperatedRobotPanel("Team " + i, Color.BLUE)));
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
	public void resetBoard(){
		for (RobotTabbedPanel<AutonomousRobotPanel, TeleoperatedRobotPanel> rp : UI.this.panels){
			//Auto Clears
			rp.autonomous.baselineField.setText("false");
			rp.autonomous.gearField.setText("0");
			rp.autonomous.gearAttempts.setText("0");
			rp.autonomous.lowGoalField.setText("0");
			rp.autonomous.lowGoalAttempts.setText("0");
			rp.autonomous.highGoalField.setText("0");
			rp.autonomous.highGoalAttempts.setText("0");
			//Tele clears
			rp.teleoperated.blocksField.setText("0");
			rp.teleoperated.gearField.setText("0");
			rp.teleoperated.gearAttempts.setText("0");
			rp.teleoperated.lowGoalAttempts.setText("0");
			rp.teleoperated.lowGoalField.setText("0");
			rp.teleoperated.highGoalAttempts.setText("0");
			rp.teleoperated.highGoalField.setText("0");
			rp.teleoperated.climbingAttempts.setText("false");
			rp.teleoperated.climbingField.setText("0");
			
			
		}
	}
	public File getSave(){
		File file= null;
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setCurrentDirectory(defaultSaveFile);
		fc.setDialogTitle("Set Save Location");
		int result = fc.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			
		file = fc.getSelectedFile();
		}
		return file;
	}
	public ArrayList<ArrayList<String>> makeNewCompiledMatch(){
		ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
			for (RobotTabbedPanel<AutonomousRobotPanel, TeleoperatedRobotPanel> rp : UI.this.panels){
				ArrayList<String> teamMatch = new ArrayList<String>();
				teamMatch.add("" + matchCount);
				teamMatch.add("" + rp.autonomous.name.getText());
				teamMatch.add(rp.autonomous.baselineField.getText());
				teamMatch.add(rp.autonomous.gearField.getText());
				teamMatch.add(rp.autonomous.gearAttempts.getText());
				teamMatch.add(rp.autonomous.lowGoalField.getText());
				teamMatch.add(rp.autonomous.lowGoalAttempts.getText());
				teamMatch.add(rp.autonomous.highGoalField.getText());
				teamMatch.add(rp.teleoperated.blocksField.getText());
				teamMatch.add(rp.teleoperated.gearField.getText());
				teamMatch.add(rp.teleoperated.gearAttempts.getText());
				teamMatch.add(rp.teleoperated.lowGoalField.getText());
				teamMatch.add(rp.teleoperated.lowGoalAttempts.getText());
				teamMatch.add(rp.teleoperated.highGoalField.getText());
				teamMatch.add(rp.teleoperated.highGoalAttempts.getText());
				teamMatch.add(rp.teleoperated.climbingField.getText());
				teamMatch.add(rp.teleoperated.climbingAttempts.getText());
				out.add(teamMatch);
			}
		return out;
		
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

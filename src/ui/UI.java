package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.frcteam1939.ui.UIV3;

import tba.v3.models.Match;
import ui.queue.Queuer;
import utils.Images;

/**
 * The user interface for the scouting system.
 * @author Grayson Spidle
 *
 */
public class UI extends UIV3<Robot> {
	
	public static final String BASE_TITLE = "FRC SteamWorks - Scouting Program";
	
	private static final long serialVersionUID = 2892016551422008688L;
	
	public JMenuBar menuBar = new JMenuBar();
	
	public JMenu menu = new JMenu("Temp");
	
	public JMenuItem itemExport = new JMenuItem("Export as .csv");
	public JMenuItem itemNext = new JMenuItem("Next");
	public JMenuItem itemReset = new JMenuItem("Reset");
	public JMenuItem itemImport = new JMenuItem("Import");
	public JMenuItem itemRecoverLastState = new JMenuItem("Recover Last State");
	public JMenuItem itemChangeSavePath = new JMenuItem("Change Save Path");
	
	public JLabel buttonQueuer = new JLabel(new ImageIcon(Images.SHOW_QUEUE));
	public JLabel buttonNext = new JLabel(new ImageIcon(Images.NEXT));
	
	public JTextField idField = new JTextField();
	
	public Queuer qer = new Queuer();
	
	public String matchKey = "";
	public String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop" + System.getProperty("file.separator");

	private boolean write = true;
	
	public UI() {
		buttonQueuer.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				qer.setVisible(true);
				qer.requestFocus();
			}
		});
		
		itemImport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) { 
					try {
						List<String> lines = Files.readAllLines(chooser.getSelectedFile().toPath());
						for (String s : lines) {
							StringTokenizer st = new StringTokenizer(s, ",");
							for (Robot r : panels) {
								for (int i = 0; i < st.countTokens(); i++) {
									if (i == 0) {
										r.setTeamNumber(Integer.parseInt(st.nextToken()));
									}
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				// TODO
				
			}
		});
		
		itemReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});
		
		itemChangeSavePath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String path = JOptionPane.showInputDialog("Change Save Path", filePath);
					path.replaceAll("\\", "/");
					if (path.charAt(path.length() - 1) != '/')
						path += "/";
					filePath = path;
				} catch (NullPointerException e2) {
					System.err.println("Save path change was cancelled.");
				}
				
			}
		});
		
		buttonNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					if (!write) writeData(new File(filePath + matchKey + ".csv"), new File(filePath + matchKey + ".csv"));
					else write = true;
					reset();
					resetPanelNumbers();
					Match match = qer.poll();
					matchKey = match.key;
					setTitle(BASE_TITLE);
					StringTokenizer st = new StringTokenizer(match.toString(), ",");
					for (int i = 0; i < 6; i++) {
						panels.get(i).setTeamNumber(Integer.parseInt(st.nextToken()));
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					if (qer.queue.isEmpty()) {
						System.err.println("Queue is empty.");
						write = false;
					} else {
						System.err.println("Not enough panels have been created.");
					}
					return;
				} catch (NoSuchElementException e2) {
					System.err.println("Not enough team numbers in the queue.");
					return;
				} catch (NullPointerException e2) {
					System.err.println("Queue is empty.");
					write = false;
					return;
				}
			}
		});
		
		menu.add(itemImport);
		menu.add(itemChangeSavePath);
		menu.add(itemReset);
		
		menuBar.add(menu);
		menuBar.add(buttonQueuer);
		menuBar.add(buttonNext);
		this.setJMenuBar(menuBar);
		
		// Initializing/Adding all the RobotTabbedPanels
		for (int i = 1; i < 7; i++) {
			if (i <= 3) {
				Robot pane = new Robot(new AutonomousRobotPanel(0 - i, Color.BLUE),
						new TeleoperatedRobotPanel(0 - i, Color.BLUE));
				pane.setTabPlacement(JTabbedPane.TOP);
				this.add(pane);
			} else {
				Robot pane = new Robot(new AutonomousRobotPanel(0 - i, Color.RED),
						new TeleoperatedRobotPanel(0 - i, Color.RED));
				pane.setTabPlacement(JTabbedPane.BOTTOM);
				this.add(pane);
			}
		}
		
		this.setLayout(new GridLayout(2, 3));
		this.pack();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		this.setVisible(true);
		this.setTitle(BASE_TITLE);
		this.setIconImage(Images.LOGO);
	}
	
	public void resetPanelNumbers() {
		for (int i = 0; i < 6; i++) {
			panels.get(i).setTeamNumber(0 - (i + 1));
		}
	}
	
	/**
	 * Returns an unmodifiable list of the panels
	 * @return
	 */
	public List<Robot> getPanels() {
		return Collections.unmodifiableList(panels);
	}
	
	public void writeAutonomousData(File autoFile) {
		if (!autoFile.exists()) {
			try {
				autoFile.mkdirs();
				autoFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Failed to create file " + autoFile.toString());
				return;
			}
		}
		
		for (int i = 0; i < 6; i++) {
			int teamNumber = panels.get(i).getTeamNumber();
			
			StringJoiner auto = new StringJoiner(",");
			auto.add(String.valueOf(teamNumber));
			List<String> autoScores = getAutonomousScores(i);
			for (String s : autoScores) {
				auto.add(s);
			}
			
			try {
				Files.write(autoFile.toPath(), (auto.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e1) {
				System.err.println("Failed to write " + auto.toString() + " to " + autoFile.toPath().toString());
				System.err.println(e1.getMessage());
			}
		}
	}
	
	public void writeTeleoperatedData(File teleFile) {
		if (!teleFile.exists()) {
			try {
				teleFile.mkdirs();
				teleFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Failed to create file " + teleFile.toString());
				return;
			}
		}
		
		for (int i = 0; i < 6; i++) {
			int teamNumber = panels.get(i).getTeamNumber();
			
			StringJoiner tele = new StringJoiner(",");
			tele.add(String.valueOf(teamNumber));
			List<String> teleScores = getTeleoperatedScores(i);
			for (String s : teleScores) {
				tele.add(s);
			}
			
			try {
				Files.write(teleFile.toPath(), (tele.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e1) {
				System.err.println("Failed to write " + tele.toString() + " to " + teleFile.toPath().toString());
				System.err.println(e1.getMessage());
			}
		}
	}
	
	public void writeData(File autoFile, File teleFile) {
		new Thread() {
			@Override
			public void run() {
				synchronized (autoFile) {
					writeAutonomousData(autoFile);
				}
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				synchronized (teleFile) {
					writeTeleoperatedData(teleFile);
				}
			}
		}.start();
	}
	
	public void generateTestData() {
		for (Robot r : panels) {
			for (int i = 0; i < r.autonomous.fields.size(); i++) {
				r.autonomous.fields.get(i).setText("" + i);
			}
			for (int i = 0; i < r.teleoperated.fields.size(); i++) {
				r.teleoperated.fields.get(i).setText("" + i);
			}
		}
	}
	
	@Override
	public void setTitle(String title) {
		super.setTitle(title + " | " + filePath + matchKey + ".csv");
	}
	
	public String getMatchKey() {
		return matchKey;
	}
}

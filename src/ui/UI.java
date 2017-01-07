package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import buildingBlocks.UIV3;
import elements.Robot;

public class UI extends UIV3 implements ActionListener {
	
	public UI() {
		
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
		this.setTitle("Java Scouting Program Example");
	}
	
}

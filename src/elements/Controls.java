package elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import buildingBlocks.ControlScheme;
import buildingBlocks.RobotTabbedPanel;
import buildingBlocks.UIV3;
import buildingBlocks.controllerElements.GamepadController;
import buildingBlocks.controllerElements.StickController;
import ui.UI;

/**
 * This class governs modifies the UI according to controller inputs. It is flexible with the difference in controller types.
 * @author Grayson Spidle
 *
 */
public class Controls extends ControlScheme {
	
	private final long DELAY = 10; // Milliseconds
	
	/**
	 * The constructor. Creates 2 ActionListeners for the user to use.
	 * @param arg0 The UI the ControlScheme should modify.
	 */
	public Controls(UI arg0) {
		super(arg0);
		setAutonomousActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					GamepadController controller = (GamepadController) e.getSource();
					RobotTabbedPanel panel = ui.getRobotTabbedPanel(controller.robotPanelNumber);
					
					if (controller.isAPressed()) { // This is just for testing
						int value = Integer.parseInt(panel.autonomous.fields.get(0).getText());
						value++;
						panel.autonomous.fields.get(0).setText("" + value);
					}
					
					// Switches between autonomous and teleoperated and their respective control schemes
					if (controller.isStartPressed()) {
						panel.setSelectedIndex(1);
						controller.setActionListener(teleoperated);
					}
					
				} catch (ClassCastException e1) {
					StickController controller = (StickController) e.getSource();
					RobotTabbedPanel panel = ui.getRobotTabbedPanel(controller.robotPanelNumber);
					
					if (controller.isAPressed()) { // This is just for testing
						int value = Integer.parseInt(panel.autonomous.fields.get(0).getText());
						value++;
						panel.autonomous.fields.get(0).setText("" + value);
					}
					
					// Switches between autonomous and teleoperated and their respective control schemes
					if (controller.isStartPressed()) {
						panel.setSelectedIndex(1);
						controller.setActionListener(teleoperated);
					}
				}
				
				// Suspends the thread for DELAY amount of miliseconds
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e1) {
					System.err.println("Unable to sleep the thread.");
					e1.printStackTrace();
				}
				
			}
		});
		
		setTeleoperatedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GamepadController controller = (GamepadController) e.getSource();
					RobotTabbedPanel panel = ui.getRobotTabbedPanel(controller.robotPanelNumber);
					
					if (controller.isAPressed()) { // This is just for testing
						int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
						value++;
						panel.teleoperated.fields.get(0).setText("" + value);
					}
					
					
					// Switches between autonomous and teleoperated and their respective control schemes
					if (controller.isStartPressed()) { 
						panel.setSelectedIndex(0);
						controller.setActionListener(autonomous);
					}
					
					// Suspends the thread for DELAY amount of miliseconds
					try {
						Thread.sleep(DELAY);
						System.err.println("Unable to sleep the thread.");
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (ClassCastException e2) {
					StickController controller = (StickController) e.getSource();
					RobotTabbedPanel panel = ui.getRobotTabbedPanel(controller.robotPanelNumber);
					
					if (controller.isAPressed()) { // This is just for testing
						int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
						value++;
						panel.teleoperated.fields.get(0).setText("" + value);
					}
					
					
					// Switches between autonomous and teleoperated and their respective control schemes
					if (controller.isStartPressed()) { 
						panel.setSelectedIndex(0);
						controller.setActionListener(autonomous);
					}
					
					// Suspends the thread for DELAY amount of miliseconds
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e1) {
						System.err.println("Unable to sleep the thread.");
						e1.printStackTrace();
					}
				}
				
			}
		});
		
	}
	
}

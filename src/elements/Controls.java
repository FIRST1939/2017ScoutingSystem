package elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import buildingBlocks.ControlScheme;
import buildingBlocks.controllerElements.JController;
import ui.UI;

/**
 * This class governs modifies the UI according to controller inputs. It is flexible with the difference in controller types.
 * @author Grayson Spidle
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
				JController controller = (JController) e.getSource();
				Robot panel = (Robot) ui.getRobotTabbedPanel(controller.robotPanelNumber);
				if (controller.isLTPressed()){
					int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
					value++;
					panel.autonomous.highGoalField.setText("" + value);
				}
				if (controller.isRTPressed()){
					int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
					value++;
					panel.autonomous.lowGoalField.setText("" + value);
				}
				if (controller.isAPressed()){
					int value = Integer.parseInt(panel.autonomous.gearField.getText());
					value++;
					panel.autonomous.gearField.setText("" + value);
				}
				if (controller.isLBPressed()){
					int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
					value++;
					panel.autonomous.baselineField.setText("" + value);
					
				}
				
				// Switches between autonomous and teleoperated and their respective control schemes
				if (controller.isStartPressed()) {
					panel.setSelectedIndex(1);
					controller.setActionListener(teleoperated);
				}
				
				// Suspends the thread for DELAY amount of milliseconds
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e1) {
					System.err.println("Unable to sleep the controls.");
					e1.printStackTrace();
				}
			}
			
		});
		
		setTeleoperatedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JController controller = (JController) e.getSource();
				Robot panel = (Robot) ui.getRobotTabbedPanel(controller.robotPanelNumber);

				
				if (controller.isLTPressed()){
					int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
					value++;
					panel.teleoperated.highGoalField.setText("" + value);
				}
				if (controller.isRTPressed()){
					int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
					value++;
					panel.teleoperated.lowGoalField.setText("" + value);
				}
				if (controller.isAPressed()){
					int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
					value++;
					panel.teleoperated.gearField.setText("" + value);
				}
				if (controller.isLBPressed()){
					int value = Integer.parseInt(panel.teleoperated.fields.get(0).getText());
					value++;
					panel.teleoperated.blocksField.setText("" +value);;
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
			}
		});
	}
	
}

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
				if (controller.isLBPressed()){
					int value = Integer.parseInt(panel.autonomous.highGoalField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
					panel.autonomous.highGoalField.setText("" + value);
				}
				if (controller.isRBPressed()){
					int value = Integer.parseInt(panel.autonomous.lowGoalField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
					panel.autonomous.lowGoalField.setText("" + value);
				}
				if (controller.isAPressed()){
					int value = Integer.parseInt(panel.autonomous.gearField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
					panel.autonomous.gearField.setText("" + value);
				}
				if (controller.isBPressed()){
					int value = Integer.parseInt(panel.autonomous.baselineField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
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

				
				
				if (controller.isLBPressed()){
					
					int value = Integer.parseInt(panel.teleoperated.highGoalField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
					panel.teleoperated.highGoalField.setText("" + value);
				}
				
				if (controller.isRBPressed()){
					int value = Integer.parseInt(panel.teleoperated.lowGoalField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
					panel.teleoperated.lowGoalField.setText("" + value);
				}
				if (controller.isAPressed()){
					int value = Integer.parseInt(panel.teleoperated.gearField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
					panel.teleoperated.gearField.setText("" + value);
				}
				if (controller.isBPressed()){
					int value = Integer.parseInt(panel.teleoperated.blocksField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
					panel.teleoperated.blocksField.setText("" +value);;
				}
				
				
				
				
				
				if (controller.isYPressed()){
					int value = Integer.parseInt(panel.teleoperated.climbingField.getText());
					if(controller.isLSHeld()){
						value--;
					}
					else{
						value++;
					}
					panel.teleoperated.climbingField.setText("" +value);;
				}
				//Removing stuff
				
				
							
				
				// Switches between autonomous and teleoperated and their respective control schemes
				if (controller.isStartPressed()) { 
					panel.setSelectedIndex(0);
					controller.setActionListener(autonomous);
				}
				
				// Suspends the thread for DELAY amount of miliseconds
				try {
					Thread.sleep(DELAY);
					
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					System.err.println("Unable to sleep the thread.");
				}
			}
		});
	}
	
}

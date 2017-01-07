package elements;

import javax.swing.JTextField;

import buildingBlocks.RobotNumber;
import buildingBlocks.RobotPanel;
import buildingBlocks.RobotTabbedPanel;

/**
 * This class manages a panel that governs the Autonomous and Teleoperated panels.
 * @author Grayson Spidle
 *
 */
public class Robot extends RobotTabbedPanel {
	
	private static final long serialVersionUID = 4952300412598887831L;
	
	private RobotNumber number = new RobotNumber("" + null);
	
	public Robot(RobotPanel autonomous, RobotPanel teleoperated) {
		super(autonomous, teleoperated);
		number.setText(autonomous.number.getText());
	}
	
	@Override
	public String getTeamNumber() {
		return number.getText();
	}
	
	public void setEditable(boolean arg0) {
		for (JTextField field : autonomous.fields) {
			field.setEditable(arg0);
		}
		for (JTextField field : teleoperated.fields) {
			field.setEditable(arg0);
		}
	}

}

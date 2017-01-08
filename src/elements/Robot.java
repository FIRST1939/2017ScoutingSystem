package elements;

import buildingBlocks.RobotNumber;
import buildingBlocks.RobotTabbedPanel;
import buildingBlocks.ScoreField;
import ui.AutonomousRobotPanel;
import ui.TeleoperatedRobotPanel;

/**
 * This class manages a panel that governs the Autonomous and Teleoperated panels.
 * @author Grayson Spidle
 *
 */
public class Robot extends RobotTabbedPanel<AutonomousRobotPanel,TeleoperatedRobotPanel> {
	
	private static final long serialVersionUID = 4952300412598887831L;
	
	private RobotNumber number = new RobotNumber("" + null);
	
	public Robot(AutonomousRobotPanel autonomous, TeleoperatedRobotPanel teleoperated) {
		super(autonomous, teleoperated);
		number.setText(autonomous.number.getText());
	}
	
	@Override
	public String getTeamNumber() {
		return number.getText();
	}
	
	public void setEditable(boolean arg0) {
		for (ScoreField field : autonomous.fields) {
			field.setEditable(arg0);
		}
		for (ScoreField field : teleoperated.fields) {
			field.setEditable(arg0);
		}
	}

}

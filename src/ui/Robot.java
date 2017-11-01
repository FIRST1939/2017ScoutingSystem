package ui;

import com.frcteam1939.ui.RobotNumber;
import com.frcteam1939.ui.RobotTabbedPanel;
import com.frcteam1939.ui.ScoreField;

/**
 * This class manages a panel that governs the Autonomous and Teleoperated panels.
 * @author Grayson Spidle
 *
 */
public class Robot extends RobotTabbedPanel<AutonomousRobotPanel,TeleoperatedRobotPanel> {
	
	private static final long serialVersionUID = 4952300412598887831L;
	
	private RobotNumber number = new RobotNumber(-1);
	
	public Robot(AutonomousRobotPanel autonomous, TeleoperatedRobotPanel teleoperated) {
		super(autonomous, teleoperated);
		number.set(autonomous.number.get());
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

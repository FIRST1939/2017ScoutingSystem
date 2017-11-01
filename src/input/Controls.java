package input;

import com.frcteam1939.controller.JController;

import ui.Robot;
import ui.UI;

/**
 * This class is a 1 instance class. In order for everything to work, you must
 * define the ui the controls are to modify,
 * 
 * @author Grayson Spidle
 */
public class Controls {
	
	public static UI ui;
	
	public static void pollController(int panelNumber, JController controller) {
		controller.poll();
		if (ui.getRobotTabbedPanel(panelNumber).getSelectedIndex() == 0) {
			useAutonomousControls(panelNumber, controller);
		} else if (ui.getRobotTabbedPanel(panelNumber).getSelectedIndex() == 1) {
			useTeleoperatedControls(panelNumber, controller);
		} else {
			return;
		}
	}
	
	private static void useAutonomousControls(int panelNumber, JController controller) {
		if (panelNumber < 0 || panelNumber > 5) 
			return;
		Robot panel = (Robot) ui.getRobotTabbedPanel(panelNumber);
		if (controller.isLBPressed()) {
			if (controller.isLTHeld()) {
				int value = Integer.parseInt(panel.autonomous.highGoalAttempts.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.autonomous.highGoalAttempts.setText("" + value);
			} else {
				int value = Integer.parseInt(panel.autonomous.highGoalField.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.autonomous.highGoalField.setText("" + value);
			}

		}
		if (controller.isRBPressed()) {
			if (controller.isLTHeld()) {
				int value = Integer.parseInt(panel.autonomous.lowGoalAttempts.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.autonomous.lowGoalAttempts.setText("" + value);
			} else {
				int value = Integer.parseInt(panel.autonomous.lowGoalField.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.autonomous.lowGoalField.setText("" + value);
			}
		}
		if (controller.isAPressed()) {
			if (controller.isLTHeld()) {
				int value = Integer.parseInt(panel.autonomous.gearAttempts.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.autonomous.gearAttempts.setText("" + value);
			} else {
				int value = Integer.parseInt(panel.autonomous.gearField.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.autonomous.gearField.setText("" + value);
			}
		}
		if (controller.isBPressed()) {
			boolean value = Boolean.parseBoolean(panel.autonomous.baselineField.getText());
			if (controller.isLSHeld()) {
				value = !value;
			} else {
				value = !value;
			}
			panel.autonomous.baselineField.setText("" + value);
		}

		// Switches between autonomous and teleoperated and their
		// respective control schemes
		if (controller.isStartPressed()) {
			panel.setSelectedIndex(1);
		}

		if (controller.isBackPressed()) {
			controller.rumble(0.5f);
		}
	}
	
	private static void useTeleoperatedControls(int panelNumber, JController controller) {
		if (panelNumber < 0 || panelNumber > 5) 
			return;
		Robot panel = (Robot) ui.getRobotTabbedPanel(panelNumber);
		if (controller.isLBPressed()) {
			if (controller.isLTHeld()) {
				int value = Integer.parseInt(panel.teleoperated.highGoalAttempts.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.teleoperated.highGoalAttempts.setText("" + value);
			} else {
				int value = Integer.parseInt(panel.teleoperated.highGoalField.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.teleoperated.highGoalField.setText("" + value);
			}
		}

		if (controller.isRBPressed()) {
			if (controller.isLTHeld()) {
				int value = Integer.parseInt(panel.teleoperated.lowGoalAttempts.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.teleoperated.lowGoalAttempts.setText("" + value);
			} else {
				int value = Integer.parseInt(panel.teleoperated.lowGoalField.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.teleoperated.lowGoalField.setText("" + value);
			}
		}
		if (controller.isAPressed()) {
			if (controller.isLTHeld()) {
				int value = Integer.parseInt(panel.teleoperated.gearAttempts.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.teleoperated.gearAttempts.setText("" + value);
			} else {
				int value = Integer.parseInt(panel.teleoperated.gearField.getText());
				if (controller.isLSHeld()) {
					value--;
				} else {
					value++;
				}
				panel.teleoperated.gearField.setText("" + value);
			}
		}
		if (controller.isBPressed()) {
			int value = Integer.parseInt(panel.teleoperated.blocksField.getText());
			if (controller.isLSHeld()) {
				value--;
			} else {
				value++;
			}
			panel.teleoperated.blocksField.setText("" + value);
			;
		}
		if (controller.isXPressed()) {
			boolean value = Boolean.parseBoolean(panel.teleoperated.deadBotField.getText());
			value = !value;
			panel.teleoperated.deadBotField.setText("" + value);
		}
		if (controller.isYPressed()) {
			if (controller.isLTHeld()) {
				boolean value = Boolean.parseBoolean(panel.teleoperated.climbingAttempts.getText());
				panel.teleoperated.climbingAttempts.setText("" + !value);
			} else {
				boolean value = Boolean.parseBoolean(panel.teleoperated.climbingField.getText());
				value = !value;
				panel.teleoperated.climbingField.setText("" + value);
			}
		}
		if (controller.isBackPressed()) {
			controller.rumble(0.5f);
		}

		// Switches between autonomous and teleoperated and their
		// respective control schemes
		if (controller.isStartPressed()) {
			panel.setSelectedIndex(0);
		}
	}
	
	
	public static void setUI(UI arg0) {
		ui = arg0;
	}
}

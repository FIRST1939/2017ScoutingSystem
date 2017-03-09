package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Vector;

import buildingBlocks.controllerElements.GamepadController;
import buildingBlocks.controllerElements.StickController;
import elements.Controls;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Rumbler;
import ui.UI;

/**
 * This is the main class with the main method. Future Scouting Systems should either use this code or 
 * create their own. In the case of creating your own you should have, at bare minimum, a while loop that will poll controllers'
 * inputs, and stick that information into some kind of control scheme. 
 * @author Grayson Spidle
 *
 */
public class Main {

	private static UI ui;
	private static List<Controller> controllers = new Vector<Controller>();
	private static List<StickController> stickControllers = new Vector<StickController>();
	private static List<GamepadController> gamepadControllers = new Vector<GamepadController>();
	private static Controls controls;

	private static boolean closeRequested = false;
	
	/**
	 * Initialize me to start the program. I gather all available controllers and assign them to panels. 
	 * @param ui The UI for the program to show and modify.
	 */
	public static void main(String[] args) {
		
		ui = new UI();
		System.setErr(System.err);
		controls = new Controls(ui);
		
		ui.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowClosing(WindowEvent arg0) {
//				closeRequested = true;
//				ui.CONSOLE.out.close();
//				ui.CONSOLE.err.close();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {}

			@Override
			public void windowOpened(WindowEvent arg0) {}
		});
		
		prepareControllers();

		// *** MAIN LOOP ***
		while (!closeRequested) {
			for (GamepadController ct : gamepadControllers) {
				ct.pollControllerInput();
			}
			for (StickController ct : stickControllers) {
				ct.pollControllerInput();
			}
		}
		// *** END OF MAIN LOOP ***
	}
	
	/**
	 * Discovers connected controllers, then handles the resulting errors, then
	 * adds an instance of ControlScheme to every controller.
	 */
	private static void prepareControllers() {

		int i = 0;

		for (Controller c : getAllControllersOfType(Controller.Type.GAMEPAD)) {
			gamepadControllers.add(new GamepadController(c, i));
			for (Rumbler r : c.getRumblers()) {
				r.rumble(0.5f);
			}
			i++;
		}

		for (Controller c : getAllControllersOfType(Controller.Type.STICK)) {
			stickControllers.add(new StickController(c, i));
			for (Rumbler r : c.getRumblers()) {
				r.rumble(0.5f);
			}
			i++;
		}

		System.out.println(controllers.size() + "/6 controllers are connected.");
		System.out.println(gamepadControllers.size() + " are Gamepad Controllers.");
		System.out.println(stickControllers.size() + " are Stick Controllers.");

		// *** ERROR HANDLING ***
		if (i < 5) {
			System.err.println("Panels " + (i + 1) + "-6 are not being controlled.");
		} else if (i > 6) {
			System.err.println("Too many controllers are connected.");
		}
		// *** END OF ERROR HANDLING ***
		

		// Adding the ControlScheme to every controller
		for (GamepadController ct : gamepadControllers) {
			ct.setActionListener(controls.autonomous);
		}
		for (StickController ct : stickControllers) {
			ct.setActionListener(controls.autonomous);
		}
	}
	
	/**
	 * Gets all controllers of the specified type.
	 * @param controllerType The specified Controller.Type
	 * @return Returns a Vector
	 */
	private static List<Controller> getAllControllersOfType(Controller.Type controllerType) {

		Vector<Controller> output = new Vector<Controller>();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller c : controllers) {
			if (c.getType() == controllerType) {
				output.add(c);
			}
		}
		return output;

	}

	/**
	 * Gets all controllers regardless of type. Includes mice and other input devices.
	 * @return Returns a Vector
	 */
	@SuppressWarnings("unused")
	private static List<Controller> getAllConnectedControllers() {
		Vector<Controller> output = new Vector<Controller>();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller c : controllers) {
			output.add(c);
		}
		return output;
	}
}

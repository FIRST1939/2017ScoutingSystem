package main;

import static net.java.games.input.Controller.Type.GAMEPAD;
import static net.java.games.input.Controller.Type.STICK;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import buildingBlocks.controllerElements.GamepadController;
import buildingBlocks.controllerElements.JController;
import buildingBlocks.controllerElements.StickController;
import elements.Controls;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
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
	private static Map<Integer, JController> controllers = new HashMap<Integer, JController>();
	private static Controls controls;

	private static boolean closeRequested = false;
	
	/**
	 * Initialize me to start the program. I gather all available controllers and assign them to panels. 
	 * @param ui The UI for the program to show and modify.
	 */
	public static void main(String[] args) {
		ui = new UI();
		controls = new Controls(ui);
		
		ui.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowClosing(WindowEvent arg0) {
				closeRequested = true;
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
			for (JController ct : controllers.values()) {
				ct.pollControllerInput();
			}
			System.gc();
			try {
				Thread.sleep(16,500000); // Limiting refresh rate to about 30 refreshes per second
			} catch (InterruptedException e) {
				e.printStackTrace();
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

		for (Controller c : getAllControllersOfType(GAMEPAD)) {
			controllers.put(i, new GamepadController(c, i));
			i++;
		}

		for (Controller c : getAllControllersOfType(STICK)) {
			controllers.put(i, new StickController(c, i));
			i++;
		}

		System.out.println(controllers.size() + "/6 controllers connected.");

		// *** ERROR HANDLING ***
		if (i < 5) {
			System.err.println("Panels " + (i + 1) + "-6 are not being controlled.");
		} else if (i > 6) {
			for (Map.Entry<Integer, JController> e : controllers.entrySet()) {
				if (e.getKey() >= 6) {
					controllers.remove(e.getKey());
				}
			}
		}
		// *** END OF ERROR HANDLING ***
		
		for (JController ct : controllers.values()) {
			prepareController(ct);
		}
	}
	
	private static void prepareController(JController arg0) {
		arg0.addActionListener(controls.autonomous);
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
	private static List<Controller> getAllConnectedDevices() {
		Vector<Controller> output = new Vector<Controller>();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller c : controllers) {
			output.add(c);
		}
		return output;
	}

}

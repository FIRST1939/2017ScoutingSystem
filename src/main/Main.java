package main;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.frcteam1939.controller.GamepadController;
import com.frcteam1939.controller.JController;
import com.frcteam1939.controller.StickController;

import input.ControllerUtils;
import input.Controls;
import net.java.games.input.Controller;
import net.java.games.input.Rumbler;
import ui.UI;

/**
 * This is the main class with the main method. 
 * @author Grayson Spidle
 *
 */
public class Main {

	public static final File recoveryFile = new File("recovery.txt");
	
	/**
	 * This dictates how many times the program will poll the controls per second. Similar to FPS for video games, but based around polling controls. Minimum should be 10. 
	 */
	private static final int PPS_LOCK = 10;
	
	private static UI ui;
	private static Map<Integer, JController> controllers = new HashMap<Integer, JController>();
	private static List<JController> extraControllers = new Vector<JController>();
	
	private static boolean closeRequested = false;
	private static long last = System.currentTimeMillis();
	
	/**
	 * The main method for the entire scouting system. Run this method to start the program. 
	 * @param ui The UI for the program to show and modify.
	 */
	public static void main(String[] args) {
		ui = new UI();
		Controls.setUI(ui);
		prepareControllers();
		while (!closeRequested) {
			for (Map.Entry<Integer, JController> e : controllers.entrySet()) {
				try {
					if (e.getKey() < 6) {
						Controls.pollController(e.getKey(), e.getValue());
					}
				} catch (NullPointerException e1) {
					if (!extraControllers.isEmpty()) {
						controllers.put(e.getKey(), extraControllers.get(0));
						extraControllers.remove(0);
					} else {
						controllers.remove(e.getKey());
					}
				}
			}
			long timeToSleep = (1000 / PPS_LOCK) - (System.currentTimeMillis() - last);
			if (timeToSleep > 0) {
				try {
					Thread.sleep(timeToSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			last = System.currentTimeMillis();
		}
	}
	
	/**
	 * Discovers connected controllers, then handles the resulting errors, then
	 * adds an ActionListener to every controller. It rumbles each controller it finds.
	 */
	private static void prepareControllers() {
		int i = 0;
		for (Controller c : ControllerUtils.getControllersOfType(Controller.Type.GAMEPAD)) {
			if (i < 6) {
				controllers.put(i, new GamepadController(c));
			} else {
				extraControllers.add(new GamepadController(c));
			}
			for (Rumbler r : c.getRumblers()) {
				r.rumble(0.5f);
			}
			i++;
		}

		for (Controller c : ControllerUtils.getControllersOfType(Controller.Type.STICK)) {
			if (i < 6) {
				controllers.put(i, new StickController(c));
			} else {
				extraControllers.add(new StickController(c));
			}
			for (Rumbler r : c.getRumblers()) {
				r.rumble(0.5f);
			}
			i++;
		}

		System.out.println(controllers.size() + "/6 controllers are connected.");
		
		if (i < 5) {
			System.err.println("Panels " + (i + 1) + "-6 are not being controlled.");
		} else if (i > 6) {
			System.err.println("Excess controllers are detected.");
		}
	}
	
}

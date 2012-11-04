package bs;

import bs.ui.BaseStationGui;

/**
 * Entry-point to create and initialize the Base Station.
 */
public class BaseStation {

	/**
	 * Main entry point into the Base Station program
	 */
	public static void main(String[] args) {
		// Connection connection = new BluetoothConnection();
		// Connection connection = new StdOutConnection();
		Connection connection = new StdIOConnection();

		RobotController robotController = new RobotController(connection);
		BaseStationGui gui = new BaseStationGui(robotController);
		gui.generateBaseStationGUI();
	}
}

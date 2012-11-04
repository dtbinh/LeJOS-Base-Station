package bs;

/**
 * Manages the UI of the Base Station
 */
public class BaseStationGui implements ConnectionStateListener,
		RobotStateListener {

	RobotController robotController;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            The robot controller to use to interact with the robot
	 */
	public BaseStationGui(RobotController controller) {

	}

	/**
	 * Initializes and displays the GUI
	 */
	public void generateBaseStationGUI() {

	}

	@Override
	public void connectionEstablished() {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionLost() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged() {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionAttemptFailed() {
		// TODO Auto-generated method stub
		
	}
}

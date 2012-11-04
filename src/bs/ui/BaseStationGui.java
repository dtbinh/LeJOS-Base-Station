package bs.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bs.ConnectionStateListener;
import bs.RobotController;
import bs.RobotStateListener;

/**
 * Manages the UI of the Base Station
 */
public class BaseStationGui {
	private RobotController robotController;

	private JFrame window;

	private RobotMovementPanel movementPanel;

	private TelemetryDisplayPanel telemetryPanel;

	private ConnectionManagerPanel connectionPanel;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            The robot controller to use to interact with the robot
	 */
	public BaseStationGui(RobotController controller) {
		robotController = controller;
	}

	/**
	 * Initializes and displays the GUI
	 */
	public void generateBaseStationGUI() {
		window = new JFrame();

		movementPanel = new RobotMovementPanel(robotController);
		telemetryPanel = new TelemetryDisplayPanel(robotController);
		connectionPanel = new ConnectionManagerPanel(
				robotController.getConnection());
		window.setLayout(new BorderLayout());
		window.add(connectionPanel, BorderLayout.NORTH);
		window.add(telemetryPanel, BorderLayout.WEST);
		window.add(movementPanel, BorderLayout.CENTER);

		window.setSize(500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}
}

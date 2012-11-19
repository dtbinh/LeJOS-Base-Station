package bs.ui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
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
	
	private NudgePanel nudgePanel;

	private TelemetryDisplayPanel telemetryPanel;

	private ConnectionManagerPanel connectionPanel;
	
	private RobotArmPanel armPanel;
	
	private RobotModePanel modePanel;

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
		armPanel = new RobotArmPanel(robotController);
		nudgePanel = new NudgePanel(robotController);
		modePanel = new RobotModePanel(robotController);
		window.setLayout(new BorderLayout());
		window.add(connectionPanel, BorderLayout.NORTH);
		window.add(telemetryPanel, BorderLayout.CENTER);
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.add(movementPanel);
		controlPanel.add(armPanel);
		controlPanel.add(nudgePanel);
		controlPanel.add(modePanel);
		window.add(controlPanel, BorderLayout.SOUTH);

		window.setSize(500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}
}

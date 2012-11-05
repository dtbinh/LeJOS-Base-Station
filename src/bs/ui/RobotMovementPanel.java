package bs.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import bs.RobotController;

/**
 * A button enabling control of a robot's motors by toggling the speeds of the
 * left and right motors
 */
public class RobotMovementPanel extends JPanel {
	private RobotController controller;

	/**
	 * Constructor
	 * 
	 * Creates a new RobotMovementPanel
	 */
	public RobotMovementPanel(RobotController controller) {
		this.controller = controller;
		this.setLayout(new GridLayout(3, 3));
		// move forward/left
		add(new RobotMovementButton(controller,
				RobotController.MOTOR_SPEED_STOP,
				RobotController.MOTOR_SPEED_MAX_FWD));
		// move forward
		add(new RobotMovementButton(controller,
				RobotController.MOTOR_SPEED_MAX_FWD,
				RobotController.MOTOR_SPEED_MAX_FWD));
		// move forward/right
		add(new RobotMovementButton(controller,
				RobotController.MOTOR_SPEED_MAX_FWD,
				RobotController.MOTOR_SPEED_STOP));

		// move left
		add(new RobotMovementButton(controller,
				RobotController.MOTOR_SPEED_MAX_REV,
				RobotController.MOTOR_SPEED_MAX_FWD));
		// stop
		add(new RobotMovementButton(controller));
		// move right
		add(new RobotMovementButton(controller,
				RobotController.MOTOR_SPEED_MAX_FWD,
				RobotController.MOTOR_SPEED_MAX_REV));

		// move reverse/left
		add(new RobotMovementButton(controller,
				RobotController.MOTOR_SPEED_STOP,
				RobotController.MOTOR_SPEED_MAX_REV));
		// move reverse
		add(new RobotMovementButton(controller,
				RobotController.MOTOR_SPEED_MAX_REV,
				RobotController.MOTOR_SPEED_MAX_REV));
		// move reverse/right
		add(new RobotMovementButton(controller,
				RobotController.MOTOR_SPEED_MAX_REV,
				RobotController.MOTOR_SPEED_STOP));
	}
}

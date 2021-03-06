package bs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import bs.Log;
import bs.RobotController;

/**
 * A button for controlling robot movement. On click, the button will send a
 * movement request with prespecified parameters
 */
public class RobotMovementButton extends JButton {
	private int speedLeft = RobotController.MOTOR_SPEED_STOP;
	private int speedRight = RobotController.MOTOR_SPEED_STOP;

	/**
	 * @param leftSpeed
	 *            The speed to set to the left motor when moving the robot
	 */
	public void setSpeedLeft(int leftSpeed) {
		speedLeft = leftSpeed;
	}

	/**
	 * @param rightSpeed
	 *            The speed to set to the right motor when moving the robot
	 */
	public void setSpeedRight(int rightSpeed) {
		speedRight = rightSpeed;
	}

	/**
	 * Constructor
	 * 
	 * Creates a RobotMovementButton which will stop the robot
	 * 
	 * @param controller
	 *            The RobotController representing the robot to move
	 */
	public RobotMovementButton(final RobotController controller) {
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Log.verbose(this, "Button pushed");
				controller.sendMove(speedLeft, speedRight);
			}
		});
	}

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            The RobotController representing the robot to move
	 * @param speedLeft
	 *            The speed to set to the left motor when moving the robot
	 * @param speedRight
	 *            The speed to set to the right motor when moving the robot
	 */
	public RobotMovementButton(RobotController controller, int speedLeft,
			int speedRight) {
		this(controller);
		setSpeedLeft(speedLeft);
		setSpeedRight(speedRight);
	}
}

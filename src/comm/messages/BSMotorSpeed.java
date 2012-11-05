package comm.messages;

import comm.Message;

/**
 * A message commanding the robot to change the speed of it's drive motors
 */
public class BSMotorSpeed extends Message {
	private static final String NAME = "motor_speed";

	private static final int NUM_PARAMETERS = 2;

	private static final int PARAM_INDEX_SPEED_LEFT = 0;
	private static final int PARAM_INDEX_SPEED_RIGHT = 1;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            The message id
	 * @param speedLeft
	 *            The speed to set the left motor
	 * @param speedRight
	 *            The speed to set the right motor
	 */
	public BSMotorSpeed(int id, int speedLeft, int speedRight) {
		super(id, NAME, NUM_PARAMETERS);
		setSpeedLeft(speedLeft);
		setSpeedRight(speedRight);
	}

	/**
	 * @param speedLeft
	 *            The speed to set the left motor
	 */
	public void setSpeedLeft(int speedLeft) {
		setLongParameter(PARAM_INDEX_SPEED_LEFT, speedLeft);
	}

	/**
	 * @param speedLeft
	 *            The speed to set the right motor
	 */
	public void setSpeedRight(int speedRight) {
		setLongParameter(PARAM_INDEX_SPEED_RIGHT, speedRight);
	}

	/**
	 * @return The speed to set the left motor
	 */
	public int getSpeedLeft() {
		return (int) getLongParameter(PARAM_INDEX_SPEED_LEFT);
	}

	/**
	 * @return The speed to set the right motor
	 */
	public int getSpeedRight() {
		return (int) getLongParameter(PARAM_INDEX_SPEED_RIGHT);
	}
}

package comm.messages;

import comm.Message;

/**
 * A message commanding the robot to change the speed of it's drive motors
 */
public class BSArmAngle extends Message {
	private static final String NAME = "arm_angle";

	private static final int NUM_PARAMETERS = 1;

	private static final int PARAM_INDEX_ARM_ANGLE = 0;;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            The message id
	 * @param armAngle
	 *            The angle at which to set the arm
	 */
	public BSArmAngle(int id, int armAngle) {
		super(id, NAME, NUM_PARAMETERS);
		setArmAngle(armAngle);
	}

	/**
	 * @param armAngle
	 *            The angle to set the arm
	 */
	public void setArmAngle(int armAngle) {
		setLongParameter(PARAM_INDEX_ARM_ANGLE, armAngle);
	}

	/**
	 * @return The angle to set the left motor
	 */
	public int getArmAngle() {
		return (int) getLongParameter(PARAM_INDEX_ARM_ANGLE);
	}

}

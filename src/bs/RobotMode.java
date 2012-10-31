package bs;

/**
 * Represents the possible modes the robot can be in while controlled by the
 * base station
 */
public enum RobotMode {
	/**
	 * In SAFE mode the robot will not perform an action or will discontinue an
	 * ongoing action if it deems that not doing so could cause damage to the
	 * robot.
	 */
	SAFE,
	/**
	 * In UNSAFE mode, even if the robot senses that performing an action could
	 * be dangerous, it will perform any actions it is told to perform by the
	 * base station
	 */
	UNSAFE
}

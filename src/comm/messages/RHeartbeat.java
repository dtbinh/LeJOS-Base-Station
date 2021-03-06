package comm.messages;

import bs.Telemetry;

import comm.Message;

/**
 * A robot heartbeat message.
 * 
 * This message includes telemetry data from all of the robot's sensors.
 */
public class RHeartbeat extends Message {
	public static final String NAME = "heartbeat";

	private static final int PARAM_INDEX_ULTRASONIC = 0;
	private static final int PARAM_INDEX_LIGHT = 1;
	private static final int PARAM_INDEX_TOUCH = 2;
	private static final int PARAM_INDEX_SOUND = 3;
	private static final int PARAM_INDEX_SPEEDLEFT = 4;
	private static final int PARAM_INDEX_SPEEDRIGHT = 5;
	private static final int PARAM_INDEX_ANGLEARM = 6;

	/**
	 * Constructor
	 * 
	 * Wraps the given message
	 * 
	 * @param wrap
	 *            The message to wrap
	 */
	public RHeartbeat(Message wrap) {
		super(wrap);
	}

	/**
	 * @return a new telemetry object containing all of the sensor data
	 *         contained in this message
	 */
	public Telemetry getTelemetry() {
		int ultrasonic = (int) getLongParameter(PARAM_INDEX_ULTRASONIC);
		int light = (int) getLongParameter(PARAM_INDEX_LIGHT);
		boolean touch = getBooleanParameter(PARAM_INDEX_TOUCH);
		int sound = (int) getLongParameter(PARAM_INDEX_SOUND);
		int speedLeft = (int) getLongParameter(PARAM_INDEX_SPEEDLEFT);
		int speedRight = (int) getLongParameter(PARAM_INDEX_SPEEDRIGHT);
		int angleArm = (int) getLongParameter(PARAM_INDEX_ANGLEARM);

		long time = System.currentTimeMillis();
		return new Telemetry(ultrasonic, light, touch, sound, speedLeft,
				speedRight, angleArm, time);
	}
}

package messages;

import bs.Telemetry;

import comm.Message;

/**
 * A robot heartbeat message
 */
public class RHeartbeat extends Message {
	public static final String NAME = "heartbeat";

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

	public Telemetry getTelemetry() {
		int ultrasonic = (int) getLongParameter(0);
		int light = (int) getLongParameter(2);
		boolean touch = getBooleanParameter(3);
		int sound = (int) getLongParameter(4);
		int speedLeft = (int) getLongParameter(5);
		int speedRight = (int) getLongParameter(6);
		int angleArm = (int) getLongParameter(7);
		long receiveTime = getLongParameter(8);
		return new Telemetry(ultrasonic, light, touch, sound, speedLeft,
				speedRight, angleArm, receiveTime);
	}
}

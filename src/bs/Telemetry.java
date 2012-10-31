package bs;

/**
 * @author Zack
 * 
 */
public class Telemetry {

	private int ultrasonic;
	private int light;
	private boolean touch;
	private int sound;
	private int speedLeft;
	private int speedRight;
	private int angleArm;
	private long receiveTime;

	/**
	 * Constructor
	 * 
	 * @param ultrasonic
	 *            the value of the robot's ultrasonic sensor
	 * @param light
	 *            the value of the robot's light sensor
	 * @param touch
	 *            true if the touch sensor is activated, false otherwise
	 * @param sound
	 *            the value of the robot's sound sensor
	 * @param speedLeft
	 *            the speed of the robot's left motor
	 * @param speedRight
	 *            the speed of the robot's right motor
	 * @param angleArm
	 *            the angle of the robot's arm
	 * @param receiveTime
	 *            the time in milliseconds from the initialization of the
	 *            program that this telemetry was received
	 */
	public Telemetry(int ultrasonic, int light, boolean touch, int sound,
			int speedLeft, int speedRight, int angleArm, long receiveTime) {
		super();
		this.ultrasonic = ultrasonic;
		this.light = light;
		this.touch = touch;
		this.sound = sound;
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
		this.angleArm = angleArm;
		this.receiveTime = receiveTime;
	}

	/**
	 * Gets the value of the ultrasonic sensor stored in this Telemetry object
	 * 
	 * @return the value of the ultrasonic sensor stored in this Telemetry
	 *         object
	 */
	public int getUltrasonic() {
		return ultrasonic;
	}

	/**
	 * Sets the value of the ultrasonic sensor to store in this Telemetry object
	 * 
	 * @param ultrasonic
	 *            the value of the ultrasonic sensor to store
	 */
	public void setUltrasonic(int ultrasonic) {
		this.ultrasonic = ultrasonic;
	}

	/**
	 * Gets the value of the light sensor stored in this Telemetry object
	 * 
	 * @return the value of the light sensor stored in this Telemetry object
	 */
	public int getLight() {
		return light;
	}

	/**
	 * Sets the value of the light sensor to store in this Telemetry object
	 * 
	 * @param ultrasonic
	 *            the value of the light sensor to store
	 */
	public void setLight(int light) {
		this.light = light;
	}

	/**
	 * Gets the value of the touch sensor stored in this Telemetry object
	 * 
	 * @return true if the touch sensor is set as activated in this Telemetry
	 *         object, false otherwise
	 */
	public boolean isTouch() {
		return touch;
	}

	/**
	 * Sets the value of the touch sensor to store in this Telemetry object
	 * 
	 * @param true if this telemetry sensor is to show that the touch sensor was
	 *        activated, false otherwise
	 */
	public void setTouch(boolean touch) {
		this.touch = touch;
	}

	/**
	 * Gets the value of the sound sensor stored in this Telemetry object
	 * 
	 * @return the value of the sound sensor stored in this Telemetry object
	 */
	public int getSound() {
		return sound;
	}

	/**
	 * Sets the value of the sound sensor to store in this Telemetry object
	 * 
	 * @param ultrasonic
	 *            the sound of the ultrasonic sensor to store
	 */
	public void setSound(int sound) {
		this.sound = sound;
	}

	/**
	 * Gets the speed of the left motor stored in this Telemetry object
	 * 
	 * @return the speed of the left motor stored in this Telemetry object
	 */
	public int getSpeedLeft() {
		return speedLeft;
	}

	/**
	 * Sets the speed of the left motor stored in this Telemetry object
	 * 
	 * @param the
	 *            speed of the left motor to store in this Telemetry object
	 */
	public void setSpeedLeft(int speedLeft) {
		this.speedLeft = speedLeft;
	}

	/**
	 * Gets the speed of the right motor stored in this Telemetry object
	 * 
	 * @return the speed of the right motor stored in this Telemetry object
	 */
	public int getSpeedRight() {
		return speedRight;
	}

	/**
	 * Sets the speed of the right motor stored in this Telemetry object
	 * 
	 * @param the
	 *            speed of the right motor to store in this Telemetry object
	 */
	public void setSpeedRight(int speedRight) {
		this.speedRight = speedRight;
	}

	/**
	 * Gets the angle of the robot arm stored in this Telemetry object
	 * 
	 * @return the angle of the robot arm stored in this Telemetry object
	 */
	public int getAngleArm() {
		return angleArm;
	}

	/**
	 * Sets the angle of the robot arm stored in this Telemetry object
	 * 
	 * @param angleArm
	 *            the angle of the robot arm to store in this Telemetry object
	 */
	public void setAngleArm(int angleArm) {
		this.angleArm = angleArm;
	}

	/**
	 * Gets the time in milliseconds from the initialization of the program that
	 * this telemetry was received
	 * 
	 * @return the time in milliseconds from the initialization of the program
	 *         that this telemetry was received
	 */
	public long getReceiveTime() {
		return receiveTime;
	}

	/**
	 * Sets the time in milliseconds from the initialization of the program that
	 * this telemetry was received
	 * 
	 * @param receiveTime
	 *            the time in milliseconds from the initialization of the
	 *            program that this telemetry was received
	 */
	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

}

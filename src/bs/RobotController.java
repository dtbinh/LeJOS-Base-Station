package bs;

import java.util.List;
import java.util.TimerTask;

import comm.Message;


/**
 * The focal point of all classes used to control the robot
 */
public class RobotController implements MessageReceiver, ConnectionStateListener{
	/** A list of recent telemetry data received from the robot.  They are ordered by increasing received time */
	private List<Telemetry> telemetry;
	/** A list of recent messages that have been sent to the robot.  They are ordered by increasing message ID */
	private List<Message> sentMessages;
	/** A list of recent messages received from the robot.  They are ordered by increasing message ID */
	private List<Message> receivedMessages;
	/** A connection with the robot */
	private Connection connection;
	/** The Current Mode (SAFE or UNSAFE) of the robot being controlled */
	private RobotMode currentMode;
	/** A listener that listens to the state of the robot and updates when it changes */
	private RobotStateListener robotStateListener;
	/** This timer repeatedly sends a heartbeat message to the robot every 250 ms */
	private TimerTask heartbeatTimer;
	
	/**
	 * Constructor
	 */
	public RobotController() {
		
	}
	
	/**
	 * Tells the connection object to initiate a connection with a robot
	 * @param name The name of the robot to connect to
	 * @param address The address of the robot to connect to
	 */
	public void Connect(String name, String address) {
		
	}
	
	/**
	 * Sends a message to the robot to set the wheel motor speed
	 * @param leftSpeed the speed to set the left motor to
	 * @param rightSpeed the speed to set the right motor to
	 */
	public void sendMove(int leftSpeed, int rightSpeed) {
		
	}
	
	/**
	 * Sends a message to the robot to set the arm angle
	 * @param armAngle the angle to set the arm to
	 */
	public void setArmAngle(int armAngle) {
		
	}
	
	/**
	 * Sends a heartbeat message to the robot
	 */
	public void sendHeartbeat() {
		
	}
	
	/**
	 * Sends a message to the robot to turn safe mode on or off
	 * @param safe true if turning on safe mode, false otherwise
	 */
	public void setSafeMode(boolean safe) {
		
	}
	
	/**
	 * Get the latest telemetry object received from the robot
	 * @return a Telemetry object representing the latest telemetry data received from the robot
	 */
	public Telemetry getLatestTelemetry() {
		return null;
	}
	
	/**
	 * Get the status of the connection
	 * @return
	 */
	public int getConnectionStatus() {
		return -1;
	}
	
	@Override
	public void receiveMessage(Message message) {
		
	}
	
	/** Sets the object that listens to the robot's state */
	public void setRobotStateListener(RobotStateListener listener) {
		this.robotStateListener = listener;
	}

	@Override
	public void connectionEstablished() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionLost() {
		// TODO Auto-generated method stub
		
	}
	
}

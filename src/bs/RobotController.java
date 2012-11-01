package bs;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import messages.BSHeartbeat;
import messages.BSMotorSpeed;
import messages.RHeartbeat;

import comm.Message;

/**
 * Encapsulates a robot controlled over a remote interface
 */
public class RobotController implements MessageReceiver,
		ConnectionStateListener {
	private static final int HEARTBEAT_PUBLISH_PERIOD_MS = 250;

	/**
	 * A list of recent telemetry data received from the robot. They are ordered
	 * by increasing received time
	 */
	private List<Telemetry> telemetry;

	/**
	 * A list of recent messages that have been sent to the robot. They are
	 * ordered by increasing message ID
	 */
	private List<Message> sentMessages;

	/**
	 * A list of recent messages received from the robot. They are ordered by
	 * increasing message ID
	 */
	private List<Message> receivedMessages;

	/** A connection with the robot */
	private Connection connection;

	/** The Current Mode (SAFE or UNSAFE) of the robot being controlled */
	private RobotMode currentMode;

	/**
	 * A listener that listens to the state of the robot and updates when it
	 * changes
	 */
	private RobotStateListener robotStateListener;

	/**
	 * This task repeatedly sends a heartbeat message to the robot every 250 ms
	 */
	private TimerTask heartbeatTask;

	/**
	 * This timer repeatedly kicks off the heartbeat task
	 */
	private Timer heartbeatTimer;

	/**
	 * A counter for the current message ID to send. It is incremented whenever
	 * a new message is created
	 */
	private int nextMessageId;

	/**
	 * Constructor
	 */
	public RobotController() {
		nextMessageId = 0;
		heartbeatTask = new TimerTask() {

			@Override
			public void run() {
				sendHeartbeat();
			}
		};

		heartbeatTimer = new Timer();
	}

	/**
	 * Tells the connection object to initiate a connection with a robot
	 * 
	 * @param name
	 *            The name of the robot to connect to
	 * @param address
	 *            The address of the robot to connect to
	 */
	public void Connect(String name, String address) {

	}

	private void sendMessage(Message m) {
		connection.sendMessage(m);
		nextMessageId++;
	}

	/**
	 * Sends a message to the robot to set the wheel motor speed
	 * 
	 * @param leftSpeed
	 *            the speed to set the left motor to
	 * @param rightSpeed
	 *            the speed to set the right motor to
	 */
	public void sendMove(int leftSpeed, int rightSpeed) {
		Message moveMessage = new BSMotorSpeed(nextMessageId, leftSpeed,
				rightSpeed);
		sendMessage(moveMessage);
	}

	/**
	 * Sends a message to the robot to set the arm angle
	 * 
	 * @param armAngle
	 *            the angle to set the arm to
	 */
	public void setArmAngle(int armAngle) {

	}

	/**
	 * Sends a heartbeat message to the robot
	 */
	public void sendHeartbeat() {
		Date currentTime = new Date();
		Message heartbeat = new BSHeartbeat(nextMessageId,
				currentTime.getTime());
		sendMessage(heartbeat);
	}

	/**
	 * Sends a message to the robot to turn safe mode on or off
	 * 
	 * @param safe
	 *            true if turning on safe mode, false otherwise
	 */
	public void setSafeMode(boolean safe) {

	}

	/**
	 * Get the latest telemetry object received from the robot
	 * 
	 * @return a Telemetry object representing the latest telemetry data
	 *         received from the robot
	 */
	public Telemetry getLatestTelemetry() {
		return telemetry.get(telemetry.size() - 1);
	}

	/**
	 * Get the status of the connection
	 * 
	 * @return
	 */
	public int getConnectionStatus() {
		return -1;
	}

	@Override
	public void receiveMessage(Message message) {
		String name = message.getName();
		if (name.equals(RHeartbeat.NAME)) {
			processHeartbeatMessage(new RHeartbeat(message));
		}
	}

	/**
	 * Process a received heartbeat message, extrating the telemetry data and
	 * adding it to the list {@code telemetry}. It then notifies the robot state
	 * listener that the robot's state has changed
	 * 
	 * @param message
	 *            the heartbeat message that is to be processed
	 */
	private void processHeartbeatMessage(RHeartbeat message) {
		Telemetry newTelemetry = message.getTelemetry();
		telemetry.add(newTelemetry);
		robotStateListener.stateChanged();
	}

	/** Sets the object that listens to the robot's state */
	public void setRobotStateListener(RobotStateListener listener) {
		this.robotStateListener = listener;
	}

	@Override
	public void connectionEstablished() {
		heartbeatTimer.schedule(heartbeatTask, 0, HEARTBEAT_PUBLISH_PERIOD_MS);
	}

	@Override
	public void connectionLost() {
		heartbeatTimer.cancel();
	}

}

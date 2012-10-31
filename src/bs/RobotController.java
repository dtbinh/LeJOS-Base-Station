package bs;

import java.util.List;
import java.util.TimerTask;

import comm.Message;

public class RobotController implements MessageReceiver, ConnectionStateListener{
	private List<Telemetry> telemetry;
	private List<Message> sentMessages;
	private List<Message> receivedMessages;
	private Connection connection;
	private RobotMode currentMode;
	private RobotStateListener robotStateListener;
	private TimerTask heartbeatTimer;
	
	public RobotController() {
		
	}
	
	public void Connect(String name, String address) {
		
	}
	
	public void sendMove(int leftSpeed, int rightSpeed) {
		
	}
	
	public void setArmAngle(int armAngle) {
		
	}
	
	public void sendHeartbeat() {
		
	}
	
	public void setSafeMode(boolean safe) {
		
	}
	
	public Telemetry getLatestTelemetry() {
		return null;
	}
	
	public int getConnectionStatus() {
		return -1;
	}
	
	@Override
	public void receiveMessage(Message message) {
		
	}
	
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

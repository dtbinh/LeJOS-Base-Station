package bs;

import java.util.List;

import comm.Message;

public class RobotController implements MessageReceiver{
	private List<Telemetry> telemetry;
	private List<Message> sentMessages;
	private List<Message> receivedMessages;
	private Connection connection;
	private RobotMode currentMode;

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
	
}

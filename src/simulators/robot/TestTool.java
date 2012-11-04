package simulators.robot;

import java.util.Timer;
import java.util.TimerTask;

import bs.Connection;

public class TestTool {
	final Connection connection;
	final TestRobot robot;
	
	boolean encounterMode;
	
	public TestTool(Connection connection) {
		this.connection = connection;
		robot = new TestRobot(connection);
	}
	
	public void init(boolean encounterMode){
		this.connection.connect("", "");
		this.encounterMode = encounterMode;

		TimerTask heartBeat = new TimerTask() {

			@Override
			public void run() {
				robot.generateHeartbeat();
			}
		};

		TimerTask encounter = new TimerTask() {

			@Override
			public void run() {
				robot.generateEncounter();
			}
		};

		Timer heartBeatTimer = new Timer();
		heartBeatTimer.schedule(heartBeat, 0, 1500);

		Timer encounterTimer = new Timer();
		encounterTimer.schedule(encounter, 0, 5000);

	}
}

package simulators.robot;

import java.util.Timer;
import java.util.TimerTask;

public class TestTool {
	final Connection connection = new Connection();
	final TestRobot robot = new TestRobot(connection);
	
	boolean encounterMode;
	
	public void init(boolean encounterMode){
		this.encounterMode = encounterMode;

		ReadingThread reader = new ReadingThread(connection, robot);
		reader.start();

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
		heartBeatTimer.schedule(heartBeat, 0, 15000);

		Timer encounterTimer = new Timer();
		encounterTimer.schedule(encounter, 0, 60000);

	}
}

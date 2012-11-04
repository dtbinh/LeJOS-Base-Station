package simulators.robot;

public class ReadingThread extends Thread {
	Connection connection;
	TestRobot robot;

	public ReadingThread(Connection connection, TestRobot robot) {
		this.connection = connection;
		this.robot = robot;
	}

	public void run() {
		while (true) {
			byte[] message = connection.readMessage();
			robot.processMessage(message);
		}
	}
}

package simulators.robot;

import bs.StdIOConnection;

/**
 * Robot Simulator Main Class
 * 
 * Entry point into a standalone robot simulator application which communicates
 * over Standard Input/Output
 */
public class RobotSimulator {

	public static void main(String[] args) {
		boolean encounterMode = false;
		if (args.length > 0 && args[0].equals("-encounter"))
			encounterMode = true;
		StdIOConnection connection = new StdIOConnection();
		TestTool testTool = new TestTool(connection);
		testTool.init(encounterMode);
	}

}

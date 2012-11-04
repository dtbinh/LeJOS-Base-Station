package simulators.robot;


public class RobotSimulator {
	
	public static void main(String[] args) {
		
		boolean encounterMode = false;
		if(args.length > 0 && args[0].equals("-encounter"))
			encounterMode = true;
		TestTool testTool = new TestTool();
		testTool.init(encounterMode);
	}
	
}

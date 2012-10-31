package bs;

/** Reacts when the robot's state, including telemetry data and current mode, changes */
public interface RobotStateListener {

	/**Called when the robot's state is updated*/
	public void stateChanged();
}

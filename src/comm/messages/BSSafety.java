package comm.messages;

import comm.Message;

/**
 * A message commanding the robot to change the speed of it's drive motors
 */
public class BSSafety extends Message {
	private static final String NAME = "safety";

	private static final int NUM_PARAMETERS = 1;
	
	private static final int PARAM_INDEX_SAFE_MODE = 0;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            The message id
	 * @param value
	 * 			  If you want to enable or disable safe mode
	 */
	public BSSafety(int id, boolean value) {
		super(id, NAME, NUM_PARAMETERS);
		setSafeMode(value);
	}

	/**
	 * @param safe
	 *            if safe mode should be enabled
	 */
	public void setSafeMode(boolean safe) {
		setBoolParameter(PARAM_INDEX_SAFE_MODE, safe);
	}

	/**
	 * @return The mode to set the robot to
	 */
	public boolean getSafeMode() {
		return  getBooleanParameter(PARAM_INDEX_SAFE_MODE);
	}

}

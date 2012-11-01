package messages;

import comm.Message;

public class BSMotorSpeed extends Message {
	private static final String NAME = "motor_speed";

	private static final int NUM_PARAMETERS = 2;

	private static final int PARAM_INDEX_SPEED_LEFT = 0;
	private static final int PARAM_INDEX_SPEED_RIGHT = 1;

	public BSMotorSpeed(int id, int speedLeft, int speedRight) {
		super(id, NAME, NUM_PARAMETERS);
		setSpeedLeft(speedLeft);
		setSpeedRight(speedRight);
	}

	public void setSpeedLeft(int speedLeft) {
		setLongParameter(PARAM_INDEX_SPEED_LEFT, speedLeft);
	}

	public void setSpeedRight(int speedRight) {
		setLongParameter(PARAM_INDEX_SPEED_RIGHT, speedRight);
	}

	public int getSpeedLeft() {
		return (int) getLongParameter(PARAM_INDEX_SPEED_LEFT);
	}

	public int getSpeedRight() {
		return (int) getLongParameter(PARAM_INDEX_SPEED_RIGHT);
	}
}

package comm.messages;

import comm.Message;

/**
 * A Base Station Heartbeat message
 */
public class BSHeartbeat extends Message {
	private static final String NAME = "heartbeat";

	private static final int NUM_PARAMETERS = 1;

	private static final int PARAM_INDEX_TIMESTAMP = 0;

	public BSHeartbeat(int id, long timestamp) {
		super(id, NAME, NUM_PARAMETERS);
		setTimestamp(timestamp);
	}

	public long getTimestamp() {
		return getLongParameter(PARAM_INDEX_TIMESTAMP);
	}

	public void setTimestamp(long timestamp) {
		setLongParameter(PARAM_INDEX_TIMESTAMP, timestamp);
	}
}

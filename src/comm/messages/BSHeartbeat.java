package comm.messages;

import comm.Message;

/**
 * A Base Station Heartbeat message
 */
public class BSHeartbeat extends Message {
	private static final String NAME = "heartbeat";

	private static final int NUM_PARAMETERS = 1;

	private static final int PARAM_INDEX_TIMESTAMP = 0;

	/**
	 * Creates a new Base Station Hearbeat message with the given message ID and
	 * timestamp
	 * 
	 * @param id
	 *            The message id
	 * @param timestamp
	 *            The timestamp associated with the hearbeat
	 */
	public BSHeartbeat(int id, long timestamp) {
		super(id, NAME, NUM_PARAMETERS);
		setTimestamp(timestamp);
	}

	/**
	 * @return The timestamp of this hearbeat
	 */
	public long getTimestamp() {
		return getLongParameter(PARAM_INDEX_TIMESTAMP);
	}

	/**
	 * @param timestamp
	 *            The timestamp of this heartbeat
	 */
	public void setTimestamp(long timestamp) {
		setLongParameter(PARAM_INDEX_TIMESTAMP, timestamp);
	}
}
